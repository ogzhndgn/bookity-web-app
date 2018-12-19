package org.bookity.dao;

import org.bookity.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Oguzhan Dogan <dogan_oguzhan@hotmail.com>
 */
public interface UserDao extends CrudRepository<User, Long> {
    User getByMailAddress(String mailAddress);
}