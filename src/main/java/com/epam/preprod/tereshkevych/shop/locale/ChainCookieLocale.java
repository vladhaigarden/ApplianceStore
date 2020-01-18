package com.epam.preprod.tereshkevych.shop.locale;

import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Locale;

/**
 * Search locale in cookie
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainCookieLocale extends ChainLocale {

    public ChainCookieLocale(ChainLocale chainLocale) {
        super(chainLocale);
    }

    @Override
    public Locale searchLocale(HttpServletRequest request) {
        Locale locale = null;
        Cookie cookie = Arrays
                .stream(ObjectUtils.defaultIfNull(request.getCookies(), new Cookie[0]))
                .filter(c -> ATTRIBUTE_LOCAL.equals(c.getName()))
                .findFirst()
                .orElse(null);
        if (cookie != null) {
            return new Locale(cookie.getValue());
        }
        ChainLocale nextSearcher = getSearcherLocale();
        return nextSearcher == null ? locale : nextSearcher.searchLocale(request);
    }
}
