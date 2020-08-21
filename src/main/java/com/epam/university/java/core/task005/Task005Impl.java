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
            for (int j = begin; j < i; j++) {
                double a = i;
                double b = j;
                tmp = Math.abs((a / b) - Math.PI);
                if (tmp < min) {
                    min = tmp;
                    numerator = i;
                    denominator = j;
                }
                if (j > i/3) {
                    break;
                }
            }
        }


        PiHolder result = new PiHolderImpl(numerator, denominator);

        return result;
    }
}
