package application.dbconnect;

import application.configurations.AppConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBCPDataSource {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(AppConfig.getProperties("db.url"));
        ds.setUsername(AppConfig.getProperties("db.username"));
        ds.setPassword(AppConfig.getProperties("db.password"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private DBCPDataSource() {}
}
