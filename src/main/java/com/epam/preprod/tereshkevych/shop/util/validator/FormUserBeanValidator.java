package com.epam.preprod.tereshkevych.shop.util.validator;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;

import java.util.Objects;

/**
 * Validation of the obtained dto
 *
 * @author Vladyslav Tereshkevych
 */
public class FormUserBeanValidator {

    private static final String ERROR_USER_LOGIN_KEY = "error_user_login";

    private static final String ERROR_USER_EMAIL_KEY = "error_user_email";

    private static final String ERROR_USER_PASSWORD_KEY = "error_user_password";

    private static final String ERROR_USER_CONFIRM_PASSWORD_KEY = "error_user_confirm_password";

    private static final String ERROR_USER_FIRST_NAME_KEY = "error_user_first_name";

    private static final String ERROR_USER_LAST_NAME_KEY = "error_user_last_name";

    private static final String ERROR_VALIDATE_LOGIN = "Entered the incorrect login!";

    private static final String ERROR_VALIDATE_EMAIL = "Entered the incorrect email!";

    private static final String ERROR_VALIDATE_PASSWORD = "Entered the incorrect password!";

    private static final String ERROR_VALIDATE_CONFIRM_PASSWORD = "Entered the incorrect confirm password!";

    private static final String ERROR_VALIDATE_MATCH_PASSWORDS = "Passwords does not match!";

    private static final String ERROR_VALIDATE_FIRST_NAME = "Entered the incorrect first name!";

    private static final String ERROR_VALIDATE_LAST_NAME = "Entered the incorrect last name!";

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private FieldValidator fieldValidator = new FieldValidator();

    public FormErrorContainer validate(FormUserDto bean) {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        validateFieldForLength(formErrorContainer, bean.getLogin(), ERROR_USER_LOGIN_KEY, ERROR_VALIDATE_LOGIN);
        validateEmail(formErrorContainer, bean.getEmail());
        validatePassword(formErrorContainer, bean.getPassword(), bean.getConfirmPassword());
        validateConfirmationPassword(formErrorContainer, bean.getPassword(), bean.getConfirmPassword());
        validateFieldForLength(formErrorContainer, bean.getFirstName(), ERROR_USER_FIRST_NAME_KEY, ERROR_VALIDATE_FIRST_NAME);
        validateFieldForLength(formErrorContainer, bean.getLastName(), ERROR_USER_LAST_NAME_KEY, ERROR_VALIDATE_LAST_NAME);
        return formErrorContainer;
    }

    private void validateFieldForLength(FormErrorContainer formErrorContainer, String value, String nameField, String errorMessages) {
        if (fieldValidator.isFieldLengthNotValid(value)) {
            formErrorContainer.add(nameField, errorMessages);
        }
    }

    private void validateEmail(FormErrorContainer formErrorContainer, String email) {
        if (fieldValidator.isFieldLengthNotValid(email) || fieldValidator.isNotValidByPattern(email, EMAIL_PATTERN)) {
            formErrorContainer.add(ERROR_USER_EMAIL_KEY, ERROR_VALIDATE_EMAIL);
        }
    }

    private void validatePassword(FormErrorContainer formErrorContainer, String password, String confirmPassword) {
        if (fieldValidator.isFieldLengthNotValid(password)) {
            formErrorContainer.add(ERROR_USER_PASSWORD_KEY, ERROR_VALIDATE_PASSWORD);
        }
        if (fieldValidator.isFieldLengthNotValid(confirmPassword)) {
            formErrorContainer.add(ERROR_USER_CONFIRM_PASSWORD_KEY, ERROR_VALIDATE_CONFIRM_PASSWORD);
        }
    }

    private void validateConfirmationPassword(FormErrorContainer formErrorContainer, String password, String confirmPassword) {
        if (!Objects.equals(password, confirmPassword)) {
            formErrorContainer.add(ERROR_USER_CONFIRM_PASSWORD_KEY, ERROR_VALIDATE_MATCH_PASSWORDS);
        }
    }
}