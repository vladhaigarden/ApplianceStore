package com.epam.preprod.tereshkevych.shop.security.holder;

import com.epam.preprod.tereshkevych.shop.security.captcha.Captcha;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Stores data captcha in context
 *
 * @author Vladyslav Tereshkevych
 */
public class StorageContext implements Storage {

    private static final String CAPTCHA = "captcha";

    private static final String CAPTCHA_ID = "captchaId";

    private Captcha captcha;

    private int captchaId;

    @Override
    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }

    @Override
    public void setCaptchaId(int captchaId) {
        this.captchaId = captchaId;
    }

    @Override
    public void store(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute(CAPTCHA, captcha);
        servletContext.setAttribute(CAPTCHA_ID, captchaId);
    }
}