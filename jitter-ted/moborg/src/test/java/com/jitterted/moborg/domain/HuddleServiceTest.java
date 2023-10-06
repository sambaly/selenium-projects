package com.jitterted.moborg.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.time.ZonedDateTime;

class HuddleServiceScheduleTest {

    @Test
    public void scheduleddHuddleIsReturnedForAllHuddles() throws Exception {
        HuddleService huddleService = new HuddleService(new InMemoryHuddleRepository());

        huddleService.scheduleHuddle("Name", ZonedDateTime.now());

        assertThat(huddleService.allHuddles())
                .hasSize(1);
    }

}