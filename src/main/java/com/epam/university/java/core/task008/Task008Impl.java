package com.epam.university.java.core.task008;

import java.util.Stack;

public class Task008Impl implements Task008 {
    @Override
    public boolean isValid(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        if (sourceString.isEmpty()) {
            return true;
        }


        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) == '{'
                || sourceString.charAt(i) == '('
                || sourceString.charAt(i) == '[') {
                stack.push(sourceString.charAt(i));
            } else if (sourceString.charAt(i) == '}'
                       || sourceString.charAt(i) == ')'
                       || sourceString.charAt(i) == ']') {
                if (stack.size() == 0) {
                    return false;
                }
                if (stack.pop() == '{') {
                    if (sourceString.charAt(i) != '}') {
                        return false;
                    }
                } else if (stack.pop() == '(') {
                    if (sourceString.charAt(i) != ')') {
                        return false;
                    }
                } else if (stack.pop() == '[') {
                    if (sourceString.charAt(i) != ']') {
                        return false;
                    }
                }
            }
        }
        return stack.size() == 0;
    }
}
