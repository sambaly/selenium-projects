package com.jitterted.moborg.adapter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatting {

    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static ZonedDateTime fromBrowserDateAndTime(String rawDate, String rawTime) {
        LocalDateTime localDateTime = LocalDateTime.parse(rawDate + " " + rawTime, YYYY_MM_DD_HH_MM_DATE_FORMATTER);
        return ZonedDateTime.of(localDateTime, ZoneId.of("Africa/Dakar"));
    }

    public static String formatAsDateTime(ZonedDateTime zonedDateTime) {
        return DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm a").format(zonedDateTime);
    }
}
