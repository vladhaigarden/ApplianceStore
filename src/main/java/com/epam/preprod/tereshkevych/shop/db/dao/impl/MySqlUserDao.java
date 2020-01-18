package com.epam.preprod.tereshkevych.shop.db.dao.impl;

import com.epam.preprod.tereshkevych.shop.db.dao.AbstractMySqlDao;
import com.epam.preprod.tereshkevych.shop.db.dao.UserDao;
import com.epam.preprod.tereshkevych.shop.db.entity.User;
import com.epam.preprod.tereshkevych.shop.db.extractor.DbExtractor;
import com.epam.preprod.tereshkevych.shop.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MySqlUserDao extends AbstractMySqlDao<User> implements UserDao {

    private static final Logger LOG = Logger.getLogger(MySqlUserDao.class);

    private static final String KEY_CONNECTION = "con";

    private static final String SQL_ADD_USER = "INSERT INTO users VALUES (DEFAULT,DEFAULT,?,?,md5(?),?,?,?)";
    private static final String SQL_FIND_USER_BY_LOGIN_PASSWORD = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String SQL_FIND_ALL_USER = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE users SET login=?,email=?,password=?,first_name=?,last_name=?,last_name=? WHERE id=?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";

    private static final String ERROR_CANNOT_OBTAIN_USERS = "Cannot obtain users";

    private static final int PARAMETER_INDEX_PASSWORD_FOR_LOGIN = 2;

    private static final int PARAMETER_INDEX_LOGIN = 1;
    private static final int PARAMETER_INDEX_EMAIL = 2;
    private static final int PARAMETER_INDEX_PASSWORD = 3;
    private static final int PARAMETER_INDEX_FIRST_NAME = 4;
    private static final int PARAMETER_INDEX_LAST_NAME = 5;
    private static final int PARAMETER_INDEX_AVATAR = 6;
    private static final int PARAMETER_INDEX_ID = 7;

    public MySqlUserDao(DbExtractor<User> userExtractor) {
        super(userExtractor);
    }

    @Override
    protected String getInsertQuery() {
        return SQL_ADD_USER;
    }

    @Override
    protected String getSelectQuery() {
        return SQL_FIND_USER_BY_LOGIN;
    }

    @Override
    protected String getAllSelectQuery() {
        return SQL_FIND_ALL_USER;
    }

    @Override
    protected String getUpdateQuery() {
        return SQL_UPDATE_USER_BY_ID;
    }

    @Override
    protected String getDeleteQuery() {
        return SQL_DELETE_USER_BY_ID;
    }

    @Override
    protected PreparedStatement setPrepareStatementToInsert(PreparedStatement statement, User user) throws SQLException {
        statement.setString(PARAMETER_INDEX_LOGIN, user.getLogin());
        statement.setString(PARAMETER_INDEX_EMAIL, user.getEmail());
        statement.setString(PARAMETER_INDEX_PASSWORD, user.getPassword());
        statement.setString(PARAMETER_INDEX_FIRST_NAME, user.getFirstName());
        statement.setString(PARAMETER_INDEX_LAST_NAME, user.getLastName());
        statement.setString(PARAMETER_INDEX_AVATAR, user.getAvatar());
        return statement;
    }

    @Override
    protected PreparedStatement setPrepareStatementToUpdate(PreparedStatement statement, User user) throws SQLException {
        setPrepareStatementToInsert(statement, user);
        statement.setLong(PARAMETER_INDEX_ID, user.getId());
        return statement;
    }

    @Override
    public User login(String login, String password, Map<String, Object> containerConnection) throws DaoException {
        Connection connection = (Connection) containerConnection.get(KEY_CONNECTION);
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN_PASSWORD)) {
            statement.setString(PARAMETER_INDEX_LOGIN, login);
            statement.setString(PARAMETER_INDEX_PASSWORD_FOR_LOGIN, password);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = parseResultSet(resultSet);
            }
            return user;
        } catch (SQLException ex) {
            LOG.error(ERROR_CANNOT_OBTAIN_USERS, ex);
            throw new DaoException(ERROR_CANNOT_OBTAIN_USERS, ex);
        }
    }
}