import entity.Journal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
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
    private String event;
    private List<Journal> journal;
    private List<Integer> expectedTable;
    private List<String> events;
    private Map<String, Double> allCorrelations;
    private int expectedSize;
    private String foundedEvent;

    void setUp() throws IOException, URISyntaxException {
        correlations = new Correlations();
        journal = new Parser().getJournal();
    }

    @BeforeEach
    void initTable() throws IOException, URISyntaxException {
        setUp();
        expectedTable = List.of(79, 6, 4, 1);
        event = "читал";
    }

    @Test
    void testTableFor() throws IOException, URISyntaxException {
        //given
        initTable();
        //when
        List<Integer> resultTable = correlations.tableFor(event, journal);
        //then
        assertEquals(expectedTable, resultTable);
    }

    @Test
    void testPhi() throws IOException, URISyntaxException {
        //given
        initTable();
        double expected = 0.11;
        //when
        double result = correlations.phi(expectedTable);
        //then
        assertEquals(expected, result, 1e-3);
    }

    @AfterEach

    @BeforeEach
    void initEventsList() throws IOException, URISyntaxException {
        setUp();
        events = correlations.getAllEvents();
    }

    @Test
    void testGetAllEvents() throws IOException, URISyntaxException {
        //given
        initEventsList();
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
    void testGetCorrelations() throws IOException, URISyntaxException {
        //given
        initEventsList();
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
    void testGetFinalCorrelations() throws IOException, URISyntaxException {
        //given
        initEventsList();
        Map<String, Double> finalCorrelations = correlations.getFinalCorrelations();
        //when
        boolean areAllFinalCorrelationsBiggerThan01AndSmallerThanMinus01 = finalCorrelations.entrySet().stream()
                .allMatch(x -> x.getValue() > 0.1 || x.getValue() < -0.1);
        boolean isEventsListContainsAllFinalCorrelations = events.containsAll(finalCorrelations.keySet());
        //then
        assertTrue(areAllFinalCorrelationsBiggerThan01AndSmallerThanMinus01);
        assertTrue(isEventsListContainsAllFinalCorrelations);
    }

    @AfterEach

    @BeforeEach
    void setParams() {
        allCorrelations = correlations.getCorrelations();
        expectedSize = 1;
    }

    @Test
    void testMinCorrelation() {
        //given
        setParams();
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

    @AfterEach

    @Test
    void testFoundedCorrelation() {
        //given
        foundedEvent = new ArrayList<>(correlations.getMaxCorrelation().keySet()).get(0) + "-" +
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
        foundedEvent = new ArrayList<>(correlations.getMaxCorrelation().keySet()).get(0) + "-" +
                new ArrayList<>(correlations.getMinCorrelation().keySet()).get(0);
        double expectedCorrelation = 1.0;
        List<Journal> foundedCorrelations = correlations.getFoundedCorrelations();
        //when
        double result = correlations.phi(correlations.tableFor(foundedEvent, foundedCorrelations));
        //then
        assertEquals(expectedCorrelation, result, 1e-5);
    }
}
