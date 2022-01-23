package application.dbconnect;


import application.configurations.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        AppConfig.getProperties("db.url"),
                        AppConfig.getProperties("db.username"),
                        AppConfig.getProperties("db.password")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
