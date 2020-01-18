package com.epam.preprod.tereshkevych.shop.error.validator.registration;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Search errors which can appear because of entered not unique login
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorUniqueLogin extends ChainValidator {

    private static final String ERROR_USER_LOGIN_KEY = "error_user_login";

    private static final String ERR_VALIDATE_LOGIN_ALREADY_EXIST = "Such login already exists!";

    private UserService userService;

    private FormUserDto formUserDto;

    public ChainValidatorUniqueLogin(ChainValidator nextHolder, UserService userService, FormUserDto formUserDto) {
        super(nextHolder);
        this.userService = userService;
        this.formUserDto = formUserDto;
    }

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        if (userService.getUserByLogin(formUserDto.getLogin()) != null) {
            formErrorContainer.add(ERROR_USER_LOGIN_KEY, ERR_VALIDATE_LOGIN_ALREADY_EXIST);
            return formErrorContainer;
        }
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }
}