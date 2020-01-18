package com.epam.preprod.tereshkevych.shop.db.extractor;

import com.epam.preprod.tereshkevych.shop.db.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create category with fields which was obtained from database
 *
 * @author Vladyslav Tereshkevych
 */
public class CategoryDaoExtractor implements DbExtractor<Category> {

    private static final String ENTITY_ID = "id";

    private static final String CATEGORY_NAME = "name";

    @Override
    public Category extract(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getLong(ENTITY_ID));
        category.setName(rs.getString(CATEGORY_NAME));
        return category;
    }
}