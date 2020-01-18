package com.epam.preprod.tereshkevych.shop.security.holder;

import java.util.Arrays;

public enum ModeStorage {

    SESSION("session"),
    CONTEXT("context");

    private String value;

    ModeStorage(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ModeStorage getEnumByString(String code) {
        return Arrays.stream(ModeStorage.values()).
                filter(modeStorage -> modeStorage.value.equals(code)).
                findFirst().orElse(null);
    }

    @Override
    public String toString() {
        return "ModeStorage{" +
                "value='" + value + '\'' +
                '}';
    }
}