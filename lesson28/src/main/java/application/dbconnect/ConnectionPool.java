package application.dbconnect;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ConnectionPool implements DbConnection {
    @Override
    public Connection getConnection() {
        return DBCPDataSource.getConnection();
    }
}

