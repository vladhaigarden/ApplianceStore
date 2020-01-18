package com.epam.preprod.tereshkevych.shop.web.filter;

import com.epam.preprod.tereshkevych.shop.wrapper.CustomRequestWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Locale;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LocalizationFilterTest {

    private static final String ATTRIBUTE_LOCAL = "local";

    private static final String LOCALE_LANGUAGE = "en";
    private static final String DEFAULT_LOCALE_LANGUAGE = "ru";
    private static final String DEFAULT_LOCALE_COUNTRY = "RU";

    private static final String PARAMETER_LOCALE_STORAGE = "localeStorage";
    private static final String TYPE_STORAGE_LOCALE = "session";

    private static final String PARAMETER_COOKIE_TIME = "timeCookie";

    private static final String FIELD_WRAPPER = "requestWrapper";

    private static final String PARAMETER_LANGUAGE = "lang";

    private static final String TIME_COOKIE = "1800";

    @Mock
    private FilterConfig filterConfig;

    @Mock
    private ServletContext servletContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpSession session;

    private LocalizationFilter localizationFilter;

    @Before
    public void initFilterConfig() {
        when(servletContext.getInitParameter(PARAMETER_COOKIE_TIME)).thenReturn(TIME_COOKIE);
        when(filterConfig.getInitParameter(PARAMETER_LOCALE_STORAGE)).thenReturn(TYPE_STORAGE_LOCALE);
        when(filterConfig.getInitParameter(PARAMETER_LOCALE_STORAGE)).thenReturn(TYPE_STORAGE_LOCALE);
        when(filterConfig.getServletContext()).thenReturn(servletContext);
    }

    @Test
    public void shouldReturnExpectedSettingLocale() throws IOException, ServletException, NoSuchFieldException, IllegalAccessException {
        when(request.getParameter(PARAMETER_LANGUAGE)).thenReturn(LOCALE_LANGUAGE);
        when(request.getSession()).thenReturn(session);
        startFilter();
        CustomRequestWrapper requestWrapper = getRequestWrapper();
        Locale expectedLocale = new Locale(LOCALE_LANGUAGE);
        Assert.assertEquals(expectedLocale, requestWrapper.getLocale());
    }

    @Test
    public void shouldReturnExpectedLocaleFromSession() throws IOException, ServletException, NoSuchFieldException, IllegalAccessException {
        when(request.getParameter(PARAMETER_LANGUAGE)).thenReturn(null);
        when(session.getAttribute(ATTRIBUTE_LOCAL)).thenReturn(new Locale(LOCALE_LANGUAGE));
        when(request.getSession()).thenReturn(session);
        startFilter();
        CustomRequestWrapper requestWrapper = getRequestWrapper();
        Locale expectedLocale = new Locale(LOCALE_LANGUAGE);
        Assert.assertEquals(expectedLocale, requestWrapper.getLocale());
    }

    @Test
    public void shouldReturnExpectedLocaleFromCookie() throws IOException, ServletException, NoSuchFieldException, IllegalAccessException {
        when(request.getParameter(PARAMETER_LANGUAGE)).thenReturn(null);
        when(session.getAttribute(ATTRIBUTE_LOCAL)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        Cookie[] cookies = {new Cookie(ATTRIBUTE_LOCAL, LOCALE_LANGUAGE)};
        when(request.getCookies()).thenReturn(cookies);
        startFilter();
        CustomRequestWrapper requestWrapper = getRequestWrapper();
        Locale expectedLocale = new Locale(LOCALE_LANGUAGE);
        Assert.assertEquals(expectedLocale, requestWrapper.getLocale());
    }

    @Test
    public void shouldReturnExpectedLocaleFromBrowser() throws IOException, ServletException, NoSuchFieldException, IllegalAccessException {
        when(request.getParameter(PARAMETER_LANGUAGE)).thenReturn(null);
        when(session.getAttribute(ATTRIBUTE_LOCAL)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        Cookie[] cookies = new Cookie[0];
        when(request.getCookies()).thenReturn(cookies);
        Locale locale = new Locale(LOCALE_LANGUAGE);
        Enumeration customEnumeration = new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public Locale nextElement() {
                return locale;
            }
        };
        when(request.getLocales()).thenReturn(customEnumeration);
        startFilter();
        CustomRequestWrapper requestWrapper = getRequestWrapper();
        Assert.assertEquals(locale, requestWrapper.getLocale());
    }

    @Test
    public void shouldReturnExpectedDefaultLocale() throws IOException, ServletException, NoSuchFieldException, IllegalAccessException {
        when(request.getParameter(PARAMETER_LANGUAGE)).thenReturn(null);
        when(session.getAttribute(ATTRIBUTE_LOCAL)).thenReturn(null);
        when(request.getSession()).thenReturn(session);
        Cookie[] cookies = new Cookie[0];
        when(request.getCookies()).thenReturn(cookies);
        Enumeration customEnumeration = new Enumeration<Locale>() {
            @Override
            public boolean hasMoreElements() {
                return false;
            }

            @Override
            public Locale nextElement() {
                return null;
            }
        };
        when(request.getLocales()).thenReturn(customEnumeration);
        startFilter();
        CustomRequestWrapper requestWrapper = getRequestWrapper();
        Locale expectedLocale = new Locale(DEFAULT_LOCALE_LANGUAGE, DEFAULT_LOCALE_COUNTRY);
        Assert.assertEquals(expectedLocale, requestWrapper.getLocale());
    }

    private void startFilter() throws IOException, ServletException {
        localizationFilter = new LocalizationFilter();
        localizationFilter.init(filterConfig);
        localizationFilter.doFilter(request, response, filterChain);
    }

    private CustomRequestWrapper getRequestWrapper() throws NoSuchFieldException, IllegalAccessException {
        Field fieldWrapper = localizationFilter.getClass().getDeclaredField(FIELD_WRAPPER);
        fieldWrapper.setAccessible(true);
        return (CustomRequestWrapper) fieldWrapper.get(localizationFilter);
    }
}