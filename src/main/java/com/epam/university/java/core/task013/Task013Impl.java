package com.epam.university.java.core.task013;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task013Impl implements Task013 {
    @Override
    public Figure invokeActions(Figure figure, Collection<FigureAction> actions) {
        if (figure == null || actions == null || actions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        for (FigureAction figureAction : actions) {
            figureAction.run(figure);
        }
        return figure;
    }

    @Override
    public boolean isConvexPolygon(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException();
        }

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
    private int getVector(Vertex a, Vertex b, Vertex c) {
        int baX = a.getX() - b.getX();
        int baY = a.getY() - b.getY();
        int bcX = c.getX() - b.getX();
        int bcY = c.getY() - b.getY();
        return (baX * bcY) - (baY * bcX);
    }

    /**
     * Get distance between points.
     * @param p1 first point
     * @param p2 second point
     * @return distance
     */
    private int getDistance(Vertex p1, Vertex p2) {
        return (p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
                + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY());
    }

    /**
     * Sort vertices.
     * @param figure - source figure
     * @return sorted collection
     */
    private List<Vertex> sortVertices(Figure figure) {
        ArrayList<Vertex> vertices = (ArrayList<Vertex>) figure.getVertexes();
        for (int i = 1; i < vertices.size(); i++) {
            if (vertices.get(0).getX() > vertices.get(i).getX()) {
                Vertex tmp = vertices.get(0);
                vertices.set(0, vertices.get(i));
                vertices.set(i, tmp);
            }
            while (i > 1) {
                int cross = getVector(vertices.get(0), vertices.get(i - 1), vertices.get(i));
                if (cross < 0) {
                    Vertex tmp = vertices.get(i);
                    vertices.set(i, vertices.get(i - 1));
                    vertices.set(i - 1, tmp);
                    i--;
                } else if (cross == 0
                        && getDistance(vertices.get(0), vertices.get(i - 1))
                        < getDistance(vertices.get(0), vertices.get(i))
                        && i > vertices.size() / 2) {
                    Vertex tmp = vertices.get(i);
                    vertices.set(i, vertices.get(i - 1));
                    vertices.set(i - 1, tmp);
                } else {
                    break;
                }
            }
        }
        return vertices;
    }
}
