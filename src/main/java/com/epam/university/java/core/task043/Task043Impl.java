package com.epam.university.java.core.task043;

import java.util.Arrays;
import java.util.List;

public class Task043Impl implements Task043 {
    private final List<String> code = Arrays.asList(
            ".----", "..---", "...--",
            "....-", ".....", "-....",
            "--...", "---..", "----.",
            "-----", ".-", "-...",
            "-.-.", "-..", ".",
            "..-.", "--.", "....",
            "..", ".---", "-.-",
            ".-..", "--", "-.",
            "---", ".--.", "--.-",
            ".-.", "...", "-",
            "..-", "...-", ".--",
            "-..-", "-.--", "--..",
            ".-.-.-", "--..--", "..--.."
    );

    private final List<String> origin = Arrays.asList(
            "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
            ".", ",", "?");

    @Override
    public String encode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder builder = new StringBuilder();
        String[] letters = sourceString.split("");
        for (String letter : letters) {
            if (letter.equals(" ")) {
                builder.append("  ");
            } else {
                int index = origin.indexOf(letter);
                builder.append(code.get(index));
                builder.append(" ");
            }
        }
        return builder.toString().trim();
    }

    @Override
    public String decode(String sourceString) {
        if (sourceString == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder builder = new StringBuilder();
        String[] words = sourceString.split("   ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                int index = code.indexOf(letter);
                builder.append(origin.get(index));
            }
            builder.append(" ");
        }
        return builder.toString().trim();
    }
}
