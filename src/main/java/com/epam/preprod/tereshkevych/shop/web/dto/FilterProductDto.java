package com.epam.preprod.tereshkevych.shop.web.dto;

import java.util.Objects;

public class FilterProductDto {

    private String name;

    private String minPrice;

    private String maxPrice;

    private String category;

    private String manufacturer;

    private String sorter;

    private Integer numberItems;

    private Integer numberPage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSorter() {
        return sorter;
    }

    public void setSorter(String sorter) {
        this.sorter = sorter;
    }

    public Integer getNumberItems() {
        return numberItems;
    }

    public void setNumberItems(Integer numberItems) {
        this.numberItems = numberItems;
    }

    public Integer getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(Integer numberPage) {
        this.numberPage = numberPage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, minPrice, maxPrice, category, manufacturer, sorter, numberItems, numberPage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterProductDto that = (FilterProductDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice) &&
                Objects.equals(category, that.category) &&
                Objects.equals(manufacturer, that.manufacturer) &&
                Objects.equals(sorter, that.sorter) &&
                Objects.equals(numberItems, that.numberItems) &&
                Objects.equals(numberPage, that.numberPage);
    }

    @Override
    public String toString() {
        return "FilterProductDto{" +
                "name='" + name + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", category='" + category + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", sorter='" + sorter + '\'' +
                ", numberItems='" + numberItems + '\'' +
                ", numberPage='" + numberPage + '\'' +
                '}';
    }
}