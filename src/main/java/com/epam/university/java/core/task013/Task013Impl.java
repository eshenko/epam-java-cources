package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task013Impl implements Task013 {
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        for (FigureAction figureAction : actions) {
            figureAction.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {

        List<Vertex> vertexList = new ArrayList<>(sortVertices(figure));

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 0; i < vertexList.size() - 2; i++) {
            int vector = getVector(vertexList.get(i), vertexList.get(i + 1), vertexList.get(i + 2));
            if (vector > 0) {
                left.add(vector);
            } else if (vector < 0) {
                right.add(vector);
            }
        }
        return (left.size() > 0 && right.size() == 0) || (right.size() > 0 && left.size() == 0);
    }

    /**
     * Get vector sign.
     *
     * @param a - first point
     * @param b - second point
     * @param c - third point
     * @return vector sign
     */
    public int getVector(Vertex a, Vertex b, Vertex c) {
        return (b.getX() - a.getX()) * (c.getY() - b.getY())
                - (b.getY() - a.getY()) * (c.getX() - b.getX());
    }

    /**
     * Sort vertices.
     *
     * @param figure - source figure
     * @return sorted collection
     */
    public ArrayList<Vertex> sortVertices(Figure figure) {
        ArrayList<Vertex> vertices = (ArrayList<Vertex>) figure.getVertexes();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(0).getX() > vertices.get(i).getX()) {
                Vertex tmp = vertices.get(0);
                vertices.set(0, vertices.get(i));
                vertices.set(i, tmp);
            }
        }
        for (int i = 1; i < vertices.size(); i++) {
            int j = i;
            while (j > 1 && (getVector(vertices.get(0),
                             vertices.get(j - 1),
                             vertices.get(j)) < 0)) {
                Vertex tmp = vertices.get(j);
                vertices.set(j, vertices.get(j - 1));
                vertices.set(j - 1, tmp);
                j--;
            }
        }

        return vertices;
    }
}
