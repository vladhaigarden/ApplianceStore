package com.epam.preprod.tereshkevych.shop.db.extractor;

import com.epam.preprod.tereshkevych.shop.db.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create product with fields which was obtained from database
 *
 * @author Vladyslav Tereshkevych
 */
public class ProductDaoExtractor implements DbExtractor<Product> {

    private static final String ENTITY_ID = "id";
    private static final String ENTITY_CATEGORY_ID = "category_id";
    private static final String ENTITY_MANUFACTURER_ID = "manufacturer_id";

    private static final String PRODUCT_NAME = "name";
    private static final String PRODUCT_PRICE = "price";

    @Override
    public Product extract(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong(ENTITY_ID));
        product.setCategoryId(rs.getLong(ENTITY_CATEGORY_ID));
        product.setManufacturerId(rs.getLong(ENTITY_MANUFACTURER_ID));
        product.setName(rs.getString(PRODUCT_NAME));
        product.setPrice(rs.getDouble(PRODUCT_PRICE));
        return product;
    }
}