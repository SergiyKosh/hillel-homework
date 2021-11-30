import entity.Journal;
import org.junit.jupiter.api.Test;
import utils.Parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {
    @Test
    void testJournal() throws IOException, URISyntaxException {
        //given
        Parser parser = new Parser();
        List<Journal> journal = parser.getJournal();
        //when
        boolean isContainsNull = journal.contains(null);
        //then
        assertFalse(journal.isEmpty());
        assertFalse(isContainsNull);
    }
}
