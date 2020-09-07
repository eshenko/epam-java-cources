package com.epam.university.java.core.task022;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Task022Impl implements Task022 {
    @Override
    public int maxSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Integer> list = new ArrayList<>(numbers);
        list.sort(Collections.reverseOrder());
        int maxSum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            maxSum += list.get(i);
        }
        return maxSum;
    }

    @Override
    public int minSum(Collection<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<Integer> list = new ArrayList<>(numbers);
        Collections.sort(list);
        int minSum = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            minSum += list.get(i);
        }
        return minSum;
    }
}
