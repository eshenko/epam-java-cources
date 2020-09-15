package com.epam.university.java.core.task027;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Task027Impl implements Task027 {
    @Override
    public Collection<Integer> extract(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        Set<Integer> result = new TreeSet<>();
        for (int i = 1; i <= sourceString.length() / 2; i++) {
            String currentPart = sourceString.substring(0, i);
            if (currentPart.startsWith("0")) {
                continue;
            }
            int current = i;
            while (current < sourceString.length()) {
                int next = Integer.parseInt(currentPart) + 1;
                int nextDigits = String.valueOf(next).length();
                if (current + nextDigits > sourceString.length()) {
                    break;
                }
                String nextPart = sourceString.substring(current,
                        current + nextDigits);
                if (next != Integer.parseInt(nextPart)) {
                    break;
                }
                result.add(next - 1);
                result.add(next);
                currentPart = nextPart;
                current += nextPart.length();
                if (current == sourceString.length()) {
                    return result;
                }
            }
        }
        result.clear();
        return result;
    }
}
