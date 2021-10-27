package com.example.entity;

import java.util.ArrayList;
import java.util.List;

public class DayJournal {
    private List<String> events = new ArrayList<>();
    private boolean squirrel;

    public DayJournal() {
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public void setSquirrel(boolean squirrel) {
        this.squirrel = squirrel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("events: ").append(events);
        sb.append(", squirrel: ").append(squirrel);
        return sb.toString();
    }
}
