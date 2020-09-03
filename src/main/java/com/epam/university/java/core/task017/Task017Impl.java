package com.epam.university.java.core.task017;

import java.text.SimpleDateFormat;

public class Task017Impl implements Task017 {
    @Override
    public String formatString(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
        return String.format("You know %s, %s!", args[0], args[1]);
    }

    @Override
    public String formatNumbers(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
        return String.format("%.1f, %<.2f, %<+.2f, %<#a", args[0]);
    }

    @Override
    public String formatDates(Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException();
        }
        for (Object o : args) {
            if (o == null) {
                throw new IllegalArgumentException();
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.dd.MM");
        return simpleDateFormat.format(args[0]);
    }
}
