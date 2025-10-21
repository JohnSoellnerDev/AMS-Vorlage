package de.bs1bt.ams.gateways;

import de.bs1bt.ams.singleton.DBCredentialsSingleton;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SQLDataGateway {
    private final Connection connection = DBCredentialsSingleton.getCredentials().getConnection();

    public void erstelleTabelle(String tabellenName, List<String> attribute) throws DataGatewayException {
        if (attribute == null || attribute.isEmpty()) {
            throw new IllegalArgumentException("Attribute dürfen nicht leer sein");
        }

        String columns = String.join(", ", attribute);
        String sqlQuery = String.format("CREATE TABLE `%s` (%s)", tabellenName, columns);

        System.out.println(sqlQuery);

        try {
            connection.prepareStatement(sqlQuery).executeUpdate();
        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        }
    }
}
