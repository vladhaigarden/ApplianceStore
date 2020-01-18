package com.epam.preprod.tereshkevych.shop.error.validator.registration;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.util.validator.FormUserDtoValidator;
import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Search errors which can appear because of incorrect FormUserDto
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorFormUserBean extends ChainValidator {

    private FormUserDto formUserDto;

    public ChainValidatorFormUserBean(ChainValidator nextHolder, FormUserDto formUserDto) {
        super(nextHolder);
        this.formUserDto = formUserDto;
    }

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new FormUserDtoValidator().validate(formUserDto);
        if (!formErrorContainer.isEmpty()) {
            return formErrorContainer;
        }
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }
}