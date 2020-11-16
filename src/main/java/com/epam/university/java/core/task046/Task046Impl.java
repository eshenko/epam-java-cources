package com.epam.university.java.core.task046;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task046Impl implements Task046 {
    @Override
    public List<String> assembleMatryoshka(Integer k, Integer n) {
        if (k == null || n == null) {
            throw new IllegalArgumentException();
        }
        if (k == 1 && n == 1) {
            return Collections.singletonList("0");
        }
        List<String> result = new ArrayList<>();
        addCombinations(result, new String[k], 0, n - 1, 0);
        return result;
    }

    private void addCombinations(List<String> result,
                        String[] data,
                        int start,
                        int end,
                        int i) {
        if (i == data.length) {
            String[] tmp = data.clone();
            result.add(String.join(" ", tmp));
        } else if (start <= end) {
            data[i] = String.valueOf(start);
            addCombinations(result, data, start + 1, end, i + 1);
            addCombinations(result, data, start + 1, end, i);
        }
    }
}
