package com.epam.university.java.core.task041;

import java.util.ArrayList;
import java.util.Collection;

public class Task041Impl implements Task041 {
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }
        EntityImpl entity = new EntityImpl();
        entity.setValue(value);
        if (collection.size() == 0) {
            entity.setId(0);
        } else {
            entity.setId(collection.size() - 1);
        }
        collection.add(entity);
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Entity> list = new ArrayList<>(collection);
        int id = entity.getId();
        return list.get(id);
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || entity == null || value == null || !collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Entity> list = new ArrayList<>(collection);
        ((EntityImpl) list.get(entity.getId())).setValue(value);
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        collection.remove(entity);
    }
}
