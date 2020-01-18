package com.epam.preprod.tereshkevych.shop.error.holder;

import java.util.HashMap;
import java.util.Map;

/**
 * Container of errors
 *
 * @author Vladyslav Tereshkevych
 */
public class FormErrorContainer {

    private Map<String, String> errors = new HashMap<>();

    public boolean isEmpty() {
        return errors.isEmpty();
    }

    public void add(String errorKey, String errorMessage) {
        errors.put(errorKey, errorMessage);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}