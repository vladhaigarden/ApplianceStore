package com.epam.preprod.tereshkevych.shop.db.extractor;

import com.epam.preprod.tereshkevych.shop.db.entity.Manufacturer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Create manufacturer with fields which was obtained from database
 *
 * @author Vladyslav Tereshkevych
 */
public class ManufacturerDaoExtractor implements DbExtractor<Manufacturer> {

    private static final String ENTITY_ID = "id";

    private static final String CATEGORY_NAME = "name";

    @Override
    public Manufacturer extract(ResultSet rs) throws SQLException {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(rs.getLong(ENTITY_ID));
        manufacturer.setName(rs.getString(CATEGORY_NAME));
        return manufacturer;
    }
}