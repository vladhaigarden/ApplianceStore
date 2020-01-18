package com.epam.preprod.tereshkevych.shop.db.manager;

import com.epam.preprod.tereshkevych.shop.db.transaction.Operation;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import com.epam.preprod.tereshkevych.shop.exception.DbException;
import org.apache.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Calls realization of operation with transaction
 *
 * @author Vladyslav Tereshkevych
 */
public class TransactionManager {

    private static final Logger LOG = Logger.getLogger(TransactionManager.class);

    private static final String ERROR_DB_MANAGER = "Exception execute TransactionManager";

    private static final String KEY_CONNECTION = "con";

    private ConnectionManager connectionManager;

    public TransactionManager(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public <E> E execute(Operation<E> operation) throws DbException {
        try (Connection connection = connectionManager.getConnection()) {
            return executeOperation(operation, connection);
        } catch (DaoException e) {
            String errorMessage = e.getMessage();
            LOG.error(errorMessage, e);
            throw new DbException(errorMessage, e);
        } catch (SQLException e) {
            LOG.error(ERROR_DB_MANAGER, e);
            throw new DbException(ERROR_DB_MANAGER, e);
        }
    }

    private <E> E executeOperation(Operation<E> operation, Connection connection) throws DaoException, SQLException {
        try {
            Map<String, Object> containerConnection = new HashMap<>();
            containerConnection.put(KEY_CONNECTION, connection);
            E result = operation.execute(containerConnection);
            connection.commit();
            return result;
        } catch (DaoException | NoSuchAlgorithmException | SQLException e) {
            connection.rollback();
            throw new DaoException(ERROR_DB_MANAGER, e);
        }
    }
}