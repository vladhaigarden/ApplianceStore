package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.db.entity.User;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@WebServlet(name = "avatar", displayName = "avatar", urlPatterns = {"/avatar.png"})
public class GeneratorAvatar extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GeneratorAvatar.class);

    private static final String PATH_AVATARS = "avatars/";

    private static final String PATH_DEFAULT_AVATAR = "avatars/default.png";

    private static final String USER = "user";

    private static final String PATH_AVATAR = "avatar/png";

    private static final String ERROR_WRITE_AVATAR = "Couldn't write avatar on web page";

    private static final String EXPANSION_IMAGE = "png";

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        User user = (User) httpServletRequest.getSession().getAttribute(USER);
        try (OutputStream os = httpServletResponse.getOutputStream()) {
            httpServletResponse.setContentType(PATH_AVATAR);
            BufferedImage image = getImage(user);
            ImageIO.write(image, EXPANSION_IMAGE, os);
        } catch (IOException e) {
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            LOG.warn(ERROR_WRITE_AVATAR, e);
        }
    }

    private BufferedImage getImage(User user) throws IOException {
        String userAvatar = user.getAvatar();
        File file = new File(PATH_AVATARS + userAvatar);
        if (!file.exists() || file.isDirectory()) {
            file = new File(PATH_DEFAULT_AVATAR);
        }
        byte[] byteAvatar = Files.readAllBytes(file.toPath());
        ByteArrayInputStream inputStream = new ByteArrayInputStream(byteAvatar);
        return ImageIO.read(inputStream);
    }
}