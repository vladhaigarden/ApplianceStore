package com.epam.preprod.tereshkevych.shop.error.validator.login;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.util.validator.UserLoginValidator;
import com.epam.preprod.tereshkevych.shop.web.dto.LoginUserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Search errors which can appear because of incorrect LoginUserDto
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorLoginUserBean extends ChainValidator {

    private LoginUserDto loginUserDto;

    public ChainValidatorLoginUserBean(ChainValidator nextSearcher, LoginUserDto loginUserDto) {
        super(nextSearcher);
        this.loginUserDto = loginUserDto;
    }

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new UserLoginValidator().validate(loginUserDto);
        if (!formErrorContainer.isEmpty()) {
            return formErrorContainer;
        }
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }
}