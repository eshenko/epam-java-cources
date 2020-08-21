package com.epam.university.java.core.task004;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

public class Task004Impl implements Task004 {
    @Override
    public String[] intersection(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        List<String> result = new ArrayList<>();
        for (String s : first) {
            if (Arrays.asList(second).contains(s)) {
                result.add(s);
            }
        }
        return result.toArray(new String[0]);
    }

    @Override
    public String[] union(String[] first, String[] second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        Set<String> result = new LinkedHashSet<>(Arrays.asList(first));
        result.addAll(Arrays.asList(second));
        return result.toArray(new String[0]);
    }
}
