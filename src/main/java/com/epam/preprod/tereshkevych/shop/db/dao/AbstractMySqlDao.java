package com.epam.preprod.tereshkevych.shop.db.dao;

import com.epam.preprod.tereshkevych.shop.db.entity.Entity;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AbstractMySqlDao<T extends Entity> implements GenericDao<T> {

    private static final Logger LOG = Logger.getLogger(AbstractMySqlDao.class);

    private static final String KEY_CONNECTION = "con";

    private static final String ERROR_OBTAIN = "Cannot obtain entity";

    private static final String ERROR_OBTAIN_ALL = "Cannot obtain all entities";

    private static final String ERROR_FORMAT_MESSAGE = "Cannot create an entity %s";

    private DbExtractor<T> extractor;

    public AbstractMySqlDao(DbExtractor<T> extractor) {
        this.extractor = extractor;
    }

    protected abstract String getInsertQuery();

    protected abstract String getSelectQuery();

    protected abstract String getAllSelectQuery();

    protected abstract String getUpdateQuery();

    protected abstract String getDeleteQuery();

    protected abstract PreparedStatement setPrepareStatementToInsert(PreparedStatement ps, T entity) throws SQLException;

    protected abstract PreparedStatement setPrepareStatementToUpdate(PreparedStatement ps, T entity) throws SQLException;

    protected T parseResultSet(ResultSet rs) throws SQLException {
        return extractor.extract(rs);
    }

    @Override
    public T create(T entity, Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        String sqlQuery = getInsertQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            setPrepareStatementToInsert(preparedStatement, entity);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Long id = null;
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            entity.setId(id);
            return entity;
        } catch (SQLException e) {
            String errorMessage = String.format(ERROR_FORMAT_MESSAGE, entity);
            LOG.error(errorMessage, e);
            throw new DaoException(errorMessage, e);
        }
    }

    @Override
    public T find(String name, Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        String sqlQuery = getSelectQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            T result = null;
            if (resultSet.next()) {
                result = extractor.extract(resultSet);
            }
            return result;
        } catch (SQLException e) {
            LOG.error(ERROR_OBTAIN, e);
            throw new DaoException(ERROR_OBTAIN, e);
        }
    }

    @Override
    public List<T> findAll(Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        try (Statement statement = connection.createStatement()) {
            List<T> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery(getAllSelectQuery());
            while (resultSet.next()) {
                result.add(extractor.extract(resultSet));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(ERROR_OBTAIN_ALL, e);
            throw new DaoException(ERROR_OBTAIN_ALL, e);
        }
    }

    @Override
    public boolean update(T entity, Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        String sql = getUpdateQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setPrepareStatementToUpdate(preparedStatement, entity);
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            String errorMessage = String.format(ERROR_FORMAT_MESSAGE, entity);
            LOG.error(errorMessage, e);
            throw new DaoException(errorMessage, e);
        }
    }

    @Override
    public boolean delete(T entity, Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        String sqlQuery = getDeleteQuery();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setLong(1, entity.getId());
            int rows = preparedStatement.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            String errorMessage = String.format(ERROR_FORMAT_MESSAGE, entity);
            LOG.error(errorMessage, e);
            throw new DaoException(errorMessage, e);
        }
    }
}