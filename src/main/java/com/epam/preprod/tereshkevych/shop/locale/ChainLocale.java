package com.epam.preprod.tereshkevych.shop.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Search locale
 *
 * @author Vladyslav Tereshkevych
 */
public abstract class ChainLocale {

    protected static final String ATTRIBUTE_LOCAL = "local";

    private ChainLocale nextSearcher;

    public ChainLocale() {
    }

    public ChainLocale(ChainLocale nextSearcher) {
        this.nextSearcher = nextSearcher;
    }

    public abstract Locale searchLocale(HttpServletRequest request);

    public ChainLocale getSearcherLocale() {
        return nextSearcher;
    }
}