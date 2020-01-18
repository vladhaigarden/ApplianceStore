package com.epam.preprod.tereshkevych.shop.util.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * Validation of the entered fields
 *
 * @author Vladyslav Tereshkevych
 */
public class FieldValidator {

    private static final int MINIMUM_LENGTH_STRING_FIELD = 4;

    public boolean isFieldLengthNotValid(String value) {
        return StringUtils.isEmpty(value) || value.length() < MINIMUM_LENGTH_STRING_FIELD;
    }

    public boolean isNotValidByPattern(String value, String regEx) {
        return !Pattern.matches(regEx, value);
    }
}