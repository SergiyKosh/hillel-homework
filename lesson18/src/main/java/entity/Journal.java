package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Journal {
    private List<String> events = new ArrayList<>();
    private boolean squirrel;

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public boolean isSquirrel() {
        return squirrel;
    }

    public void setSquirrel(boolean squirrel) {
        this.squirrel = squirrel;
    }

    @Override
    public String toString() {
        return String.format("events: %s, squirrel: %s", events, squirrel);
    }
}
