import entity.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Correlations;
import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CorrelationsTest {
    private Correlations correlations;
    private List<Journal> journal;

    @BeforeEach
    void initTable() throws IOException, URISyntaxException {
        correlations = new Correlations();
        journal = new Parser().getJournal();
    }

    @Test
    void testGetAllEvents() {
        List<String> events = correlations.getAllEvents();
        //when
        boolean areAllElementsContainsInTheJournal = events.stream()
                .allMatch(x -> journal.stream()
                        .anyMatch(l -> l.getEvents().contains(x)));
        boolean areAllElementsDistinct = events.size() == events.stream().distinct().count();
        //then
        assertTrue(areAllElementsContainsInTheJournal);
        assertTrue(areAllElementsDistinct);
    }

    @Test
    void testGetCorrelations() {
        List<String> events = correlations.getAllEvents();
        Map<String, Double> allCorrelations = correlations.getCorrelations();
        int eventsSize = events.size();
        //when
        boolean areAllElementsContainsInTheAllCorrelationsMap = events.stream().allMatch(allCorrelations::containsKey);
        boolean isEventsSizeEqualToCorrelationsMapSize = eventsSize == allCorrelations.size();
        //then
        assertTrue(areAllElementsContainsInTheAllCorrelationsMap);
        assertTrue(isEventsSizeEqualToCorrelationsMapSize);
    }

    @Test
    void testGetFinalCorrelations() {
        //given
        List<String> events = correlations.getAllEvents();
        Map<String, Double> finalCorrelations = correlations.getFinalCorrelations();
        //when
        boolean areAllFinalCorrelationsBiggerThan01AndSmallerThanMinus01 = finalCorrelations.entrySet().stream()
                .allMatch(x -> x.getValue() > 0.1 || x.getValue() < -0.1);
        boolean isEventsListContainsAllFinalCorrelations = events.containsAll(finalCorrelations.keySet());
        //then
        assertTrue(areAllFinalCorrelationsBiggerThan01AndSmallerThanMinus01);
        assertTrue(isEventsListContainsAllFinalCorrelations);
    }

    @Test
    void testMinCorrelation() {
        Map<String, Double> allCorrelations = correlations.getCorrelations();
        int expectedSize = 1;
        Map<String, Double> minCorrelation = correlations.getMinCorrelation();
        double expectedMinCorrelation = allCorrelations.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .orElseThrow(RuntimeException::new);
        String expectedEventWithMinCorrelation = allCorrelations.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
        //when
        int sizeOfMapWithMinCorr = minCorrelation.size();
        boolean isMinCorrelationCorrect = correlations.getMinCorrelation().entrySet().stream()
                .allMatch(x -> x.getValue() == expectedMinCorrelation);
        boolean isEventWithMinCorrCorrect = correlations.getMinCorrelation().entrySet().stream()
                .allMatch(x -> x.getKey()
                        .equals(expectedEventWithMinCorrelation));
        //then
        assertEquals(expectedSize, sizeOfMapWithMinCorr);
        assertTrue(isMinCorrelationCorrect);
        assertTrue(isEventWithMinCorrCorrect);
    }

    @Test
    void testMaxCorrelation() {
        //given
        Map<String, Double> allCorrelations = correlations.getCorrelations();
        Map<String, Double> minCorrelation = correlations.getMinCorrelation();
        int expectedSize = 1;
        double expectedMaxCorrelation = allCorrelations.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getValue)
                .orElseThrow(RuntimeException::new);
        String expectedEventWithMaxCorrelation = allCorrelations.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(RuntimeException::new);
        //when
        int sizeOfMapWithMinCorr = minCorrelation.size();
        boolean isMaxCorrelationCorrect = correlations.getMaxCorrelation().entrySet().stream()
                .allMatch(x -> x.getValue() == expectedMaxCorrelation);
        boolean isEventWithMaxCorrCorrect = correlations.getMaxCorrelation().entrySet().stream()
                .allMatch(x -> x.getKey()
                        .equals(expectedEventWithMaxCorrelation));
        //then
        assertEquals(expectedSize, sizeOfMapWithMinCorr);
        assertTrue(isMaxCorrelationCorrect);
        assertTrue(isEventWithMaxCorrCorrect);
    }

    @Test
    void testFoundedCorrelation() {
        //given
        String foundedEvent = new ArrayList<>(correlations.getMaxCorrelation().keySet()).get(0) + "-" +
                new ArrayList<>(correlations.getMinCorrelation().keySet()).get(0);
        List<Journal> newJournal = correlations.getFoundedCorrelations();
        //when
        boolean isNewJournalContainsFoundedEvent = newJournal.stream()
                .anyMatch(x -> x.getEvents().contains(foundedEvent));
        boolean isNewJournalSizeEqualToOldJournal = journal.size() == newJournal.size();
        //then
        assertTrue(isNewJournalContainsFoundedEvent);
        assertTrue(isNewJournalSizeEqualToOldJournal);
    }

    @Test
    void isFoundedCorrelationCorrect() {
        //given
        String foundedEvent = new ArrayList<>(correlations.getMaxCorrelation().keySet()).get(0) + "-" +
                new ArrayList<>(correlations.getMinCorrelation().keySet()).get(0);
        double expectedCorrelation = 1.0;
        List<Journal> foundedCorrelations = correlations.getFoundedCorrelations();
        //when
        double result = correlations.phi(correlations.tableFor(foundedEvent, foundedCorrelations));
        //then
        assertEquals(expectedCorrelation, result, 1e-5);
    }
}