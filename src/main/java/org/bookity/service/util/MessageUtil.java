package org.bookity.service.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class MessageUtil {
    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor messageSourceAccessor;
    private static final String ERROR_PREFIX = "text.ServiceError.";

    @PostConstruct
    private void init() {
        messageSourceAccessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
    }

    public String getErrorMessage(String errorCode) {
        return messageSourceAccessor.getMessage(ERROR_PREFIX + errorCode, errorCode);
    }
}