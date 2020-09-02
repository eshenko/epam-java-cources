package com.epam.university.java.core.task015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task015Impl implements Task015 {
    @Override
    public double getArea(Square first, Square second) {
        List<Point> firstSquare = getVertices(first);
        List<Point> secondSquare = getVertices(second);

        Set<Point> intersections = new HashSet<>();

        intersections.addAll(getInsidePoints(firstSquare, secondSquare));
        intersections.addAll(getInsidePoints(secondSquare, firstSquare));

        intersections.addAll(getIntersectionPoints(firstSquare, secondSquare));

        if (intersections.size() < 3) {
            return 0;
        }
        if (intersections.size() == 3) {
            return getTriangleArea(intersections);
        }
        return getPolygonArea(intersections);
    }

    /**
     * Get all vertices of square.
     * @param square whose vertices we are looking for
     * @return list of vertices
     */
    private List<Point> getVertices(Square square) {
        List<Point> vertices = new ArrayList<>(4);

        double aX = square.getFirst().getX();
        double aY = square.getFirst().getY();

        double cX = square.getSecond().getX();
        double cY = square.getSecond().getY();

        double bX = (aX + cX) / 2 + (aY - cY) / 2;
        double bY = (aY + cY) / 2 + (cX - aX) / 2;

        double dX = (aX + cX) / 2 + (cY - aY) / 2;
        double dY = (aY + cY) / 2 + (aX - cX) / 2;

        vertices.add(new PointFactoryImpl().newInstance(aX, aY));
        vertices.add(new PointFactoryImpl().newInstance(bX, bY));
        vertices.add(new PointFactoryImpl().newInstance(cX, cY));
        vertices.add(new PointFactoryImpl().newInstance(dX, dY));

        return vertices;
    }

    /**
     * Get points which are inside square <code>in</code>.
     * @param in - list of points where we are looking for <code>find</code> points
     * @param find - list of points which we are looking for
     * @return collection of points
     */
    private Collection<Point> getInsidePoints(List<Point> in, List<Point> find) {
        Set<Point> result = new HashSet<>();
        int wn = 0;
        for (Point d : find) {
            double dX = d.getX();
            double dY = d.getY();
            for (int i = 0; i < in.size(); i++) {
                int k = getK(i, in.size());
                double aX = in.get(i).getX();
                double aY = in.get(i).getY();
                double bX = in.get(k).getX();
                double bY = in.get(k).getY();

                double tmp = (bX - aX) * (dY - bY) - (bY - aY) * (dX - bX);
                if (tmp <= 0) {
                    wn++;
                }
            }
            if (wn == 4) {
                result.add(d);
            }
            wn = 0;
        }
        return result;
    }

    /**
     * Get index K.
     * @param index on which the K is based
     * @param length of collection
     * @return index K
     */
    private int getK(int index, int length) {
        int k = index + 1;
        if (k == length) {
            k = 0;
        }
        return k;
    }

    /**
     * Get points which are intersection of squares.
     * @param first list of points of square
     * @param second list of points of square
     * @return collection of points
     */
    private Collection<Point> getIntersectionPoints(List<Point> first, List<Point> second) {
        Set<Point> result = new HashSet<>();
        for (int i = 0; i < first.size(); i++) {
            int k1 = getK(i, first.size());
            double aX = first.get(i).getX();
            double aY = first.get(i).getY();
            double bX = first.get(k1).getX();
            double bY = first.get(k1).getY();

            for (int j = 0; j < second.size(); j++) {
                int k2 = getK(j, second.size());
                double cX = second.get(j).getX();
                double cY = second.get(j).getY();
                double dX = second.get(k2).getX();
                double dY = second.get(k2).getY();

                double tmp = (aX - bX) * (dY - cY) - (aY - bY) * (dX - cX);
                double uA = ((aX - cX) * (dY - cY) - (aY - cY) * (dX - cX)) / tmp;
                double uB = ((aX - bX) * (aY - cY) - (aY - bY) * (aX - cX)) / tmp;

                if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
                    double x = aX + uA * (bX - aX);
                    double y = aY + uA * (bY - aY);
                    result.add(new PointFactoryImpl().newInstance(x, y));
                }
            }
        }
        return result;
    }

    /**
     * Get Triangle are.
     * @param triangle whose area we are looking for
     * @return area
     */
    private double getTriangleArea(Collection<Point> triangle) {
        List<Point> list = new ArrayList<>(triangle);
        List<Double> side = new ArrayList<>(3);
        for (int i = 0; i < list.size(); i++) {
            int k = getK(i, list.size());
            double aX = list.get(i).getX();
            double aY = list.get(i).getY();
            double bX = list.get(k).getX();
            double bY = list.get(k).getY();

            side.add(Math.sqrt(Math.pow(bX - aX, 2) + Math.pow(bY - aY, 2)));
        }
        double p = (side.get(0) + side.get(1) + side.get(2)) / 2;
        return Math.sqrt(p * (p - side.get(0)) * (p - side.get(1)) * (p - side.get(2)));
    }

    /**
     * Get polygon area.
     * @param polygon whose area we are looking for
     * @return area
     */
    private double getPolygonArea(Collection<Point> polygon) {
        List<Point> list = getSortedPoints(polygon);
        double area = 0.0;
        Point main = list.remove(0);
        for (int i = 0; i < list.size() - 1; i++) {
            area += getTriangleArea(new ArrayList<>(Arrays.asList(main,
                                                                  list.get(i),
                                                                  list.get(i + 1))));
        }
        return area;
    }

    /**
     * Get ordered list of points of polygon.
     * @param polygon whose points we are ordering
     * @return ordered list
     */
    private List<Point> getSortedPoints(Collection<Point> polygon) {
        List<Point> result = new ArrayList<>(polygon);
        for (int i = 1; i < result.size(); i++) {
            if (result.get(0).getX() > result.get(i).getX()) {
                Point tmp = result.get(0);
                result.set(0, result.get(i));
                result.set(i, tmp);
            }
            while (i > 1 && getVector(result.get(0), result.get(i - 1), result.get(i)) < 0) {
                Point tmp = result.get(i);
                result.set(i, result.get(i - 1));
                result.set(i - 1, tmp);
                i--;
            }
        }
        return result;
    }

    /**
     * Get vector sign.
     * @param a - first point
     * @param b - second point
     * @param c - third point
     * @return vector sign
     */
    public double getVector(Point a, Point b, Point c) {
        return (b.getX() - a.getX()) * (c.getY() - b.getY())
                - (b.getY() - a.getY()) * (c.getX() - b.getX());
    }
}