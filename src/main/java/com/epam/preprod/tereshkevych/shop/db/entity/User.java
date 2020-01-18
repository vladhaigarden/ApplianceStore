package com.epam.preprod.tereshkevych.shop.db.entity;

import java.util.List;
import java.util.Objects;

public class User extends Entity {

    private Role role;

    private String login;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private List<String> newsletters;

    private String avatar;

    public User() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, login, email, password, firstName, lastName, newsletters, avatar);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return role == user.role &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(newsletters, user.newsletters) &&
                Objects.equals(avatar, user.avatar);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + getId() + '\'' +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", newsletters=" + newsletters +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}