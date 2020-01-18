package com.epam.preprod.tereshkevych.shop.db.entity;

import java.util.Objects;

/**
 * Manufacturer entity.
 *
 * @author Vladyslav Tereshkevych
 */
public class Manufacturer extends Entity {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
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
        Manufacturer that = (Manufacturer) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
