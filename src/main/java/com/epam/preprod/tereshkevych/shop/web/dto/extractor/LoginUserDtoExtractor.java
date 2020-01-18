package com.epam.preprod.tereshkevych.shop.web.dto.extractor;

import com.epam.preprod.tereshkevych.shop.web.dto.LoginUserDto;

import javax.servlet.http.HttpServletRequest;

/**
 * Create dto for validation authorization
 *
 * @author Vladyslav Tereshkevych
 */
public class LoginUserDtoExtractor {

    private static final String USER_LOGIN = "login";
    private static final String USER_PASSWORD = "password";

    public LoginUserDto extract(HttpServletRequest request) {
        LoginUserDto loginUserDto = new LoginUserDto();
        loginUserDto.setLogin(request.getParameter(USER_LOGIN));
        loginUserDto.setPassword(request.getParameter(USER_PASSWORD));
        return loginUserDto;
    }
}