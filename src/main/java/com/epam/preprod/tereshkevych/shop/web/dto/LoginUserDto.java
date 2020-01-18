package com.epam.preprod.tereshkevych.shop.web.dto;

import java.util.Objects;

public class LoginUserDto {

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginUserDto that = (LoginUserDto) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public String toString() {
        return "LoginUserDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}