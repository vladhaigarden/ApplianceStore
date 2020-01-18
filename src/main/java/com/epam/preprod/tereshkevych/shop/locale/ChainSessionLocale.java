package com.epam.preprod.tereshkevych.shop.locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Search locale in session
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainSessionLocale extends ChainLocale {

    public ChainSessionLocale(ChainLocale chainLocale) {
        super(chainLocale);
    }

    @Override
    public Locale searchLocale(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(ATTRIBUTE_LOCAL);
        if (locale != null) {

            return locale;
        }
        ChainLocale nextSearcher = getSearcherLocale();
        return nextSearcher == null ? locale : nextSearcher.searchLocale(request);
    }
}