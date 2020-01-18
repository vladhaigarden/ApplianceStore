package com.epam.preprod.tereshkevych.shop.web.filter;

import com.epam.preprod.tereshkevych.shop.locale.ChainBrowserLocale;
import com.epam.preprod.tereshkevych.shop.locale.ChainCookieLocale;
import com.epam.preprod.tereshkevych.shop.locale.ChainDefaultLocale;
import com.epam.preprod.tereshkevych.shop.locale.ChainLocale;
import com.epam.preprod.tereshkevych.shop.locale.ChainSessionLocale;
import com.epam.preprod.tereshkevych.shop.locale.ChainSettingLocale;
import com.epam.preprod.tereshkevych.shop.wrapper.CustomRequestWrapper;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class LocalizationFilter implements Filter {

    private static final String SET_LOCALE = "javax.servlet.jsp.jstl.fmt.locale";

    private static final String ATTRIBUTE_LOCAL = "local";

    private static final String PARAMETER_LOCALE_STORAGE = "localeStorage";

    private static final String PARAMETER_COOKIE_TIME = "timeCookie";

    private static final String STORAGE_IN_SESSION = "session";

    private static final String STORAGE_IN_COOKIE = "cookie";

    private String typeStorage;

    private int timeCookie;

    private CustomRequestWrapper requestWrapper;

    private ChainLocale chainLocale;

    @Override
    public void init(FilterConfig filterConfig) {
        typeStorage = filterConfig.getInitParameter(PARAMETER_LOCALE_STORAGE);
        timeCookie = Integer.parseInt(filterConfig.getServletContext().getInitParameter(PARAMETER_COOKIE_TIME));
        ChainDefaultLocale defaultLocale = new ChainDefaultLocale();
        ChainBrowserLocale browserLocale = new ChainBrowserLocale(defaultLocale);
        ChainCookieLocale chainCookieLocale = new ChainCookieLocale(browserLocale);
        ChainSessionLocale chainSessionLocale = new ChainSessionLocale(chainCookieLocale);
        this.chainLocale = new ChainSettingLocale(chainSessionLocale);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        requestWrapper = new CustomRequestWrapper(request);
        Locale locale = chainLocale.searchLocale(request);
        requestWrapper.setLocale(locale);
        storageLocale(request, response, locale);
        Config.set(request.getSession(), SET_LOCALE, locale);
        filterChain.doFilter(requestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
    }

    private void storageLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        if (STORAGE_IN_SESSION.equals(typeStorage)) {
            request.getSession().setAttribute(ATTRIBUTE_LOCAL, locale);
            return;
        }
        if (STORAGE_IN_COOKIE.equals(typeStorage)) {
            storageLocaleInCookie(request, response, locale);
        }
    }

    private void storageLocaleInCookie(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        Cookie cookie = Arrays
                .stream(ObjectUtils.defaultIfNull(request.getCookies(), new Cookie[0]))
                .filter(c -> ATTRIBUTE_LOCAL.equals(c.getName()))
                .findFirst()
                .orElse(null);
        String language = locale.getLanguage();
        if (cookie == null) {
            cookie = new Cookie(ATTRIBUTE_LOCAL, language);
        } else {
            cookie.setValue(language);
        }
        cookie.setMaxAge(timeCookie);
        response.addCookie(cookie);
    }
}