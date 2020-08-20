package com.epam.university.java.core.task003;

public class MappingOperationImpl implements MappingOperation {
    @Override
    public String map(String source) {
        StringBuilder result = new StringBuilder(source);
        result.reverse();
        return result.toString();
    }
}
