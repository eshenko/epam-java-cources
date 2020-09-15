package com.epam.university.java.core.task016;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task016Impl implements Task016 {
    @Override
    public Collection<Coordinate> getSquaresInsideCircle(int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException();
        }
        CoordinateFactory coordinateFactory = new CoordinateFactoryImpl();
        List<Coordinate> result = new ArrayList<>();
        radius *= 2;
        for (int i = 0 - radius; i < radius; i++) {
            for (int j = 0 - radius; j < radius; j++) {
                if ((i * i) + (j * j) <= radius * radius) {
                    int x = i * (-1);
                    int y = j * (-1);
                    if (x != 0 && y != 0) {
                        result.add(coordinateFactory.newInstance(x, y));
                    }
                }
            }
        }
        return result;
    }
}
