package com.epam.preprod.tereshkevych.shop.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Search default locale
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainDefaultLocale extends ChainLocale {

    @Override
    public Locale searchLocale(HttpServletRequest request) {
        return Locale.getDefault();
    }
}