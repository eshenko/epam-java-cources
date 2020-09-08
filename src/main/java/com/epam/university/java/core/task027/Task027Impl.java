package com.epam.university.java.core.task027;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Task027Impl implements Task027 {
    @Override
    public Collection<Integer> extract(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        Set<Integer> result = new HashSet<>();
        for (int i = 1; i <= sourceString.length() / 2; i++) {
            String currentPart = sourceString.substring(0, i);
            if (currentPart.startsWith("0")) {
                continue;
            }
            int current = i;
            while (current < sourceString.length()) {
                int next = Integer.parseInt(currentPart) + 1;
                String nextPart = sourceString.substring(current,
                        current + String.valueOf(next).length());
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
