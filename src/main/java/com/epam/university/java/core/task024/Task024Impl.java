package com.epam.university.java.core.task024;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task024Impl implements Task024 {
    @Override
    public Collection<String> getWordsCount(String source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        if (source.length() == 0) {
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        for (String s : source.split("(?=[\\p{Lu}])")) {
            result.add(s.toLowerCase());
        }
        return result;
    }
}
