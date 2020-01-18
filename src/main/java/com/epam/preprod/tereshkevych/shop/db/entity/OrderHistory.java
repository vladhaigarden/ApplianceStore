package com.epam.preprod.tereshkevych.shop.db.entity;

import java.util.Objects;

public final class OrderHistory {

    private Long orderId;

    private Long productId;

    private int quantityProduct;

    private double currentPrice;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(int quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, quantityProduct, currentPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderHistory that = (OrderHistory) o;
        return quantityProduct == that.quantityProduct &&
                Double.compare(that.currentPrice, currentPrice) == 0 &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public String toString() {
        return "OrderHistory{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantityProduct=" + quantityProduct +
                ", currentPrice=" + currentPrice +
                '}';
    }
}