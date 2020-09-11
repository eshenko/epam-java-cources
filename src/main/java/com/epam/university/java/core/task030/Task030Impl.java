package com.epam.university.java.core.task030;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class Task030Impl implements Task030 {
    @Override
    public int daysBetweenDates(LocalDate dateStart, LocalDate dateEnd) {
        if (dateStart == null || dateEnd == null) {
            throw new IllegalArgumentException();
        }
        return dateEnd.getDayOfYear() - dateStart.getDayOfYear();
    }

    @Override
    public LocalDate adjustDays(LocalDate dateStart, int daysToAdd) {
        if (dateStart == null) {
            throw new IllegalArgumentException();
        }
        return dateStart.plusDays(daysToAdd);
    }

    @Override
    public long distanceBetween(Instant instantStart, Instant instantEnd) {
        if (instantStart == null || instantEnd == null) {
            throw new IllegalArgumentException();
        }
        return Duration.between(instantStart, instantEnd).get(ChronoUnit.SECONDS);
    }

    @Override
    public DayOfWeek getDayOfWeek(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        return localDate.getDayOfWeek();
    }

    @Override
    public LocalDate getNextWeekend(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException();
        }
        return localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
    }

    @Override
    public LocalTime getLocalTime(String timeString) {
        if (timeString == null) {
            throw new IllegalArgumentException();
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("K:ma");
        return LocalTime.parse(timeString, formatter);
    }
}
