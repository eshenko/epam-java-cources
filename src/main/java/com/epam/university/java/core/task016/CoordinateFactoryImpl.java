package com.epam.university.java.core.task016;

public class CoordinateFactoryImpl implements CoordinateFactory {
    @Override
    public Coordinate newInstance(int x, int y) {
        Coordinate instance = new CoordinateImpl();
        instance.setX(x);
        instance.setY(y);
        return instance;
    }
}
