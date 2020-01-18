package com.epam.preprod.tereshkevych.shop.security.captcha;

import com.epam.preprod.tereshkevych.shop.util.CaptchaGenerator;

import java.util.Arrays;

public class Captcha {

    private String generatedText;

    private byte[] generatedImage;

    private long limitTime;

    public Captcha(long limitTime) {
        this.limitTime = limitTime;
        this.generatedText = CaptchaGenerator.generateText();
        this.generatedImage = org.javalite.activeweb.Captcha.generateImage(generatedText);
    }

    public String getGeneratedText() {
        return generatedText;
    }

    public byte[] getGeneratedImage() {
        return generatedImage;
    }

    public long getLimitTime() {
        return limitTime;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "generatedText='" + generatedText + '\'' +
                ", generatedImage=" + Arrays.toString(generatedImage) +
                '}';
    }
}