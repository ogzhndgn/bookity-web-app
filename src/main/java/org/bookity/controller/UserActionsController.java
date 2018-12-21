package org.bookity.controller;

import com.sun.prism.shader.DrawSemiRoundRect_LinearGradient_PAD_AlphaTest_Loader;
import org.bookity.exception.ServiceException;
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
public class UserActionsController {

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

    @PostMapping(value = "/login")
    public ModelAndView login(HttpServletRequest request) {
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        try {
            User user = userService.loginUser(mailAddress, password);
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
            return null;
        } catch (ServiceException e) {
            logger.error("Error at register method: ", e);
            ModelAndView modelAndView = new ModelAndView("main");
            modelAndView.addObject("errorMessage", messageUtil.getErrorMessage(e.getServiceError().name()));
            return modelAndView;
        }
    }
}
