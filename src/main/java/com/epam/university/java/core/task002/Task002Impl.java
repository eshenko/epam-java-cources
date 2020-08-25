package com.epam.university.java.core.task002;

import java.util.Arrays;
import java.util.Collections;

public class Task002Impl implements Task002 {
    @Override
    public boolean isEquals(String firstString, String secondString) {
        if (firstString == null || secondString == null) {
            throw new IllegalArgumentException();
        }
        return firstString.equals(secondString);
    }

    @Override
    public String left(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        }
        if (number > sourceString.length()) {
            return sourceString;
        }
        return sourceString.substring(0, number);
    }

    @Override
    public String left(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }
        if (!sourceString.contains(separator)) {
            return sourceString;
        }
        return sourceString.substring(0, sourceString.indexOf(separator));
    }

    @Override
    public String right(String sourceString, int number) {
        if (sourceString == null || number < 0) {
            throw new IllegalArgumentException();
        }
        if (number > sourceString.length()) {
            return sourceString;
        }
        return sourceString.substring(number + 1).trim();
    }

    @Override
    public String right(String sourceString, String separator) {
        if (sourceString == null || separator == null) {
            throw new IllegalArgumentException();
        }
        if (!sourceString.contains(separator)) {
            return sourceString;
        }
        return sourceString.substring(sourceString.indexOf(separator) + separator.length());
    }

    @Override
    public String[] split(String sourceString, String split) {
        if (sourceString == null || split == null) {
            throw new IllegalArgumentException();
        }
        return sourceString.split(split);
    }

    @Override
    public String join(String[] sourceCollection, String glue) {
        if (glue == null
            || sourceCollection == null
            || sourceCollection.length == 0
            || Arrays.asList(sourceCollection).contains(null)) {
            throw new IllegalArgumentException();
        }
        return String.join(glue, sourceCollection);
    }
}
