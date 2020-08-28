package com.epam.university.java.core.task014;

import java.util.ArrayList;
import java.util.Collection;

public class Task014Impl implements Task014 {
    @Override
    public Collection<VampireNumber> getVampireNumbers() {
        Collection<VampireNumber> result = new ArrayList<>();
        for (int i = 10; i < 100; i++) {
            for (int j = 10; j < 100; j++) {
                if (isVampire(i, j)) {
                    VampireNumber vampire = new VampireNumberFactoryImpl().newInstance(i * j, i, j);
                    if (!result.contains(vampire)) {
                        result.add(vampire);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Verify if a number is vampire.
     * @param first part of number
     * @param second part of number
     * @return result true/false
     */
    public boolean isVampire(int first, int second) {
        int mult = first * second;
        String multString = String.valueOf(mult);
        String firstString = String.valueOf(first);
        String secondString = String.valueOf(second);

        if (multString.length() == 4
            && !(firstString.endsWith("0") && secondString.endsWith("0"))) {

            multString = multString.replaceFirst(firstString.substring(0, 1), "");
            multString = multString.replaceFirst(firstString.substring(1), "");
            multString = multString.replaceFirst(secondString.substring(0, 1), "");
            multString = multString.replaceFirst(secondString.substring(1), "");

            return multString.length() == 0;
        }
        return false;
    }
}
