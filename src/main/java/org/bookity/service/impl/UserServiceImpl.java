package org.bookity.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.bookity.dao.UserDao;
import org.bookity.enums.ServiceError;
import org.bookity.enums.UserStatus;
import org.bookity.exception.ServiceException;
import org.bookity.model.User;
import org.bookity.service.spec.UserService;
import org.bookity.service.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Service
public class UserServiceImpl implements UserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;
    @Autowired
    PasswordUtil passwordUtil;

    @Override
    public User registerUser(String mailAddress, String password, String passwordConfirmation) throws ServiceException {
        mailAddress = StringUtils.trim(mailAddress);
        if (!EmailValidator.getInstance().isValid(mailAddress)) {
            throw new ServiceException(ServiceError.ERR_INVALID_EMAIL);
        }
        if (this.isExistsUser(mailAddress)) {
            throw new ServiceException(ServiceError.ERR_USER_ALREADY_EXISTS);
        }
        if (!StringUtils.equals(password, passwordConfirmation)) {
            throw new ServiceException(ServiceError.ERR_PASSWORDS_DONT_MATCH);
        }
        if (!passwordUtil.isValidPasswordFormat(password)) {
            throw new ServiceException(ServiceError.ERR_INVALID_PASSWORD_FORMAT);
        }
        return createUser(mailAddress, password);
    }

    @Override
    public User getUserByEmail(String mailAddress) throws ServiceException {
        User user = userDao.getByMailAddress(mailAddress);
        if (user == null) {
            throw new ServiceException(ServiceError.ERR_USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User loginUser(String mailAddress, String password) throws ServiceException {
        User user = this.getUserByEmail(mailAddress);
        if (!passwordUtil.isMatched(user.getPasswordHash(), user.getPasswordSalt(), password)) {
            throw new ServiceException(ServiceError.ERR_INVALID_LOGIN_CREDENTIAL);
        }
        return user;
    }

    private User createUser(String mailaddress, String password) {
        String passwordSalt = passwordUtil.generatePasswordSalt();
        String passwordHash = passwordUtil.getPasswordHash(password, passwordSalt);
        User user = new User();
        user.setMailAddress(mailaddress);
        user.setPasswordSalt(passwordSalt);
        user.setPasswordHash(passwordHash);
        user.setStatus(UserStatus.ACTIVE.name());
        userDao.save(user);
        return user;
    }

    private boolean isExistsUser(String mailAddress) {
        try {
            this.getUserByEmail(mailAddress);
        } catch (ServiceException e) {
            return false;
        }
        return true;
    }
}