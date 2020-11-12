package com.epam.university.java.core.task051;

public class Task051Impl implements Task051 {
    @Override
    public String replace(String source) {
        if (source == null
                || source.isBlank()
                || source.matches("[^a-z]+")
                || source.equals("the")) {
            throw new IllegalArgumentException();
        }
        String[] words = source.split(" ");
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals("the") && "AEIOUaeiou".indexOf(words[i + 1].charAt(0)) != -1) {
                words[i] = "an";
            } else if (words[i].equals("the")) {
                words[i] = "a";
            }
        }
        return String.join(" ", words);
    }
}
