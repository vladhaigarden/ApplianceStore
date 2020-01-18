package com.epam.preprod.tereshkevych.shop.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Locale;

/**
 * Custom wrapper for locale substitution
 *
 * @author Vladyslav Tereshkevych
 */
public class CustomRequestWrapper extends HttpServletRequestWrapper {

    private Locale locale;

    public CustomRequestWrapper(HttpServletRequest httpServletRequest) {
        super(httpServletRequest);
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}