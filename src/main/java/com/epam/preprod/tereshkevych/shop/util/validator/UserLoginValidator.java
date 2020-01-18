package com.epam.preprod.tereshkevych.shop.util.validator;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.web.dto.LoginUserDto;

public class UserLoginValidator {

    private static final String ERROR_USER_LOGIN_KEY = "error_user_login";

    private static final String ERROR_USER_PASSWORD_KEY = "error_user_password";

    private static final String ERROR_VALIDATE_LOGIN = "Entered the incorrect login!";

    private static final String ERROR_VALIDATE_PASSWORD = "Entered the incorrect password!";

    private FieldValidator fieldValidator = new FieldValidator();

    public FormErrorContainer validate(LoginUserDto bean) {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        checkStringField(formErrorContainer, bean.getLogin(), ERROR_USER_LOGIN_KEY, ERROR_VALIDATE_LOGIN);
        checkStringField(formErrorContainer, bean.getPassword(), ERROR_USER_PASSWORD_KEY, ERROR_VALIDATE_PASSWORD);
        return formErrorContainer;
    }

    private void checkStringField(FormErrorContainer formErrorContainer, String value, String nameField, String errorMessages) {
        if (fieldValidator.isFieldLengthNotValid(value)) {
            formErrorContainer.add(nameField, errorMessages);
        }
    }
}