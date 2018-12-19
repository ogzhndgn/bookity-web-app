package org.bookity.service.spec;

import org.bookity.exception.ServiceException;
import org.bookity.model.User;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public interface UserService {
    User registerUser(String mailAddress, String password, String passwordConfirmation) throws ServiceException;

    User getUserByEmail(String mailAddress) throws ServiceException;

    User loginUser(String mailAddress, String password) throws ServiceException;
}
