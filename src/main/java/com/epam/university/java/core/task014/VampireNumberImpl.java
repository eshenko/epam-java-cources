package com.epam.university.java.core.task014;

public class VampireNumberImpl implements VampireNumber {
    private final int mult;
    private final int first;
    private final int second;

    /**
     * Just constructor.
     * @param multiplication of two part of numbers
     * @param first part of number
     * @param second part of number
     */
    public VampireNumberImpl(int multiplication, int first, int second) {
        this.mult = multiplication;
        this.first = first;
        this.second = second;
    }

    @Override
    public int getMultiplication() {
        return mult;
    }

    @Override
    public int getFirst() {
        return first;
    }

    @Override
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VampireNumberImpl that = (VampireNumberImpl) o;
        return (first == that.getFirst() && second == that.getSecond())
                || (first == that.getSecond() && second == that.getFirst());
    }
}
