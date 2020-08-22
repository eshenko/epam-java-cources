package com.epam.university.java.core.task006;

import java.util.Collection;

public class Task006Impl implements Task006 {
    @Override
    public double resistance(Collection<Measurement> measurements) {
        if (measurements == null) {
            throw new IllegalArgumentException();
        }

        double avgU = 0.0;
        double avgI = 0.0;
        double devU = 0.0;
        double devI = 0.0;

        for (Measurement m : measurements) {
            avgU += m.getVoltage();
            avgI += m.getAmperage();
        }

        avgU /= measurements.size();
        avgI /= measurements.size();

        for (Measurement m : measurements) {
            devU += (m.getVoltage() - avgU) * (m.getAmperage() - avgI);
            devI += Math.pow(m.getAmperage() - avgI, 2);
        }

        return ((int)((devU / devI) * 1000)) / 1000.0;
    }
}
