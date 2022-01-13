import org.junit.jupiter.api.Test;
import utils.DatabaseConnection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestDatabaseConnection {
    @Test
    void testGetConnection() {
        assertNotNull(DatabaseConnection.getConnection());
    }
}
