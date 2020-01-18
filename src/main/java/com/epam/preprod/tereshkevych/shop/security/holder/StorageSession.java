package com.epam.preprod.tereshkevych.shop.security.holder;

import com.epam.preprod.tereshkevych.shop.security.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Stores data captcha in session
 *
 * @author Vladyslav Tereshkevych
 */
public class StorageSession implements Storage {

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
        HttpSession session = request.getSession();
        session.setAttribute(CAPTCHA, captcha);
        session.setAttribute(CAPTCHA_ID, captchaId);
    }
}