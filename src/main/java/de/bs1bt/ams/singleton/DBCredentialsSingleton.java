package de.bs1bt.ams.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCredentialsSingleton {

    private static DBCredentialsSingleton instance;

    private final Connection connection;

    private DBCredentialsSingleton() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ams_fx_test", "schueler", "Geheim01");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBCredentialsSingleton getCredentials() {
        if (instance == null) {
            instance = new DBCredentialsSingleton();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
