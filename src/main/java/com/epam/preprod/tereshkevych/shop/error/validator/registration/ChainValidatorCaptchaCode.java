package com.epam.preprod.tereshkevych.shop.error.validator.registration;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.security.captcha.Captcha;
import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Search errors which can appear because of entered wrong code from captcha.
 *
 * @author Vladyslav Tereshkevych
 */
public class ChainValidatorCaptchaCode extends ChainValidator {

    private static final String ERROR_USER_CODE_KEY = "error_user_code";

    private static final String ERR_VALIDATE_CODE = "Entered the incorrect code!";

    private static final String ERR_VALIDATE_TIME_CODE = "Time is up!";

    private static final String CAPTCHA_ID = "captchaId";

    private static final String CAPTCHA_CODE = "captchaCode";

    private CaptchaContainer captchaContainer;

    public ChainValidatorCaptchaCode(ChainValidator nextSearcher, CaptchaContainer captchaContainer) {
        super(nextSearcher);
        this.captchaContainer = captchaContainer;
    }

    @Override
    public FormErrorContainer searchError(HttpServletRequest request) throws DbException {
        FormErrorContainer formErrorContainer = new FormErrorContainer();
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute(CAPTCHA_ID);
        String enteredCode = request.getParameter(CAPTCHA_CODE);
        Captcha captcha = captchaContainer.getCaptchaById(id);
        if (captcha == null) {
            formErrorContainer.add(ERROR_USER_CODE_KEY, ERR_VALIDATE_TIME_CODE);
            return formErrorContainer;
        }
        if (!captcha.getGeneratedText().equals(enteredCode)) {
            formErrorContainer.add(ERROR_USER_CODE_KEY, ERR_VALIDATE_CODE);
            return formErrorContainer;
        }
        ChainValidator chainValidator = getSearcherError();
        return chainValidator == null ? formErrorContainer : chainValidator.searchError(request);
    }
}