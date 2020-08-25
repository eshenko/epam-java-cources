package com.epam.university.java.core.task010;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task010Impl implements Task010 {
    @Override
    public Map<String, Integer> countWordNumbers(File source) {
        if (source == null) {
            throw new IllegalArgumentException();
        }
        Map<String, Integer> result = new HashMap<>();
        for (String s : getUniqWords(source)) {
            if (!result.containsKey(s)) {
                result.put(s, 1);
            } else {
                result.put(s, result.get(s) + 1);
            }
        }
        return result;
    }

    private String[] getUniqWords(File source) {
        StringBuilder text = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            while ((line = br.readLine()) != null) {
                text.append("\n").append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString()
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z\\-\\s]", "")
                .split("\\s+");
    }
}
