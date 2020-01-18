package com.epam.preprod.tereshkevych.shop.db.dao.impl;

import com.epam.preprod.tereshkevych.shop.db.builder.QueryBuilder;
import com.epam.preprod.tereshkevych.shop.db.dao.AbstractMySqlDao;
import com.epam.preprod.tereshkevych.shop.db.dao.ProductDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Product;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import com.epam.preprod.tereshkevych.shop.web.dto.FilterProductDto;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlProductDao extends AbstractMySqlDao<Product> implements ProductDao {

    private static final Logger LOG = Logger.getLogger(MySqlProductDao.class);

    private static final String NAME_MAIN_TABLE = "products";
    private static final String NAME_TABLE_CATEGORIES = "categories";
    private static final String NAME_TABLE_MANUFACTURERS = "manufacturers";

    private static final String KEY_CATEGORY = "categories.id";
    private static final String KEY_PRODUCT_CATEGORY = "products.category_id";
    private static final String KEY_PRODUCT_MANUFACTURER = "products.manufacturer_id";

    private static final String KEY_MANUFACTURER = "manufacturers.id";

    private static final String PARAMETER_NAME = "products.name";
    private static final String PARAMETER_CATEGORY = "categories.name";
    private static final String PARAMETER_MANUFACTURER = "manufacturers.name";
    private static final String PARAMETER_PRICE_MORE = "price>";
    private static final String PARAMETER_PRICE_LESS = "price<";

    private static final String SQL_FIND_ALL_PRODUCTS = "SELECT * FROM products";
    private static final String SQL_ADD_PRODUCT = "INSERT INTO products VALUES (DEFAULT,?,?,?,?)";
    private static final String SQL_FIND_PRODUCT_BY_ID = "SELECT * FROM products WHERE id=?";
    private static final String SQL_UPDATE_PRODUCT_BY_ID = "UPDATE products SET name=?,price=?,category_id=?,manufacturer_id=? WHERE id=?";
    private static final String SQL_DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE id=?";

    private static final String ERROR_CANNOT_OBTAIN_PRODUCTS = "Cannot obtain products";

    private static final String ERROR_CANNOT_OBTAIN_AMOUNT = "Cannot obtain amount of products";

    private static final String KEY_CONNECTION = "con";

    private static final String COUNT = "count(*)";

    private static final int PARAMETER_INDEX_NAME = 1;
    private static final int PARAMETER_INDEX_PRICE = 2;
    private static final int PARAMETER_INDEX_CATEGORY = 3;
    private static final int PARAMETER_INDEX_MANUFACTURER = 4;
    private static final int PARAMETER_INDEX_ID = 5;

    public MySqlProductDao(DbExtractor<Product> productExtractor) {
        super(productExtractor);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_ADD_PRODUCT;
    }

    @Override
    protected String getSelectQuery() {
        return SQL_FIND_PRODUCT_BY_ID;
    }

    @Override
    protected String getAllSelectQuery() {
        return SQL_FIND_ALL_PRODUCTS;
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_PRODUCT_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return SQL_DELETE_PRODUCT_BY_ID;
    }

    @Override
    protected PreparedStatement setPrepareStatementToInsert(PreparedStatement statement, Product product) throws SQLException {
        statement.setString(PARAMETER_INDEX_NAME, product.getName());
        statement.setDouble(PARAMETER_INDEX_PRICE, product.getPrice());
        statement.setLong(PARAMETER_INDEX_CATEGORY, product.getCategoryId());
        statement.setLong(PARAMETER_INDEX_MANUFACTURER, product.getManufacturerId());
        return statement;
    }

    @Override
    protected PreparedStatement setPrepareStatementToUpdate(PreparedStatement statement, Product product) throws SQLException {
        setPrepareStatementToInsert(statement, product);
        statement.setLong(PARAMETER_INDEX_ID, product.getId());
        return statement;
    }

    @Override
    public List<Product> findProductsByFilter(Map<String, Object> containerConnection, FilterProductDto filterProductDto) throws DaoException {
        List<Product> products = new ArrayList<>();
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        PreparedStatement statement = null;
        try {
            statement = getStatementToFindProductsByFilter(filterProductDto, connection);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                products.add(parseResultSet(resultSet));
            }
            return products;
        } catch (SQLException ex) {
            LOG.warn(ERROR_CANNOT_OBTAIN_PRODUCTS, ex);
            throw new DaoException(ERROR_CANNOT_OBTAIN_PRODUCTS, ex);
        } finally {
            close(statement);
        }
    }

    @Override
    public int getAmountProducts(Map<String, Object> containerConnection, FilterProductDto filterProductDto) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        PreparedStatement statement = null;
        try {
            statement = getStatementToFindCountProductsByFilter(filterProductDto, connection);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(COUNT);
        } catch (SQLException ex) {
            LOG.warn(ERROR_CANNOT_OBTAIN_AMOUNT, ex);
            throw new DaoException(ERROR_CANNOT_OBTAIN_AMOUNT, ex);
        } finally {
            close(statement);
        }
    }

    private PreparedStatement getStatementToFindProductsByFilter(FilterProductDto filterProductDto, Connection connection) throws SQLException {
        return new QueryBuilder()
                .select()
                .from(NAME_MAIN_TABLE)
                .join(NAME_TABLE_CATEGORIES, KEY_PRODUCT_CATEGORY, KEY_CATEGORY)
                .join(NAME_TABLE_MANUFACTURERS, KEY_PRODUCT_MANUFACTURER, KEY_MANUFACTURER)
                .where(PARAMETER_NAME, filterProductDto.getName())
                .where(PARAMETER_CATEGORY, filterProductDto.getCategory())
                .where(PARAMETER_MANUFACTURER, filterProductDto.getManufacturer())
                .where(PARAMETER_PRICE_MORE, filterProductDto.getMinPrice())
                .where(PARAMETER_PRICE_LESS, filterProductDto.getMaxPrice())
                .orderBy(filterProductDto.getSorter())
                .limit(filterProductDto.getNumberItems(), filterProductDto.getNumberPage())
                .build(connection);
    }

    private PreparedStatement getStatementToFindCountProductsByFilter(FilterProductDto filterProductDto, Connection connection) throws SQLException {
        return new QueryBuilder()
                .selectCount()
                .from(NAME_MAIN_TABLE)
                .join(NAME_TABLE_CATEGORIES, KEY_PRODUCT_CATEGORY, KEY_CATEGORY)
                .join(NAME_TABLE_MANUFACTURERS, KEY_PRODUCT_MANUFACTURER, KEY_MANUFACTURER)
                .where(PARAMETER_NAME, filterProductDto.getName())
                .where(PARAMETER_CATEGORY, filterProductDto.getCategory())
                .where(PARAMETER_MANUFACTURER, filterProductDto.getManufacturer())
                .where(PARAMETER_PRICE_MORE, filterProductDto.getMinPrice())
                .where(PARAMETER_PRICE_LESS, filterProductDto.getMaxPrice())
                .build(connection);
    }

    private void close(Statement statement) throws DaoException {
        try {
            statement.close();
        } catch (SQLException e) {
            LOG.warn(ERROR_CANNOT_OBTAIN_PRODUCTS, e);
            throw new DaoException(ERROR_CANNOT_OBTAIN_PRODUCTS, e);
        }
    }
}