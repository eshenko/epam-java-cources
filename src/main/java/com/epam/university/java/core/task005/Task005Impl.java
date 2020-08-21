package com.epam.university.java.core.task005;

public class Task005Impl implements Task005 {
    @Override
    public PiHolder findPi(int digits) {
        if (digits <= 0 || digits > 10) {
            throw new IllegalArgumentException();
        }

        int end = (int) (Math.pow(10, digits) - 1);
        int begin = (int) (Math.pow(10, digits - 1));

        int numerator = 0;
        int denominator = 0;

        double tmp = 0;
        double min = 1;

        for (int i = (int) (begin * Math.PI); i <= end; i++) {
            for (int j = i / 4; j <= i / 3; j++) {
                tmp = Math.abs(((double) i / (double) j) - Math.PI);
                if (tmp < min) {
                    min = tmp;
                    numerator = i;
                    denominator = j;
                }
            }
        }

        return new PiHolderImpl(numerator, denominator);
    }
}
