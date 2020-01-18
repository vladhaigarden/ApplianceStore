package com.epam.preprod.tereshkevych.shop.web.dto;

import java.util.List;
import java.util.Objects;

public class FormUserDto {

    private String login;

    private String email;

    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private List<String> newsletters;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getNewsletters() {
        return newsletters;
    }

    public void setNewsletters(List<String> newsletters) {
        this.newsletters = newsletters;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, email, password, confirmPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FormUserDto bean = (FormUserDto) o;
        return Objects.equals(login, bean.login) &&
                Objects.equals(email, bean.email) &&
                Objects.equals(password, bean.password) &&
                Objects.equals(confirmPassword, bean.confirmPassword);
    }

    @Override
    public String toString() {
        return "FormUserDto{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", newsletters=" + newsletters +
                '}';
    }
}