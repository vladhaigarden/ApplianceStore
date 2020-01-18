package com.epam.preprod.tereshkevych.shop.container;

import com.epam.preprod.tereshkevych.shop.db.entity.Product;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Cart for the content of products in one session
 *
 * @author Vladyslav Tereshkevych
 */
public class Cart {

    private Map<Product, Integer> basket = new LinkedHashMap<>();

    public int getSize() {
        return basket.size();
    }

    public int getAmountProduct() {
        return basket.values().stream().mapToInt(integer -> integer).sum();
    }

    public void clear() {
        basket.clear();
    }

    public void add(Product product) {
        int productAmount = basket.getOrDefault(product, -1);
        if (productAmount == -1) {
            basket.put(product, 1);
        } else {
            basket.put(product, productAmount + 1);
        }
    }

    public void remove(Product product) {
        basket.remove(product);
    }

    public void reduce(Product product) {
        int productAmount = basket.getOrDefault(product, -1);
        if (productAmount != -1) {
            if (productAmount == 1) {
                remove(product);
            } else {
                basket.put(product, productAmount - 1);
            }
        }
    }

    public Map<Product, Integer> getBasket() {
        return basket;
    }

    public double getTotalPrice() {
        double price = basket.entrySet().stream().mapToDouble(o -> o.getKey().getPrice() * o.getValue()).sum();
        return Math.round(price * 100) / 100D;
    }

    @Override
    public String toString() {
        return basket.toString();
    }
}