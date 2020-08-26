package com.epam.university.java.core.task012;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task012Impl implements Task012 {
    List<Integer> existedVortex = new ArrayList<>();

    @Override
    public Graph invokeActions(Graph sourceGraph, Collection<GraphAction> actions) {
        for (GraphAction ga : actions) {
            ga.run(sourceGraph);
        }
        return sourceGraph;
    }

    @Override
    public boolean pathExists(Graph graph, int from, int to) {
        if (graph.edgeExists(from, to)) {
            return true;
        }
        Collection<Integer> vortexes = graph.getAdjacent(from);
        for (int i : vortexes) {
            if (existedVortex.contains(i)) {
                continue;
            } else {
                existedVortex.add(i);
            }

            if (pathExists(graph, i, to)) {
                return true;
            }
        }
        return false;
    }
}
