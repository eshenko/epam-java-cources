package com.epam.university.java.core.task003;

import java.util.*;

public class Task003Impl implements Task003 {
    @Override
    public String[] invert(String[] source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < source.length / 2; i++) {
            String tmp = source[i];
            source[i] = source[source.length - i - 1];
            source[source.length - i - 1] = tmp;
        }
        return source;
    }

    @Override
    public String[] join(String[] first, String[] second) {
        if (first == null && second == null) {
            throw new IllegalArgumentException();
        }
        String[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    @Override
    public int findMax(int[] source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        Arrays.sort(source);
        return source[source.length - 1];
    }

    @Override
    public String[] filter(String[] source, FilteringCondition condition) {
        if (source == null && condition == null) {
            throw new IllegalArgumentException();
        }
        List<String> result = new ArrayList<>();
        for (String s : source) {
            if (condition.isValid(s)) {
                result.add(s);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String[] removeElements(String[] source, String[] toRemote) {
        if (source == null && toRemote == null) {
            throw new IllegalArgumentException();
        }
        List<String> result = new ArrayList<>(Arrays.asList(source));
        for (int i = 0; i < result.size(); i++) {
            if (Arrays.asList(toRemote).contains(result.get(i))) {
                result.remove(i);
            }
        }
        return result.toArray(new String[result.size()]);
    }

    @Override
    public String[] map(String[] source, MappingOperation operation) {
        if (source == null && operation == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < source.length; i++) {
            source[i] = operation.map(source[i]);
        }
        return source;
    }

    @Override
    public String[] flatMap(String[] source, FlatMappingOperation operation) {
        if (source == null && operation == null) {
            throw new IllegalArgumentException();
        }

        Comparator<String> comparator = new Comparator<>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o2) - Integer.parseInt(o1);
            }
        };

        Set<String> result = new TreeSet<>(comparator);
        for (String value : source) {
            Collections.addAll(result, operation.flatMap(value));
        }

        return result.toArray(new String[result.size()]);
    }
}
