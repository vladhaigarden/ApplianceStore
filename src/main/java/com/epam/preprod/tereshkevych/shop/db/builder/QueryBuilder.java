package com.epam.preprod.tereshkevych.shop.db.builder;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {

    private static final String SELECT_START_QUERY = "SELECT *";
    private static final String SELECT_COUNT_QUERY = "SELECT COUNT(*)";

    private static final String SQL_FROM = " FROM ";
    private static final String SQL_INNER_JOIN = " INNER JOIN ";
    private static final String SQL_WHERE = " WHERE ";
    private static final String SQL_ORDER_BY = " ORDER BY ";
    private static final String AND = " AND ";
    private static final String SQL_LIMIT = " LIMIT ";
    private static final String SQL_OFFSET = " OFFSET ";
    private static final String EQUAL_SIGN = "=";
    private static final String QUESTION_MARK = "?";
    private static final String ON = " ON ";

    private boolean firstWhere = true;

    private StringBuilder finalQuery;

    private List<Object> valuesParameters = new ArrayList<>();

    public QueryBuilder select() {
        finalQuery = new StringBuilder(SELECT_START_QUERY);
        return this;
    }

    public QueryBuilder selectCount() {
        finalQuery = new StringBuilder(SELECT_COUNT_QUERY);
        return this;
    }

    public QueryBuilder from(String nameEntity) {
        finalQuery.append(SQL_FROM);
        finalQuery.append(nameEntity);
        return this;
    }

    public QueryBuilder join(String nameTable, String firstColumn, String secondColumn) {
        finalQuery.append(SQL_INNER_JOIN);
        finalQuery.append(nameTable);
        finalQuery.append(ON);
        finalQuery.append(firstColumn);
        finalQuery.append(EQUAL_SIGN);
        finalQuery.append(secondColumn);
        return this;
    }

    public QueryBuilder where(String nameValue, String value) {
        if (StringUtils.isNotBlank(value)) {
            if (firstWhere) {
                firstWhere = false;
                finalQuery.append(SQL_WHERE);
            } else {
                finalQuery.append(AND);
            }
            finalQuery.append(nameValue);
            finalQuery.append(EQUAL_SIGN);
            finalQuery.append(QUESTION_MARK);
            valuesParameters.add(value);
        }
        return this;
    }

    public QueryBuilder orderBy(String sorter) {
        if (StringUtils.isNotBlank(sorter)) {
            finalQuery.append(SQL_ORDER_BY);
            finalQuery.append(sorter);
        }
        return this;
    }

    public QueryBuilder limit(Integer limit, Integer offset) {
        int numberOffset = (offset - 1) * limit;
        finalQuery.append(SQL_LIMIT);
        finalQuery.append(QUESTION_MARK);
        finalQuery.append(SQL_OFFSET);
        finalQuery.append(QUESTION_MARK);
        valuesParameters.add(limit);
        valuesParameters.add(numberOffset);
        return this;
    }

    public PreparedStatement build(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(finalQuery.toString());
        int index = 1;
        for (Object value : valuesParameters) {
            preparedStatement.setObject(index++, value);
        }
        return preparedStatement;
    }
}