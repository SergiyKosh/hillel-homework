package application.dbconnect;

import java.sql.Connection;

public interface DbConnection {
    Connection getConnection();
}
