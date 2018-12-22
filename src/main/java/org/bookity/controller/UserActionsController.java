package org.bookity.controller;

import org.apache.commons.lang3.StringUtils;
import org.bookity.enums.ServiceError;
import org.bookity.exception.ServiceException;
import org.bookity.model.SessionInfo;
import org.bookity.model.User;
import org.bookity.service.spec.UserService;
import org.bookity.service.util.EmailSender;
import org.bookity.service.util.MessageUtil;
import org.bookity.service.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Controller
public class UserActionsController {

    @Autowired
    UserService userService;
    @Autowired
    MessageUtil messageUtil;
    @Autowired
    EmailSender emailSender;
    @Autowired
    PasswordUtil passwordUtil;

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
        try {
            User user = userService.loginUser(mailAddress, password);
            this.setSessionInfo(user, request);
            return null;
        } catch (ServiceException e) {
            logger.error("Error at login method: ", e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
            return modelAndView;
        }
    }

    @PostMapping(value = "/register")
    public ModelAndView register(HttpServletRequest request) {
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        String confirmationPassword = request.getParameter("confirmationpassword");
        try {
            User user = userService.registerUser(mailAddress, password, confirmationPassword);
            emailSender.sendWelcomeMail(user.getMailAddress());
            this.setSessionInfo(user, request);
            return null;
        } catch (ServiceException e) {
            logger.error("Error at register method: ", e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
            return modelAndView;
        }
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        this.removeSessionInfo(request);
        return new ModelAndView("main");
    }

    private void setSessionInfo(User user, HttpServletRequest request) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUser(user);
        sessionInfo.setSessionId(passwordUtil.generatePasswordSalt());
        HttpSession session = request.getSession();
        session.setAttribute("sessionInfo", sessionInfo);
    }

    private void removeSessionInfo(HttpServletRequest request) {
        request.getSession().removeAttribute("sessionInfo");
    }
}
