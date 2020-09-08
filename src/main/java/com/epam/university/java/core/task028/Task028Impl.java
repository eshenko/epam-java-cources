package com.epam.university.java.core.task028;

public class Task028Impl implements Task028 {
    @Override
    public int getWays(int value, int power) {
        return countWays(value, power, 1);
    }

    /**
     * Count ways with recursive approach.
     * @param value value number
     * @param power power
     * @param n current number
     * @return count ways
     */
    private int countWays(int value, int power, int n) {
        int val = (int) (value - Math.pow(n, power));
        if (val == 0) {
            return 1;
        }
        if (val < 0) {
            return 0;
        }
        return countWays(val, power, n + 1)
                + countWays(value, power, n + 1);
    }
}
