package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.security.captcha.Captcha;
import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaContainer;
import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaStorage;
import com.epam.preprod.tereshkevych.shop.security.holder.Storage;
import com.epam.preprod.tereshkevych.shop.security.thread.DestroyerCaptcha;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Servlet for creating and storing new captcha
 *
 * @author Vladyslav Tereshkevych
 */

@WebServlet(name = "captcha", displayName = "captcha", urlPatterns = {"/captcha.png"})
public class GeneratorCaptcha extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GeneratorCaptcha.class);

    private static final long TIME_MS = 200000;

    private static final String CAPTCHA_CONTAINER = "captchaContainer";

    private static final String WAY_STORAGE = "wayStorage";

    private static final String PATH_IMAGE = "image/png";

    private static final String EXPANSION_IMAGE = "png";

    private static final String ERROR_WRITE_CAPTCHA = "Couldn't write captcha on web page";

    private CaptchaContainer captchaContainer;

    @Override
    public void init() {
        captchaContainer = (CaptchaContainer) getServletContext().getAttribute(CAPTCHA_CONTAINER);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Captcha captcha = new Captcha(TIME_MS);
        storeCaptcha(httpServletRequest, captcha);
        httpServletResponse.setContentType(PATH_IMAGE);
        try (OutputStream os = httpServletResponse.getOutputStream()) {
            BufferedImage image = getImage(captcha);
            ImageIO.write(image, EXPANSION_IMAGE, os);
        } catch (IOException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.warn(ERROR_WRITE_CAPTCHA, e);
        }
    }

    private void storeCaptcha(HttpServletRequest httpServletRequest, Captcha captcha) {
        Storage storage = (Storage) httpServletRequest.getServletContext().getAttribute(WAY_STORAGE);
        int captchaId = captchaContainer.addCaptcha(captcha);
        CaptchaStorage captchaStorage = new CaptchaStorage(storage, captcha, captchaId);
        captchaStorage.storeData(httpServletRequest);
        new DestroyerCaptcha(captchaContainer, captchaId, captcha.getLimitTime()).start();
    }

    private BufferedImage getImage(Captcha captcha) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(captcha.getGeneratedImage());
        return ImageIO.read(inputStream);
    }
}