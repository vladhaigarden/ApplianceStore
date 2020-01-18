package com.epam.preprod.tereshkevych.shop.db.dao.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.AbstractMySqlDao;
import com.epam.preprod.tereshkevych.shop.db.dao.OrderDao;
import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.db.entity.OrderHistory;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Map;

public class MySqlOrderDao extends AbstractMySqlDao<Order> implements OrderDao {

    private static final Logger LOG = Logger.getLogger(MySqlOrderDao.class);

    private static final String KEY_CONNECTION = "con";

    private static final String SQL_ADD_ORDER = "INSERT INTO orders (user_id) VALUES (?)";

    private static final String SQL_FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id=?";
    private static final String SQL__FIND_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_UPDATE_ORDER_BY_ID = "UPDATE orders SET id=?,user_id=?,status=?,state_detail=?,date_time=? WHERE id=?";
    private static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id=?";

    private static final String SQL_INSERT_ORDER_HISTORY = "INSERT INTO orders_products VALUES (?,?,?,?)";

    private static final int PARAMETER_INDEX_ORDER_ID = 1;
    private static final int PARAMETER_INDEX_PRODUCT_ID = 2;
    private static final int PARAMETER_INDEX_QUANTITY_PRODUCT = 3;
    private static final int PARAMETER_INDEX_PRICE_PRODUCT = 4;

    private static final int PARAMETER_INDEX_USER_ID = 1;
    private static final int PARAMETER_INDEX_ORDER_STATUS = 2;
    private static final int PARAMETER_INDEX_STATE_DETAIL = 3;
    private static final int PARAMETER_INDEX_DATE_TIME = 4;
    private static final int PARAMETER_INDEX_ORDER_ID_FOR_UPDATE = 5;

    private static final String ERROR_CREATE = "Error create order";

    public MySqlOrderDao(DbExtractor<Order> orderExtractor) {
        super(orderExtractor);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_ADD_ORDER;
    }

    @Override
    protected String getSelectQuery() {
        return SQL_FIND_ORDER_BY_ID;
    }

    @Override
    protected String getAllSelectQuery() {
        return SQL__FIND_ALL_ORDERS;
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_ORDER_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return SQL_DELETE_ORDER_BY_ID;
    }

    @Override
    protected PreparedStatement setPrepareStatementToInsert(PreparedStatement statement, Order order) throws SQLException {
        statement.setLong(PARAMETER_INDEX_ORDER_ID, order.getUserId());
        return statement;
    }

    @Override
    protected PreparedStatement setPrepareStatementToUpdate(PreparedStatement statement, Order order) throws SQLException {
        statement.setLong(PARAMETER_INDEX_USER_ID, order.getUserId());
        statement.setString(PARAMETER_INDEX_ORDER_STATUS, order.getStatus().value());
        statement.setString(PARAMETER_INDEX_STATE_DETAIL, order.getStateDetail());
        statement.setTimestamp(PARAMETER_INDEX_DATE_TIME, order.getDateTime());
        statement.setLong(PARAMETER_INDEX_ORDER_ID_FOR_UPDATE, order.getId());
        return statement;
    }

    @Override
    public Order addOrder(Order order, Map<String, Object> containerConnection) throws DaoException {
        create(order, containerConnection);
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_ORDER_HISTORY, Statement.RETURN_GENERATED_KEYS)) {
            for (OrderHistory orderHistory : order.getOrderHistory()) {
                setPrepareStatementToAddOrderHistory(preparedStatement, order, orderHistory);
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
            }
            return order;
        } catch (SQLException e) {
            LOG.error(ERROR_CREATE, e);
            throw new DaoException(ERROR_CREATE, e);
        }
    }

    private PreparedStatement setPrepareStatementToAddOrderHistory(PreparedStatement statement, Order order, OrderHistory history) throws SQLException {
        statement.setLong(PARAMETER_INDEX_ORDER_ID, order.getId());
        statement.setLong(PARAMETER_INDEX_PRODUCT_ID, history.getProductId());
        statement.setInt(PARAMETER_INDEX_QUANTITY_PRODUCT, history.getQuantityProduct());
        statement.setDouble(PARAMETER_INDEX_PRICE_PRODUCT, history.getCurrentPrice());
        return statement;
    }
}