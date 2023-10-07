package com.jitterted.moborg.adapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatting {

    public static final DateTimeFormatter YYYY_MM_DD_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDateTime fromBrowserDate(String rawDate) {
        LocalDate localDate = LocalDate.parse(rawDate, YYYY_MM_DD_DATE_FORMATTER);
        return localDate.atStartOfDay();
    }

    public static String formatAsDate(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("MM/DD/yyyy").format(localDateTime);
    }
}
