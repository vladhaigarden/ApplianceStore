package com.epam.preprod.tereshkevych.shop.error.validator.login;

import com.epam.preprod.tereshkevych.shop.container.Cart;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import com.epam.preprod.tereshkevych.shop.web.dto.LoginUserDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Search errors which can appear because of wrong authorization.
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorUser extends ChainValidator {

    private static final String USER = "user";

    private static final String CART = "cart";

    private static final String ERROR_LOG_IN_KEY = "error_log_in";
    private static final String ERR_LOG_IN_FIND_USER = "Cannot find user with such login or password";

    private UserService userService;
    private LoginUserDto loginUserDto;

    public ChainValidatorUser(UserService userService, LoginUserDto loginUserDto) {
        this.userService = userService;
        this.loginUserDto = loginUserDto;
    }

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        User user = userService.login(loginUserDto.getLogin(), loginUserDto.getPassword());
        if (user == null) {
            formErrorContainer.add(ERROR_LOG_IN_KEY, ERR_LOG_IN_FIND_USER);
            return formErrorContainer;
        }
        HttpSession session = request.getSession();
        session.setAttribute(USER, user);
        session.setAttribute(CART, getCart(session));
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART);
        return cart != null ? cart : new Cart();
    }
}