package com.epam.preprod.tereshkevych.shop.security.captcha;

import java.util.HashMap;
import java.util.Map;

public class CaptchaContainer {

    private Map<Integer, Captcha> captchaMap = new HashMap<>();

    public int addCaptcha(Captcha captcha) {
        int captchaId = captchaMap.size();
        captchaMap.put(captchaId, captcha);
        return captchaId;
    }

    public void removeCaptchaById(int id) {
        captchaMap.remove(id);
    }

    public Captcha getCaptchaById(int id) {
        return captchaMap.get(id);
    }
}