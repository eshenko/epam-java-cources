package com.epam.university.java.core.task045;

import java.util.ArrayList;
import java.util.List;

public class Task045Impl implements Task045 {
    @Override
    public String doAnagram(String input) {
        if (input == null) {
            throw new IllegalArgumentException();
        }
        if (input.length() == 1 || input.length() == 0) {
            return input;
        }
        List<String> result = new ArrayList<>();
        String[] words = input.split(" ");
        for (String word : words) {
            String res = reverse(word);
            result.add(res);
        }
        return String.join(" ", result);
    }

    /**
     * Reverse word.
     * @param input string.
     * @return reversed string.
     */
    public String reverse(String input) {
        char[] result = new char[input.length()];
        for (int i = 0, j = input.length() - 1; j >= i;) {
            String current = input.substring(i, i + 1);
            if (i == j) {
                result[i] = current.charAt(0);
                break;
            }
            String editable = input.substring(j, j + 1);
            if (current.matches("[a-zA-Z]") && editable.matches("[a-zA-Z]")) {
                result[j] = current.charAt(0);
                result[i] = editable.charAt(0);
                i++;
                j--;
            }
            if (!editable.matches("[a-zA-Z]")) {
                result[j] = editable.charAt(0);
                j--;
            }
            if (!current.matches("[a-zA-Z]")) {
                result[i] = current.charAt(0);
                i++;
            }
        }
        return String.valueOf(result);
    }
}
