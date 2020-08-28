package com.epam.university.java.core.task012;

import java.util.ArrayList;
import java.util.Collection;

public class GraphImpl implements Graph {
    private final int vertexes;
    private final boolean[][] edges;

    public GraphImpl(int vertexesCount) {
        this.vertexes = vertexesCount + 1;
        this.edges = new boolean[vertexes][vertexes];
    }

    @Override
    public void createEdge(int from, int to) {
        if (from >= vertexes || to >= vertexes) {
            throw new IllegalArgumentException();
        }
        edges[from][to] = true;
        edges[to][from] = true;
    }

    @Override
    public boolean edgeExists(int from, int to) {
        if (from >= vertexes || to >= vertexes) {
            throw new IllegalArgumentException();
        }
        return edges[from][to];
    }

    @Override
    public void removeEdge(int from, int to) {
        if (from >= vertexes || to >= vertexes) {
            throw new IllegalArgumentException();
        }
        edges[from][to] = false;
        edges[to][from] = false;
    }

    @Override
    public Collection<Integer> getAdjacent(int from) {
        Collection<Integer> adjacent = new ArrayList<>();
        for (int i = 0; i < vertexes; i++) {
            if (edges[from][i]) {
                adjacent.add(i);
            }
        }
        return adjacent;
    }
}
