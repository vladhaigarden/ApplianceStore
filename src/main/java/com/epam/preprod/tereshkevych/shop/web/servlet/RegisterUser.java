package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.converter.FormUserDtoToUserConverter;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.error.validator.registration.ChainValidatorCaptchaCode;
import com.epam.preprod.tereshkevych.shop.error.validator.registration.ChainValidatorFormUserBean;
import com.epam.preprod.tereshkevych.shop.error.validator.registration.ChainValidatorImage;
import com.epam.preprod.tereshkevych.shop.error.validator.registration.ChainValidatorUniqueLogin;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaContainer;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import com.epam.preprod.tereshkevych.shop.web.dto.FormUserDto;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.FormUserDtoExtractor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

/**
 * Servlet for registration new user
 *
 * @author Vladyslav Tereshkevych
 */

@MultipartConfig()
@WebServlet("/register")
public class RegisterUser extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(RegisterUser.class);

    private static final String PAGE__REGISTRATION_USER = "/WEB-INF/jsp/user/registration.jsp";

    private static final String SERVLET_REGISTER = "register";

    private static final String SERVLET_ACCOUNT = "account";

    private static final String SERVLET_HANDLER_ERROR = "error";

    private static final String ERROR_HOLDER = "holder";

    private static final String DTO = "dto";

    private static final String USER = "user";

    private static final String CAPTCHA_CONTAINER = "captchaContainer";

    private static final String USER_SERVICE = "userService";

    private static final String FORM_USER_EXTRACTOR = "formUserExtractor";

    private static final String ERROR_APP_EXCEPTION = "appError";

    private static final String ERROR_FIELD_LOG = "errorMessage --> ";

    private static final String FILE_PARAMETER = "file";

    private static final String SLASH = "/";

    private static final String DOT = ".";

    private static final String PATH_AVATARS = "avatars";

    private UserService userService;

    private CaptchaContainer captchaContainer;

    private FormUserDtoExtractor userDtoExtractor;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(USER_SERVICE);
        captchaContainer = (CaptchaContainer) context.getAttribute(CAPTCHA_CONTAINER);
        userDtoExtractor = (FormUserDtoExtractor) context.getAttribute(FORM_USER_EXTRACTOR);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        HttpSession session = httpServletRequest.getSession();
        FormUserDto formUserDto = userDtoExtractor.extract(httpServletRequest);
        ChainValidator chainValidator = getChainValidators(formUserDto);
        try {
            FormErrorContainer formErrorContainer = chainValidator.searchError(httpServletRequest);
            if (formErrorContainer.isEmpty()) {
                User newUser = FormUserDtoToUserConverter.convert(formUserDto);
                loadAvatar(httpServletRequest.getPart(FILE_PARAMETER), newUser);
                userService.addUser(newUser);
                session.setAttribute(USER, newUser);
                httpServletResponse.sendRedirect((SERVLET_ACCOUNT));
                return;
            }
            sendRedirectWithErrors(httpServletResponse, session, formErrorContainer, formUserDto);
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.setAttribute(ERROR_HOLDER, session.getAttribute(ERROR_HOLDER));
        httpServletRequest.setAttribute(DTO, session.getAttribute(DTO));
        session.removeAttribute(ERROR_HOLDER);
        session.removeAttribute(DTO);
        RequestDispatcher dispatcherRegistrationPage = httpServletRequest.getRequestDispatcher(PAGE__REGISTRATION_USER);
        dispatcherRegistrationPage.forward(httpServletRequest, httpServletResponse);
    }

    private void sendRedirectWithErrors(HttpServletResponse httpServletResponse, HttpSession session, FormErrorContainer formErrorContainer, FormUserDto formUserDto) throws IOException {
        session.setAttribute(ERROR_HOLDER, formErrorContainer);
        session.setAttribute(DTO, formUserDto);
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        httpServletResponse.sendRedirect(SERVLET_REGISTER);
    }

    private ChainValidator getChainValidators(FormUserDto formUserDto) {
        ChainValidator validatorImage = new ChainValidatorImage();
        ChainValidator validatorUniqueLogin = new ChainValidatorUniqueLogin(validatorImage, userService, formUserDto);
        ChainValidator validatorRegistrationField = new ChainValidatorFormUserBean(validatorUniqueLogin, formUserDto);
        return new ChainValidatorCaptchaCode(validatorRegistrationField, captchaContainer);
    }

    private void loadAvatar(Part part, User user) throws IOException {
        String contentType = part.getContentType();
        String expansion = contentType.substring(contentType.lastIndexOf(SLASH) + 1);
        String nameImage = user.getLogin() + DOT + expansion;
        user.setAvatar(nameImage);
        File newImage = new File(PATH_AVATARS, nameImage);
        try (InputStream fileContent = part.getInputStream();
             OutputStream outStream = new FileOutputStream(newImage)) {
            byte[] buffer = new byte[fileContent.available()];
            fileContent.read(buffer);
            outStream.write(buffer);
        }
    }

    private void handleError(HttpSession session, HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        session.setAttribute(ERROR_APP_EXCEPTION, errorMessage);
        LOG.error(ERROR_FIELD_LOG + errorMessage);
        httpServletResponse.sendRedirect(SERVLET_HANDLER_ERROR);
    }
}