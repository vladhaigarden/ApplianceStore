package com.epam.preprod.tereshkevych.shop.security.thread;

import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaContainer;
import org.apache.log4j.Logger;

public class DestroyerCaptcha extends Thread {

    private static final Logger LOG = Logger.getLogger(DestroyerCaptcha.class);

    private CaptchaContainer captchaContainer;

    private int captchaId;

    private long timeForRegistration;

    public DestroyerCaptcha(CaptchaContainer captchaContainer, int captchaId, long timeForRegistration) {
        this.captchaContainer = captchaContainer;
        this.captchaId = captchaId;
        this.timeForRegistration = timeForRegistration;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(timeForRegistration);
            captchaContainer.removeCaptchaById(captchaId);
        } catch (InterruptedException e) {
            LOG.error("Error thread", e);
        }
    }
}