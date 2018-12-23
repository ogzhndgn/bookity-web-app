package org.bookity.controller;

import org.bookity.controller.base.BaseController;
import org.bookity.enums.ServiceError;
import org.bookity.exception.ServiceException;
import org.bookity.model.SessionInfo;
import org.bookity.model.User;
import org.bookity.service.spec.UserService;
import org.bookity.service.util.EmailSender;
import org.bookity.service.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Controller
public class UserActionsController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    MessageUtil messageUtil;
    @Autowired
    EmailSender emailSender;

    private final static Logger logger = LoggerFactory.getLogger(UserActionsController.class);

    @GetMapping(value = "/")
    public ModelAndView displayMainPage() {
        return new ModelAndView("main");
    }

    @GetMapping(value = "/notauth")
    public ModelAndView notAuth() {
        ModelAndView modelAndView = new ModelAndView("main");
        modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(ServiceError.ERR_NOT_AUTH_USER.name()));
        return modelAndView;
    }

    @PostMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        logger.info(mailAddress + " is trying to login...");
        try {
            User user = userService.loginUser(mailAddress, password);
            SessionInfo sessionInfo = super.setSessionInfo(user, request);
            logger.info(mailAddress + " logged in with sessionInfo id: " + sessionInfo.getSessionId());
            return new ModelAndView("redirect:/book-list/1");
        } catch (ServiceException e) {
            logger.error("Error at login method for mailaddress: " + mailAddress, e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
            return modelAndView;
        } catch (Exception e) {
            logger.error("Error at login method for mailaddress: " + mailAddress, e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(ServiceError.ERR_UNEXPECTED_ERROR.name()));
            return modelAndView;
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request) {
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationpassword");
        logger.info(mailAddress + " is trying to register...");
        try {
            User user = userService.registerUser(mailAddress, password, confirmationPassword);
            emailSender.sendWelcomeMail(user.getMailAddress());
            SessionInfo sessionInfo = super.setSessionInfo(user, request);
            logger.info(mailAddress + " is registered and logged in with sessionInfo id: " + sessionInfo.getSessionId());
            return new ModelAndView("redirect:/book-list/1");
        } catch (ServiceException e) {
            logger.error("Error at register method for mailAddress: " + mailAddress, e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
            return modelAndView;
        } catch (Exception e) {
            logger.error("Error at register method for mailaddress: " + mailAddress, e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(ServiceError.ERR_UNEXPECTED_ERROR.name()));
            return modelAndView;
        }
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        SessionInfo sessionInfo = super.getSessionInfo(request);
        logger.info(sessionInfo.getSessionId() + " is loggedout");
        super.removeSessionInfo(request);
        return new ModelAndView("main");
    }
}
