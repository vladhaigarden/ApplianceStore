package com.epam.preprod.tereshkevych.shop.db.manager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Creates connection by url
 *
 * @author Vladyslav Tereshkevych
 */

public class ConnectionManager {

    private DataSource dataSource;

    public ConnectionManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}