package com.epam.university.java.core.task048;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task048Impl implements Task048 {
    @Override
    public Collection<Integer> getArmstrongNumbers(Integer from, Integer to) {
        if (from == null || to == null || from < 0 || to < 0) {
            throw new IllegalArgumentException();
        }
        List<Integer> result = new ArrayList<>();
        for (int i = from; i < to; i++) {
            String index = String.valueOf(i);
            String[] digits = index.split("");
            int sum = 0;
            for (String d : digits) {
                sum += Math.pow(Integer.parseInt(d), index.length());
            }
            if (sum == i) {
                result.add(i);
            }
        }
        return result;
    }
}
