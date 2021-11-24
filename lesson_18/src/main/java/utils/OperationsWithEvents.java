package utils;

import entity.Journal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OperationsWithEvents {
    private Parser parser = new Parser();
    List<Journal> journal = parser.getJournal();

    public OperationsWithEvents() throws IOException, URISyntaxException {
    }

    public List<String> getAllEvents() {
        return journal.stream()
                .map(Journal::getEvents)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private Map<String, Double> getCorrelations() {
        return getAllEvents().stream().collect(Collectors.toMap(Function.identity(),
                event -> {
                    Phi phi = new Phi();
                    return phi.phi(phi.tableFor(event, journal));
                }));
    }

    private Map<String, Double> getFinalCorrelations() {
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
                .filter(x -> x.getEvents().contains(maxCorr) && !x.getEvents().contains(minCorr))
                .map(x -> {
                    List<String> lst = new ArrayList<>(x.getEvents());
                    lst.add(maxCorr + "-" + minCorr);
                    return lst;
                })
                .map(x -> {
                    int index = journal.indexOf(journal.stream()
                            .filter(y -> y.getEvents().contains(maxCorr) && !y.getEvents().contains(minCorr) && !y.getEvents().contains(maxCorr + "-" + minCorr))
                            .findFirst()
                            .orElseThrow(RuntimeException::new));
                    journal.set(index, new Journal(x, journal.get(index).isSquirrel()));
                    return journal;
                })
                .flatMap(Collection::stream) //ошибка где-то здесь
                .collect(Collectors.toList());
    }
}
