package application.dbconnect;

import application.configurations.AppConfig;

public class ConnectionFactory {
    public static DbConnection build() {
        String type = AppConfig.getProperties("db.con.type");
        if (type.equals("simple")) {
            return new SimpleConnection();
        } else {
            return new ConnectionPool();
        }
    }
}
