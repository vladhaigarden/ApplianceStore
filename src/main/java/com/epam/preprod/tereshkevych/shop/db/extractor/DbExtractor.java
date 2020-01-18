package com.epam.preprod.tereshkevych.shop.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbExtractor<T> {

    T extract(ResultSet rs) throws SQLException;
}