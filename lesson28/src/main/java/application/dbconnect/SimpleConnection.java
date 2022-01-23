package application.dbconnect;

import java.sql.Connection;

public class SimpleConnection implements DbConnection {
    @Override
    public Connection getConnection() {
        return DatabaseConnection.getConnection();
    }
}
