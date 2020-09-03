package com.epam.university.java.core.task007;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

public class Task007Impl implements Task007 {
    @Override
    public Collection<Integer> multiplyPolynomial(Collection<Integer> first,
                                                  Collection<Integer> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }
        List<Integer> firstList = new ArrayList<>(first);
        List<Integer> secondList = new ArrayList<>(second);

        List<Integer> result = new ArrayList<>();
        int size = firstList.size() + secondList.size();
        for (int i = 0; i < size; i++) {
            result.add(0);
        }

        for (int i = 0; i < firstList.size(); i++) {
            for (int j = 0; j < secondList.size(); j++) {
                int position = i + j;
                result.set(position, result.get(position) + (firstList.get(i) * secondList.get(j)));
            }
        }

        for (int i = result.size() - 1; i > 0; i--) {
            if (result.get(i) == 0) {
                result.remove(i);
            } else {
                break;
            }
        }
        return result;
    }
}