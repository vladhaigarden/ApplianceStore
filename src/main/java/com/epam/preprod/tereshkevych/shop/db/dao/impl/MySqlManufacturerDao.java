package com.epam.preprod.tereshkevych.shop.db.dao.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.AbstractMySqlDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ManufacturerDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Manufacturer;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;

import java.sql.PreparedStatement;

public class MySqlManufacturerDao extends AbstractMySqlDao<Manufacturer> implements ManufacturerDao {

    private static final String SQL_FIND_ALL_MANUFACTURERS = "SELECT * FROM manufacturers";

    private static final String OPERATION_ERROR = "Cannot execute method";

    public MySqlManufacturerDao(DbExtractor<Manufacturer> manufacturerExtractor) {
        super(manufacturerExtractor);
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
        return SQL_FIND_ALL_MANUFACTURERS;
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
    protected PreparedStatement setPrepareStatementToInsert(PreparedStatement ps, Manufacturer entity) {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }

    @Override
    protected PreparedStatement setPrepareStatementToUpdate(PreparedStatement ps, Manufacturer entity) {
        throw new UnsupportedOperationException(OPERATION_ERROR);
    }
}