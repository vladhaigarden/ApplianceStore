package com.epam.preprod.tereshkevych.shop.db.entity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

public class Order extends Entity {

    private Long userId;

    private Status status;

    private String stateDetail;

    private Timestamp dateTime;

    private List<OrderHistory> orderHistory;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getStateDetail() {
        return stateDetail;
    }

    public void setStateDetail(String stateDetail) {
        this.stateDetail = stateDetail;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public List<OrderHistory> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<OrderHistory> orderHistory) {
        this.orderHistory = orderHistory;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, status, stateDetail, dateTime, orderHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(userId, order.userId) &&
                status == order.status &&
                Objects.equals(stateDetail, order.stateDetail) &&
                Objects.equals(dateTime, order.dateTime) &&
                Objects.equals(orderHistory, order.orderHistory);
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", status=" + status +
                ", stateDetail='" + stateDetail + '\'' +
                ", dateTime=" + dateTime +
                ", orderHistory=" + orderHistory +
                '}';
    }
}