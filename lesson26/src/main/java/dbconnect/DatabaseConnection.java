package dbconnect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                try {
                    connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
