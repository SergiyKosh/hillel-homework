import entity.Journal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Correlations;
import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TableForAndPhiTest {
    private Correlations correlations;
    private String event;
    private List<Journal> journal;
    private List<Integer> expectedTable;

    @BeforeEach
    void initTable() throws IOException, URISyntaxException {
        correlations = new Correlations();
        journal = new Parser().getJournal();
        expectedTable = List.of(79, 6, 4, 1);
        event = "читал";
    }

    @Test
    void testTableFor() {
        //when
        List<Integer> resultTable = correlations.tableFor(event, journal);
        //then
        assertEquals(expectedTable, resultTable);
    }

    @Test
    void testPhi() {
        double expected = 0.11;
        //when
        double result = correlations.phi(expectedTable);
        //then
        assertEquals(expected, result, 1e-3);
    }
}
