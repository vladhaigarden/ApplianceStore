package com.epam.preprod.tereshkevych.shop.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Search locale in browser
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainBrowserLocale extends ChainLocale {

    public ChainBrowserLocale(ChainLocale chainLocale) {
        super(chainLocale);
    }

    @Override
    public Locale searchLocale(HttpServletRequest request) {
        Locale locale = request.getLocales().nextElement();
        if (locale != null) {
            return locale;
        }
        ChainLocale nextSearcher = getSearcherLocale();
        return nextSearcher == null ? locale : nextSearcher.searchLocale(request);
    }
}