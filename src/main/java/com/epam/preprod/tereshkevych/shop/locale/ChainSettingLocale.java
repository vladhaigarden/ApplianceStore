package com.epam.preprod.tereshkevych.shop.locale;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Search setting locale
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainSettingLocale extends ChainLocale {

    protected static final String PARAMETER_LANGUAGE = "lang";

    public ChainSettingLocale(ChainLocale chainLocale) {
        super(chainLocale);
    }

    @Override
    public Locale searchLocale(HttpServletRequest request) {
        Locale locale = null;
        String selectedLanguage = request.getParameter(PARAMETER_LANGUAGE);
        if (selectedLanguage != null) {
            locale = new Locale(selectedLanguage);
            return locale;
        }
        ChainLocale nextSearcher = getSearcherLocale();
        return nextSearcher == null ? locale : nextSearcher.searchLocale(request);
    }
}