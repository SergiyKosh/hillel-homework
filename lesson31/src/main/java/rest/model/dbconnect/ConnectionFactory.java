package rest.model.dbconnect;

import rest.util.PropertiesUtil;

public class ConnectionFactory {
    public static DbConnection build() {
        return new ConnectionPool();
    }
}
