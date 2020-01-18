package com.epam.preprod.tereshkevych.shop.security.holder;

import com.epam.preprod.tereshkevych.shop.security.captcha.Captcha;

import javax.servlet.http.HttpServletRequest;

public interface Storage {

    void setCaptcha(Captcha captcha);

    void setCaptchaId(int captchaId);

    void store(HttpServletRequest request);
}