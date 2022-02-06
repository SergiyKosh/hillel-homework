package rest.model.dbconnect;

import java.sql.Connection;

public class ConnectionPool implements DbConnection {
    @Override
    public Connection getConnection() {
        return DBCPDataSource.getConnection();
    }
}
