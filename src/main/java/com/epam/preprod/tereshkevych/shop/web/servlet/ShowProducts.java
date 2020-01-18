package com.epam.preprod.tereshkevych.shop.web.servlet;

import com.epam.preprod.tereshkevych.shop.db.entity.Category;
import com.epam.preprod.tereshkevych.shop.db.entity.Manufacturer;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import com.epam.preprod.tereshkevych.shop.service.ProductService;
import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;
import com.epam.preprod.tereshkevych.shop.web.dto.extractor.FilterProductDtoExtractor;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Servlet for showing all products from storage
 *
 * @author Vladyslav Tereshkevych
 */
@WebServlet("/products")
public class ShowProducts extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(ShowProducts.class);

    private static final String PAGE__PRODUCT_CATEGORIES = "/WEB-INF/jsp/product/product_category.jsp";

    private static final String SERVLET_HANDLER_ERROR = "error";

    private static final String ERROR_APP_EXCEPTION = "appError";

    private static final String ERROR_FIELD_LOG = "errorMessage --> ";

    private static final String PRODUCT_SERVICE = "productService";

    private static final String FILTER_PRODUCT_EXTRACTOR = "filterProductExtractor";

    private static final String WEB_CONSTANT_PRODUCTS = "products";

    private static final String WEB_CONSTANT_NUMBER_PRODUCTS = "numberProducts";

    private static final String WEB_CONSTANT_FILTER_BEAN = "filterBean";

    private static final String ATTRIBUTE_CATEGORIES = "categories";

    private static final String ATTRIBUTE_MANUFACTURERS = "manufacturers";

    private ProductService productService;

    private FilterProductDtoExtractor productDtoExtractor;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute(PRODUCT_SERVICE);
        productDtoExtractor = (FilterProductDtoExtractor) context.getAttribute(FILTER_PRODUCT_EXTRACTOR);
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        try {
            Map<Long, String> categories = getProductCategories();
            Map<Long, String> manufacturers = getProductManufacturers();
            httpServletRequest.setAttribute(ATTRIBUTE_CATEGORIES, categories);
            httpServletRequest.setAttribute(ATTRIBUTE_MANUFACTURERS, manufacturers);
            FilterProductDto filterProductDto = productDtoExtractor.extract(httpServletRequest, categories, manufacturers);
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher(PAGE__PRODUCT_CATEGORIES);
            List<Product> products = productService.getProductsByFilter(filterProductDto);
            int numberProducts = productService.getAmountProducts(filterProductDto);
            httpServletRequest.setAttribute(WEB_CONSTANT_NUMBER_PRODUCTS, numberProducts);
            httpServletRequest.setAttribute(WEB_CONSTANT_PRODUCTS, products);
            httpServletRequest.setAttribute(WEB_CONSTANT_FILTER_BEAN, filterProductDto);
            dispatcher.forward(httpServletRequest, httpServletResponse);
        } catch (DbException e) {
            handleError(httpServletRequest.getSession(), httpServletResponse, e.getMessage());
        }
    }

    private Map<Long, String> getProductCategories() throws DbException {
        List<Category> categories = productService.getAllCategories();
        return Objects.requireNonNull(categories).stream().collect(Collectors.toMap(Category::getId, Category::getName));
    }

    private Map<Long, String> getProductManufacturers() throws DbException {
        List<Manufacturer> manufacturers = productService.getAllManufacturers();
        return Objects.requireNonNull(manufacturers).stream().collect(Collectors.toMap(Manufacturer::getId, Manufacturer::getName));
    }

    private void handleError(HttpSession session, HttpServletResponse httpServletResponse, String errorMessage) throws IOException {
        session.setAttribute(ERROR_APP_EXCEPTION, errorMessage);
        LOG.error(ERROR_FIELD_LOG + errorMessage);
        httpServletResponse.sendRedirect(SERVLET_HANDLER_ERROR);
    }
}