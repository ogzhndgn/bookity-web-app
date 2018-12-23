package org.bookity.service.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
@Component
public class PasswordUtil {

    private String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    public String generatePasswordSalt() {
        String uuid = UUID.randomUUID().toString();
        return StringUtils.remove(uuid, "-");
    }

    public String getPasswordHash(String plainPassword, String passwordSalt) {
        return DigestUtils.sha512Hex(plainPassword + ":" + passwordSalt);
    }

    public boolean isMatched(String passwordHash, String passwordSalt, String plainPassword) {
        String passwordHashProvided = this.getPasswordHash(plainPassword, passwordSalt);
        return StringUtils.equals(passwordHash, passwordHashProvided);
    }

    /**
     * According to business logic abstraction. This password format checker is located in PasswordUtil instead of field annotation.
     * <p>
     * a digit must occur at least once
     * a lower case letter must occur at least once
     * an upper case letter must occur at least once
     * no whitespace allowed in the entire string
     * at least 8 characters
     *
     * @param plainPassword which is provided by user
     * @return boolean
     */
    public boolean isValidPasswordFormat(String plainPassword) {
        return plainPassword.matches(PASSWORD_PATTERN);
    }

}