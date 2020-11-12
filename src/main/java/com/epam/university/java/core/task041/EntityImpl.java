package com.epam.university.java.core.task041;

import java.util.Objects;

public class EntityImpl implements Entity {
    private int id;
    private String value;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getValue() {
        return value;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityImpl entity = (EntityImpl) o;
        return Objects.equals(value, entity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
