package com.epam.preprod.tereshkevych.shop.db.extractor;

import com.epam.preprod.tereshkevych.shop.db.entity.Role;
import com.epam.preprod.tereshkevych.shop.db.entity.Status;
import com.epam.preprod.tereshkevych.shop.db.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create user with fields which was obtained from database
 *
 * @author Vladyslav Tereshkevych
 */
public class UserDaoExtractor implements DbExtractor<User> {

    private static final String ENTITY_ID = "id";
    private static final String USER_ROLE = "role";
    private static final String USER_LOGIN = "login";
    private static final String USER_EMAIL = "email";
    private static final String USER_PASSWORD = "password";
    private static final String USER_FIRST_NAME = "first_name";
    private static final String USER_LAST_NAME = "last_name";
    private static final String USER_AVATAR = "avatar";

    @Override
    public User extract(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(ENTITY_ID));
        user.setRole(Role.valueOf(rs.getString(USER_ROLE).toUpperCase()));
        user.setLogin(rs.getString(USER_LOGIN));
        user.setEmail(rs.getString(USER_EMAIL));
        user.setPassword(rs.getString(USER_PASSWORD));
        user.setFirstName(rs.getString(USER_FIRST_NAME));
        user.setLastName(rs.getString(USER_LAST_NAME));
        user.setAvatar(rs.getString(USER_AVATAR));
        return user;
    }
}