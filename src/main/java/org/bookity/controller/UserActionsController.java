package org.bookity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Controller
public class UserActionsController {
    private final static Logger logger = LoggerFactory.getLogger(UserActionsController.class);

    @GetMapping(value = "/")
    public ModelAndView displayMainPage() {
        return new ModelAndView("main");
    }
}
