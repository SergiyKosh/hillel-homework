package utils;

import entity.Journal;

import java.util.*;
import java.util.stream.Collectors;

public class OperationsWithEvents {
    public List<String> getAllEvents(List<Journal> events) {
        return events.stream()
                .map(Journal::getEvents)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
