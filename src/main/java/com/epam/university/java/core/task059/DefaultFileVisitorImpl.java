package com.epam.university.java.core.task059;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DefaultFileVisitorImpl extends DefaultFileVisitor {
    private final String search;
    private final List<String> result = new ArrayList<>();

    public DefaultFileVisitorImpl(String search) {
        this.search = search;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            String content = new String(Files.readAllBytes(path));
            if (content.contains(search)) {
                result.add(path.toString());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public List<String> getResult() {
        return result;
    }
}
