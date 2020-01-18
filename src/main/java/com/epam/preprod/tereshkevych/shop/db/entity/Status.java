package com.epam.preprod.tereshkevych.shop.db.entity;

/**
 * Status order.
 *
 * @author V.Tereshkevych
 */
public enum Status {

    ACCEPTED("accepted"),
    CONFIRMED("confirmed"),
    FORMED("formed"),
    EXILED("exiled"),
    COMPLETED("completed"),
    CANCELED("canceled");


    private String value;

    Status(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "Status{" +
                "value='" + value + '\'' +
                '}';
    }
}