package com.epam.preprod.tereshkevych.shop.db.dao.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.AbstractMySqlDao;
import com.epam.preprod.tereshkevych.shop.db.dao.CategoryDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Category;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlCategoryDao extends AbstractMySqlDao<Category> implements CategoryDao {

    private static final String SQL_FIND_ALL_CATEGORIES = "SELECT * FROM categories";

    private static final String OPERATION_ERROR = "Cannot execute method";

    public MySqlCategoryDao(DbExtractor<Category> categoryExtractor) {
        super(categoryExtractor);
    }

    @Override
    protected String getInsertQuery() {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected String getSelectQuery() {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected String getAllSelectQuery() {
        return SQL_FIND_ALL_CATEGORIES;
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected String getDeleteQuery() {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected PreparedStatement setPrepareStatementToInsert(PreparedStatement ps, Category entity) throws SQLException {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected PreparedStatement setPrepareStatementToUpdate(PreparedStatement ps, Category entity) throws SQLException {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }
}
