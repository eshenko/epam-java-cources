package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Task059Impl implements Task059 {
    @Override
    public List<String> find(String path, String search) {
        if (path == null || search == null) {
            throw new IllegalArgumentException();
        }
        DefaultFileVisitor fileVisitor = new DefaultFileVisitorImpl(search);
        try {
            Files.walkFileTree(Paths.get(path), fileVisitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileVisitor.getResult();
    }
}
