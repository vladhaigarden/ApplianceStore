package com.epam.preprod.tereshkevych.shop.db.entity;

import java.util.Objects;

/**
 * Product entity.
 *
 * @author Vladyslav Tereshkevych
 */
public class Product extends Entity {

    private Long categoryId;

    private Long manufacturerId;

    private String name;

    private Double price;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), categoryId, manufacturerId, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(categoryId, product.categoryId) &&
                Objects.equals(manufacturerId, product.manufacturerId) &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                "categoryId=" + categoryId +
                ", manufacturerId=" + manufacturerId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}