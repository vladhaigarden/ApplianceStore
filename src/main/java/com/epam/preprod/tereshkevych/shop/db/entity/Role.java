package com.epam.preprod.tereshkevych.shop.db.entity;

public enum Role {

    ADMIN("admin"),
    USER("user");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}