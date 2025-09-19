package com.offcl.competency_tracker.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

    // Parse only date string -> LocalDate
    public static LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    // Parse date-time string -> LocalDateTime
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    // Convert LocalDate -> LocalDateTime (default time = 00:00:00)
    public static LocalDateTime toDateTime(LocalDate date) {
        return date.atStartOfDay();
    }

    // Convert LocalDate + custom time -> LocalDateTime
    public static LocalDateTime toDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    // Format LocalDate -> String
    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    // Format LocalDateTime -> String
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATETIME_FORMATTER);
    }
    }