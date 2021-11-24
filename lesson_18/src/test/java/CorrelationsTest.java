import entity.Journal;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Correlations;
import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class CorrelationsTest {
    private static Correlations correlations;
    private static List<String> allEvents;
    private static List<Journal> journal;
    private static Map<String, Double> allCorrelations;
    private static Map<String, Double> minCorrelation;
    private static Map<String, Double> maxCorrelation;
    private static Map<String, Double> finalCorrelations;

    @BeforeAll
    static void setUp() throws IOException, URISyntaxException {
        Parser parser = new Parser();
        correlations = new Correlations();
        allEvents = correlations.getAllEvents();
        journal = parser.getJournal();
        allCorrelations = correlations.getCorrelations();
        minCorrelation = correlations.getMinCorrelation();
        maxCorrelation = correlations.getMaxCorrelation();
        finalCorrelations = correlations.getFinalCorrelations();

    }

    @Test
    void testJournalSize() {
        assertEquals(90, journal.size());
    }

    @Test
    void notContainsNullInTheJournal() {
        assertFalse(journal.contains(null));
    }

    @Test
    void isContainsRandomEventInTheAllEvents() {
        int randomIndex = IntStream
                .range(0, allEvents.size())
                .limit(1)
                .map(num -> ThreadLocalRandom.current().nextInt(0, allEvents.size()))
                .toArray()[0];
        assertTrue(allCorrelations.containsKey(allEvents.get(randomIndex)));
    }

    @Test
    void testAllEventsCount() {
        assertEquals(26, allEvents.size());
    }

    @Test
    void isMinAndMaxCorrelationNotNull() {
        assertNotNull(minCorrelation);
        assertNotNull(maxCorrelation);
    }

    @Test
    void isAllFinalCorrelationsBiggerThan01AndSmallerThanMinus01() {
        assertFalse(finalCorrelations.entrySet().stream().anyMatch(x -> x.getValue() < 0.1 && x.getValue() > -0.1));
    }

    @Test
    void isTheMinimumCorrelationCorrect() {
        assertTrue(minCorrelation.entrySet().stream().anyMatch(x -> x.getValue().equals(-0.3805211953235953)));
        assertTrue(minCorrelation.entrySet().stream().anyMatch(x -> x.getKey().equals("чистил зубы")));
    }

    @Test
    void isTheMaximumCorrelationCorrect() {
        assertTrue(maxCorrelation.entrySet().stream().anyMatch(x -> x.getValue().equals(0.59026798116852)));
        assertTrue(maxCorrelation.entrySet().stream().anyMatch(x -> x.getKey().equals("ел арахис")));
    }

    @Test
    void isTheFinalCorrelationValueCorrect() {
        List<Journal> foundedCorrelations = correlations.getFoundedCorrelations();
        assertEquals(1.000000, correlations.phi(correlations.tableFor("ел арахис-чистил зубы", foundedCorrelations)));
    }
}
