package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FigureImpl implements Figure {

    private final int vertexCount;
    List<Vertex> vertexList = new ArrayList<>();

    public FigureImpl(int vertexCount) {
        this.vertexCount = vertexCount;
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (vertexList.size() >= vertexCount) {
            throw new IllegalArgumentException("Too many vertexes");
        }
        vertexList.add(vertex);
    }

    @Override
    public Collection<Vertex> getVertexes() {
        return vertexList;
    }
}
