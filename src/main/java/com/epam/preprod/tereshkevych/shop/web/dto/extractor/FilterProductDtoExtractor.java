package com.epam.preprod.tereshkevych.shop.web.dto.extractor;

import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;
import org.apache.commons.lang3.math.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Create dto for filtration products
 *
 * @author Vladyslav Tereshkevych
 */
public class FilterProductDtoExtractor {

    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_MIN_PRICE = "minPrice";
    private static final String PRODUCT_MAX_PRICE = "maxPrice";
    private static final String PRODUCT_CATEGORY = "category";
    private static final String PRODUCT_MANUFACTURER = "manufacturer";
    private static final String PRODUCT_SORTER = "sorter";
    private static final String TYPE_SORTER = "typeSorter";
    private static final String NUMBER_ITEMS = "numberItems";
    private static final String NUMBER_PAGE = "numberPage";

    private static final Integer DEFAULT_NUMBER_ITEMS = 30;

    private static final Integer DEFAULT_NUMBER_PAGE = 1;

    public FilterProductDto extract(HttpServletRequest request, Map<Long, String> categories, Map<Long, String> manufacturers) {
        FilterProductDto filterProductDto = new FilterProductDto();
        filterProductDto.setName(request.getParameter(PRODUCT_NAME));
        filterProductDto.setMinPrice(getNumberParameterAfterValidation(request.getParameter(PRODUCT_MIN_PRICE)));
        filterProductDto.setMaxPrice(getNumberParameterAfterValidation(request.getParameter(PRODUCT_MAX_PRICE)));
        filterProductDto.setCategory(getNameCategoryById(request, categories));
        filterProductDto.setManufacturer(getNameManufacturerById(request, manufacturers));
        filterProductDto.setSorter(getTypeSorter(request));
        filterProductDto.setNumberItems(getNumberItems(request));
        filterProductDto.setNumberPage(getNumberPage(request));
        return filterProductDto;
    }

    private String getNumberParameterAfterValidation(String parameter) {
        return NumberUtils.isNumber(parameter) ? parameter : null;
    }

    private String getNameCategoryById(HttpServletRequest request, Map<Long, String> categories) {
        String strKey = request.getParameter(PRODUCT_CATEGORY);
        return NumberUtils.isNumber(strKey) ? categories.get(Long.parseLong(strKey)) : null;
    }

    private String getNameManufacturerById(HttpServletRequest request, Map<Long, String> manufacturers) {
        String strKey = request.getParameter(PRODUCT_MANUFACTURER);
        return NumberUtils.isNumber(strKey) ? manufacturers.get(Long.parseLong(strKey)) : null;
    }

    private String getTypeSorter(HttpServletRequest request) {
        Map<String, String> typeSorters = (Map<String, String>) request.getServletContext().getAttribute(TYPE_SORTER);
        return typeSorters.get(request.getParameter(PRODUCT_SORTER));
    }

    private Integer getNumberItems(HttpServletRequest request) {
        String numberItems = request.getParameter(NUMBER_ITEMS);
        return NumberUtils.isNumber(numberItems) ? Integer.parseInt(numberItems) : DEFAULT_NUMBER_ITEMS;
    }

    private Integer getNumberPage(HttpServletRequest request) {
        String numberPage = request.getParameter(NUMBER_PAGE);
        return NumberUtils.isNumber(numberPage) ? Integer.parseInt(numberPage) : DEFAULT_NUMBER_PAGE;
    }
}