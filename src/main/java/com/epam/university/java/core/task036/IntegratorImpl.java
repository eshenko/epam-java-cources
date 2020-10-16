package com.epam.university.java.core.task036;

import java.util.function.Function;

public class IntegratorImpl implements Integrator {
    @Override
    public double integrate(double left, double right, Function<Double, Double> function) {
        double range = right - left;
        double result = 0.0;
        double n = 1000.0;
        for (double i = 1.0; i < n; i++) {
            double x = left + range * i / n;
            result += function.apply(x);
        }
        result += (function.apply(left) + function.apply(right)) / 2.0;
        return result * range / n;
    }
}
