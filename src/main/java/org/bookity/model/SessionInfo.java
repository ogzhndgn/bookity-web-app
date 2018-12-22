package org.bookity.model;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public class SessionInfo {
    private User user;
    private String sessionId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}