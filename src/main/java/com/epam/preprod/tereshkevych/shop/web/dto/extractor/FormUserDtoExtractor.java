package com.epam.preprod.tereshkevych.shop.web.dto.extractor;

import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Create dto for validation registration
 *
 * @author Vladyslav Tereshkevych
 */
public class FormUserDtoExtractor {

    private static final String USER_LOGIN = "login";

    private static final String USER_PASSWORD = "password";

    private static final String USER_CONFIRM_PASSWORD = "confirmPassword";

    private static final String USER_EMAIL = "email";

    private static final String USER_FIRST_NAME = "firstName";

    private static final String USER_LAST_NAME = "lastName";

    private static final String USER_NEWSLETTERS = "newsletters";

    public FormUserDto extract(HttpServletRequest request) {
        FormUserDto formUserDto = new FormUserDto();
        formUserDto.setLogin(request.getParameter(USER_LOGIN));
        formUserDto.setEmail(request.getParameter(USER_EMAIL));
        formUserDto.setPassword(request.getParameter(USER_PASSWORD));
        formUserDto.setConfirmPassword(request.getParameter(USER_CONFIRM_PASSWORD));
        formUserDto.setFirstName(request.getParameter(USER_FIRST_NAME));
        formUserDto.setLastName(request.getParameter(USER_LAST_NAME));
        String[] newsletters = request.getParameterValues(USER_NEWSLETTERS);
        if (newsletters != null) {
            formUserDto.setNewsletters(Arrays.asList(newsletters));
        }
        return formUserDto;
    }
}