package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.error.holder.FormErrorContainer;
import com.epam.preprod.tereshkevych.shop.error.validator.ChainValidator;
import com.epam.preprod.tereshkevych.shop.error.validator.login.ChainValidatorLoginUserBean;
import com.epam.preprod.tereshkevych.shop.error.validator.login.ChainValidatorUser;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import com.epam.preprod.tereshkevych.shop.web.dto.LoginUserDto;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.LoginUserDtoExtractor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet for user authorization
 *
 * @author Vladyslav Tereshkevych
 */

@WebServlet("/login")
public class LoginUser extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LoginUser.class);

    private static final String USER_SERVICE = "userService";

    private static final String LOGIN_USER_EXTRACTOR = "loginUserExtractor";

    private static final String ERROR_APP_EXCEPTION = "appError";

    private static final String ERROR_FIELD_LOG = "errorMessage --> ";

    private static final String SERVLET_HANDLER_ERROR = "error";

    private static final String ERROR_HOLDER = "holder";

    private static final String LOGIN_USER_DTO = "loginUserDto";

    private static final String SERVLET_LOGIN = "login";

    private static final String PAGE_LOGIN_USER = "/WEB-INF/jsp/user/login.jsp";

    private static final String DTO = "dto";

    private static final String START_PAGE = "http://localhost:8888/index.jsp";

    private static final String LAST_PAGE = "referer";

    private UserService userService;

    private LoginUserDtoExtractor userDtoExtractor;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        userDtoExtractor = (LoginUserDtoExtractor) getServletContext().getAttribute(LOGIN_USER_EXTRACTOR);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        LoginUserDto loginUserDto = userDtoExtractor.extract(httpServletRequest);
        HttpSession session = httpServletRequest.getSession();
        ChainValidator chainValidator = getChainValidators(loginUserDto);
        try {
            FormErrorContainer errorContainer = chainValidator.searchError(httpServletRequest);
            if (errorContainer.isEmpty()) {
                String pathReferer = (String) session.getAttribute(LAST_PAGE);
                session.removeAttribute(LAST_PAGE);
                String path = pathReferer != null ? pathReferer : START_PAGE;
                httpServletResponse.sendRedirect(path);
                return;
            }
            sendRedirectWithErrors(httpServletResponse, session, errorContainer, loginUserDto);
        } catch (DbException e) {
            handleError(session, httpServletResponse, e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        HttpSession session = httpServletRequest.getSession();
        httpServletRequest.setAttribute(ERROR_HOLDER, session.getAttribute(ERROR_HOLDER));
        httpServletRequest.setAttribute(DTO, session.getAttribute(DTO));
        session.removeAttribute(ERROR_HOLDER);
        session.removeAttribute(DTO);
        RequestDispatcher dispatcherLogin = httpServletRequest.getRequestDispatcher(PAGE_LOGIN_USER);
        dispatcherLogin.forward(httpServletRequest, httpServletResponse);
    }

    private void sendRedirectWithErrors(HttpServletResponse httpServletResponse, HttpSession session, FormErrorContainer errorContainer, LoginUserDto loginUserDto) throws IOException {
        session.setAttribute(ERROR_HOLDER, errorContainer);
        session.setAttribute(LOGIN_USER_DTO, loginUserDto);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.sendRedirect(SERVLET_LOGIN);
    }

    private ChainValidator getChainValidators(LoginUserDto loginUserDto) {
        ChainValidator validatorUser = new ChainValidatorUser(userService, loginUserDto);
        return new ChainValidatorLoginUserBean(validatorUser, loginUserDto);
    }

    private void handleError(HttpSession session, HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        session.setAttribute(ERROR_APP_EXCEPTION, errorMessage);
        LOG.error(ERROR_FIELD_LOG + errorMessage);
        httpServletResponse.sendRedirect(SERVLET_HANDLER_ERROR);
    }
}