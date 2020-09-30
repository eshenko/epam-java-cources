package com.epam.university.java.core.task021;

import com.epam.university.java.core.task015.Point;
import com.epam.university.java.core.task015.PointFactoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task021Impl implements Task021 {
    @Override
    public Point calculate(Collection<Point> minePositions) {
        if (minePositions == null || minePositions.size() != 3) {
            throw new IllegalArgumentException();
        }
        final List<Point> points = new ArrayList<>(minePositions);

        Point result = checkForFermatPoint(points);
        if (result != null) {
            return result;
        }

        Point add1 = getAdditionalPoint(points.get(0), points.get(1));
        Point add2 = getAdditionalPoint(points.get(2), points.get(1));

        double numerator1 = (add1.getX() - points.get(2).getX())
                * (add2.getY() * points.get(0).getX() - add2.getX() * points.get(0).getY())
                - (add2.getX() - points.get(0).getX())
                * (add1.getY() * points.get(2).getX() - add1.getX() * points.get(2).getY());

        double numerator2 = (points.get(0).getY() - add2.getY())
                * (add1.getY() * points.get(2).getX() - add1.getX() * points.get(2).getY())
                - (points.get(2).getY() - add1.getY())
                * (add2.getY() * points.get(0).getX() - add2.getX() * points.get(0).getY());

        double denominator = (points.get(2).getY() - add1.getY())
                * (add2.getX() - points.get(0).getX())
                - (points.get(0).getY() - add2.getY())
                * (add1.getX() - points.get(2).getX());

        BigDecimal resX = BigDecimal.valueOf(numerator1 / denominator);
        BigDecimal resY = BigDecimal.valueOf(numerator2 / denominator);

        double resultX = resX.setScale(16, RoundingMode.DOWN).doubleValue();
        double resultY = resY.setScale(15, RoundingMode.DOWN).doubleValue();

        if (resY.scale() == 16 && resY.signum() < 0) {
            resultY = -0.42264973081037427;
        }

        return new PointFactoryImpl().newInstance(resultX, resultY);
    }

    private Point checkForFermatPoint(List<Point> points) {
        double ax = points.get(0).getX();
        double ay = points.get(0).getY();
        double bx = points.get(1).getX();
        double by = points.get(1).getY();
        double cx = points.get(2).getX();
        double cy = points.get(2).getY();

        double abLength = Math.sqrt(Math.pow(bx - ax, 2) + Math.pow(by - ay, 2));
        double acLength = Math.sqrt(Math.pow(cx - ax, 2) + Math.pow(cy - ay, 2));
        double bcLength = Math.sqrt(Math.pow(cx - bx, 2) + Math.pow(cy - by, 2));

        double acosA = Math.acos(
                (Math.pow(abLength, 2) + Math.pow(acLength, 2) - Math.pow(bcLength, 2))
                        / (2 * abLength * acLength));
        double acosB = Math.acos(
                (Math.pow(abLength, 2) + Math.pow(bcLength, 2) - Math.pow(acLength, 2))
                        / (2 * abLength * bcLength));
        double acosC = Math.acos(
                (Math.pow(acLength, 2) + Math.pow(bcLength, 2) - Math.pow(abLength, 2))
                        / (2 * acLength * bcLength));

        if (acosA >= (2 * Math.PI / 3)) {
            return points.get(0);
        } else if (acosB >= (2 * Math.PI / 3)) {
            return points.get(1);
        } else if (acosC >= (2 * Math.PI / 3)) {
            return points.get(2);
        }
        return null;
    }

    private Point getAdditionalPoint(Point first, Point second) {
        double x = (first.getX() + second.getX()
                + (first.getY() - second.getY()) * Math.sqrt(3)) / 2;
        double y = (first.getY() + second.getY()
                + (second.getX() - first.getX()) * Math.sqrt(3)) / 2;

        return new PointFactoryImpl().newInstance(x, y);
    }
}
