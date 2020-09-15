package com.epam.university.java.core.task016;

public class CoordinateImpl implements Coordinate {
    private int x;
    private int y;

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoordinateImpl that = (CoordinateImpl) o;
        return x == that.x
                && y == that.y;
    }
}
