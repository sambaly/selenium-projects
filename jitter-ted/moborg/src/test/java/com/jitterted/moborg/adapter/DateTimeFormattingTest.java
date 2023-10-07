package com.jitterted.moborg.adapter;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class DateTimeFormattingTest {

    @Test
    public void browserFormattedDateAndTimeAreConvertedToZonedDateTime() throws Exception {
        String rawDate = "2021-04-30";
        String rawTime = "09:00";

        ZonedDateTime zonedDateTime = DateTimeFormatting.fromBrowserDateAndTime(rawDate, rawTime);

        assertThat(zonedDateTime)
                .isEqualTo(
                        ZonedDateTime.of(2021, 4, 30,
                                9, 0, 0, 0,
                                ZoneId.of("Africa/Dakar")));
    }

}