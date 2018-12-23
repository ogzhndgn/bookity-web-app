package org.bookity.interceptor;

import org.bookity.model.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class SessionInterceptor implements HandlerInterceptor {

    private final static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
        String uri = request.getRequestURI();
        logger.info("Uri: " + uri + " will be checked");
        if (session == null) {
            logger.info("Session is null for request to: " + uri);
            response.sendRedirect(contextPath + "/notauth");
            return false;
        }
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
        if (sessionInfo == null) {
            logger.info("SessionInfo is null for request to: " + uri);
            response.sendRedirect(contextPath + "/notauth");
            return false;
        }
        return true;
    }
}