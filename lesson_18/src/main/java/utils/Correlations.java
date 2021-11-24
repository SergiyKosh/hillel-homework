package utils;

import entity.Journal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Correlations extends HashMap {
    private Parser parser = new Parser();
    private List<Journal> journal = parser.getJournal();

    public Correlations() throws IOException, URISyntaxException {
    }

    public Double phi(List<Integer> table) {
        return (table.get(3) * table.get(0) - table.get(2) * table.get(1)) /
                Math.sqrt((table.get(2) + table.get(3)) *
                        (table.get(0) + table.get(1)) *
                        (table.get(1) + table.get(3)) *
                        (table.get(0) + table.get(2)));
    }

    public List<Integer> tableFor(String event, List<Journal> events) {
        List<Integer> matrix = new ArrayList<>(List.of(0, 0, 0, 0));
        events.forEach(x -> {
            int index = 0;
            if (x.getEvents().contains(event)) {
                index += 1;
            }
            if (x.isSquirrel()) {
                index += 2;
            }
            matrix.set(index, matrix.get(index) + 1);
        });
        return matrix;
    }

    private List<String> getAllEvents() {
        return journal.stream()
                .map(Journal::getEvents)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    public Map<String, Double> getCorrelations() {
        return getAllEvents().stream()
                .collect(Collectors.toMap(Function.identity(),
                        event -> phi(tableFor(event, journal))));
    }

    public Map<String, Double> getFinalCorrelations() {
        return getCorrelations().entrySet().stream()
                .filter(x -> x.getValue() > 0.1 || x.getValue() < -0.1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<String, Double> getMinCorrelation() {
        return getFinalCorrelations().entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(entry -> Map.of(entry.getKey(), entry.getValue()))
                .orElseThrow(RuntimeException::new);
    }

    public Map<String, Double> getMaxCorrelation() {
        return getFinalCorrelations().entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(entry -> Map.of(entry.getKey(), entry.getValue()))
                .orElseThrow(RuntimeException::new);
    }

    public List<Journal> getFoundedCorrelations() {
        String minCorr = new ArrayList<>(getMinCorrelation().keySet()).get(0);
        String maxCorr = new ArrayList<>(getMaxCorrelation().keySet()).get(0);
        return journal.stream()
                .peek(x -> {
                    if (x.getEvents().contains(maxCorr) && !x.getEvents().contains(minCorr)) {
                        x.getEvents().add(maxCorr + "-" + minCorr);
                    }
                })
                .collect(Collectors.toList());
    }
}
