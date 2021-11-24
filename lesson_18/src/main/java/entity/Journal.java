package entity;

import java.util.ArrayList;
import java.util.List;

public class Journal {
    private List<String> events = new ArrayList<>();
    private boolean squirrel;

    public List<String> getEvents() {
        return events;
    }

    public boolean isSquirrel() {
        return squirrel;
    }

    @Override
    public String toString() {
        return String.format("events: %s, squirrel: %s", events, squirrel);
    }
}
