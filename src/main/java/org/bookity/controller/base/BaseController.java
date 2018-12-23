package org.bookity.controller.base;

import org.bookity.model.SessionInfo;
import org.bookity.model.User;
import org.bookity.service.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Controller
public class BaseController {

    public static final String SESSION_INFO = "sessionInfo";
    @Autowired
    PasswordUtil passwordUtil; //It work fine but naming is not. Think?

    public SessionInfo setSessionInfo(User user, HttpServletRequest request) {
        SessionInfo sessionInfo = new SessionInfo();
        sessionInfo.setUser(user);
        sessionInfo.setSessionId(passwordUtil.generatePasswordSalt());
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_INFO, sessionInfo);
        return sessionInfo;
    }

    public void removeSessionInfo(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_INFO);
    }

    public SessionInfo getSessionInfo(HttpServletRequest request) {
        return (SessionInfo) request.getSession().getAttribute(SESSION_INFO);
    }
}