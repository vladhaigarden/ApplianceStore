package com.epam.preprod.tereshkevych.shop.web.listener;

import com.epam.preprod.tereshkevych.shop.db.dao.CategoryDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ManufacturerDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ProductDao;
import com.epam.preprod.tereshkevych.shop.db.dao.impl.*;
import com.epam.preprod.tereshkevych.shop.db.extractor.*;
import com.epam.preprod.tereshkevych.shop.db.manager.ConnectionManager;
import com.epam.preprod.tereshkevych.shop.db.manager.TransactionManager;
import com.epam.preprod.tereshkevych.shop.security.captcha.CaptchaContainer;
import com.epam.preprod.tereshkevych.shop.security.holder.Storage;
import com.epam.preprod.tereshkevych.shop.service.OrderService;
import com.epam.preprod.tereshkevych.shop.service.ProductService;
import com.epam.preprod.tereshkevych.shop.service.UserService;
import com.epam.preprod.tereshkevych.shop.service.impl.OrderServiceImpl;
import com.epam.preprod.tereshkevych.shop.service.impl.ProductServiceImpl;
import com.epam.preprod.tereshkevych.shop.service.impl.UserServiceImpl;
import com.epam.preprod.tereshkevych.shop.util.ExtractorWayStorage;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.FilterProductDtoExtractor;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.FormUserDtoExtractor;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.LoginUserDtoExtractor;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    private static final Logger LOG = Logger.getLogger(ApplicationContextListener.class);

    private static final String CAPTCHA_CONTAINER = "captchaContainer";

    private static final String CAPTCHA_MODE_STORAGE = "modeStorage";

    private static final String WAY_STORAGE = "wayStorage";

    private static final String USER_SERVICE = "userService";

    private static final String PRODUCT_SERVICE = "productService";

    private static final String ORDER_SERVICE = "orderService";

    private static final String TYPE_SORTER = "typeSorter";

    private static final String KEY_NAME_ASC = "products.name";

    private static final String VALUE_NAME_ASC = "products.name";

    private static final String KEY_NAME_DESC = "products.name DESC";

    private static final String VALUE_NAME_DESC = "products.name DESC";

    private static final String KEY_PRICE_ASC = "price";

    private static final String VALUE_PRICE_ASC = "price";

    private static final String KEY_PRICE_DESC = "price DESC";

    private static final String VALUE_PRICE_DESC = "price DESC";

    private static final String ERROR_INIT_DATA_SOURCE = "Cannot init data source: ";

    private static final String FORM_USER_EXTRACTOR = "formUserExtractor";
    private static final String LOGIN_USER_EXTRACTOR = "loginUserExtractor";
    private static final String FILTER_PRODUCT_EXTRACTOR = "filterProductExtractor";

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        TransactionManager manager = new TransactionManager(new ConnectionManager(initDataSource()));
        UserService userService = new UserServiceImpl(new MySqlUserDao(new UserDaoExtractor()), manager);
        OrderService orderService = new OrderServiceImpl(new MySqlOrderDao(new OrderDaoExtractor()), manager);
        context.setAttribute(FORM_USER_EXTRACTOR, new FormUserDtoExtractor());
        context.setAttribute(LOGIN_USER_EXTRACTOR, new LoginUserDtoExtractor());
        context.setAttribute(FILTER_PRODUCT_EXTRACTOR, new FilterProductDtoExtractor());
        context.setAttribute(USER_SERVICE, userService);
        context.setAttribute(PRODUCT_SERVICE, getProductService(manager));
        context.setAttribute(ORDER_SERVICE, orderService);
        context.setAttribute(WAY_STORAGE, getWayStorage(context));
        context.setAttribute(CAPTCHA_CONTAINER, new CaptchaContainer());
        context.setAttribute(TYPE_SORTER, getTypeSorter());
    }

    private DataSource initDataSource() {
        DataSource dataSource = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/aroma_shop");
        } catch (NamingException e) {
            LOG.error(ERROR_INIT_DATA_SOURCE);
        }
        return dataSource;
    }

    private Storage getWayStorage(ServletContext context) {
        String nameModeStorage = context.getInitParameter(CAPTCHA_MODE_STORAGE);
        return ExtractorWayStorage.getWayStorage(nameModeStorage);
    }

    private Map<String, String> getTypeSorter() {
        Map<String, String> typeSorters = new HashMap<>();
        typeSorters.put(KEY_NAME_ASC, VALUE_NAME_ASC);
        typeSorters.put(KEY_NAME_DESC, VALUE_NAME_DESC);
        typeSorters.put(KEY_PRICE_ASC, VALUE_PRICE_ASC);
        typeSorters.put(KEY_PRICE_DESC, VALUE_PRICE_DESC);
        return typeSorters;
    }

    private ProductService getProductService(TransactionManager manager) {
        ProductDao productDao = new MySqlProductDao(new ProductDaoExtractor());
        CategoryDao categoryDao = new MySqlCategoryDao(new CategoryDaoExtractor());
        ManufacturerDao manufacturerDao = new MySqlManufacturerDao(new ManufacturerDaoExtractor());
        return new ProductServiceImpl(productDao, categoryDao, manufacturerDao, manager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}