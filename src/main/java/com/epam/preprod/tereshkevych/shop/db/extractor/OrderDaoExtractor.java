package com.epam.preprod.tereshkevych.shop.db.extractor;

import com.epam.preprod.tereshkevych.shop.db.entity.Order;
import com.epam.preprod.tereshkevych.shop.db.entity.Status;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoExtractor implements DbExtractor<Order> {

    private static final String ENTITY_ID = "id";

    private static final String USER_ID = "user_id";

    private static final String ORDER_STATUS = "status";

    private static final String ORDER_DETAIL = "state_detail";
    private static final String ORDER_DATE = "date_time";

    @Override
    public Order extract(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong(ENTITY_ID));
        order.setUserId(rs.getLong(USER_ID));
        order.setStatus(Status.valueOf(rs.getString(ORDER_STATUS).toUpperCase()));
        order.setStateDetail(rs.getString(ORDER_DETAIL));
        order.setDateTime(rs.getTimestamp(ORDER_DATE));
        return order;
    }
}
