package com.epam.university.java.core.task009;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class Task009Impl implements Task009 {
    @Override
    public Collection<String> countWords(File sourceFile) {
        if (sourceFile == null) {
            throw new IllegalArgumentException();
        }
        StringBuilder text = new StringBuilder();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            while ((line = br.readLine()) != null) {
                text.append("\n").append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] words = text.toString()
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z\\-\\s]", "")
                .split("\\s+");
        return new HashSet<>(Arrays.asList(words));
    }
}
