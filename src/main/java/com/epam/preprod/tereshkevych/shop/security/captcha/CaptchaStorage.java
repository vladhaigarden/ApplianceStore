package com.epam.preprod.tereshkevych.shop.security.captcha;

import com.epam.preprod.tereshkevych.shop.security.holder.Storage;

import javax.servlet.http.HttpServletRequest;

/**
 * Stores data captcha using obtained way
 *
 * @author Vladyslav Tereshkevych
 */
public class CaptchaStorage {

    private Storage storage;

    private Captcha captcha;

    private int captchaId;

    public CaptchaStorage(Storage storage, Captcha captcha, int captchaId) {
        this.storage = storage;
        this.captcha = captcha;
        this.captchaId = captchaId;
    }

    public void storeData(HttpServletRequest httpServletRequest) {
        storage.setCaptcha(captcha);
        storage.setCaptchaId(captchaId);
        storage.store(httpServletRequest);
    }
}