package com.epam.preprod.tereshkevych.shop.converter;

import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;

/**
 * Create user with fields which was obtained from dto
 *
 * @author Vladyslav Tereshkevych
 */
public class FormUserDtoToUserConverter {

    /**
     * Convert into user from dto
     *
     * @param bean the dto
     * @return the user
     */
    public static User convert(FormUserDto bean) {
        User user = new User();
        user.setLogin(bean.getLogin());
        user.setEmail(bean.getEmail());
        user.setPassword(bean.getPassword());
        user.setFirstName(bean.getFirstName());
        user.setLastName(bean.getLastName());
        user.setNewsletters(bean.getNewsletters());
        return user;
    }
}