package org.bookity.exception;

import org.bookity.enums.ServiceError;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public class ServiceException extends Exception {

    public ServiceException(ServiceError serviceError) {
        super(serviceError.toString());
    }
}
