package com.jitterted.moborg.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryHuddleRepository implements HuddleRepository {
    private final Map<HuddleId, Huddle> huddles = new HashMap<>();
    private final AtomicLong sequence = new AtomicLong(0);

    public Huddle save(Huddle huddle) {
        if (huddle.getId() == null) {
            huddle.setId(HuddleId.of(sequence.getAndIncrement()));
        }
        huddles.put(huddle.getId(), huddle);
        return huddle;
    }

    public List<Huddle> findAll() {
        return List.copyOf(huddles.values());
    }
}
