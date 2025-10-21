package de.bs1bt.ams.gateways;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.singleton.DBCredentialsSingleton;

import java.sql.*;
import java.util.ArrayList;

public class RaumMySQLDataGateway {
    private final Connection connection = DBCredentialsSingleton.getCredentials().getConnection();

    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void erstelleTabelle() throws DataGatewayException {
        // Quelle: https://www.tutorialspoint.com/java_mysql/java_mysql_create_tables.htm
        var sqlQuery = "CREATE TABLE `raeume` (raum_id integer PRIMARY KEY AUTO_INCREMENT, " +
                "bezeichnung varchar(20), " +
                "gebaeude varchar(20), " +
                "laenge_in_cm double, " +
                "breite_in_cm double, " +
                "verantwortlicher varchar(20))";
        System.out.println(sqlQuery);
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        }
    }

    public void loescheTabelle() throws DataGatewayException {
        var sqlQuery = "DROP TABLE raeume";
        System.out.println(sqlQuery);
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        }
    }

    public Raum hole(int id) throws DataGatewayException {
        Raum raum = null;
        try {
            var sqlQuery = "SELECT * FROM raeume WHERE raum_id=?";
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next())
            {
                if(count > 0) {
                    // soweit sollte es bei unique PK nie kommen:
                    throw new DataGatewayException("Der Datensatz ist nicht einzigartig.");
                }

                raum = new Raum(resultSet.getInt("raum_id"),
                        resultSet.getString("bezeichnung"),
                        resultSet.getString("gebaeude"),
                        resultSet.getDouble("breite_in_cm"),
                        resultSet.getDouble("laenge_in_cm")
                );
                count++;
            }
            if ( 0 == count || null == raum) {
                throw new DataGatewayException("Es ist kein Raum mit der raum_id=" + id + " vorhanden.");
            }

        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // soweit sollte es bei bestehenden, validen Daten aus der DB nie kommen
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DataGatewayException(e.getMessage());
            }
        }

        return raum;
    }

    public ArrayList<Raum> holeAlle() throws DataGatewayException {
        ArrayList<Raum> raeumeListe = new ArrayList<Raum>();

        try {
            String sqlQuery = "SELECT * FROM raeume";
            preparedStatement = connection.prepareStatement(sqlQuery);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Raum raum = new Raum( resultSet.getInt("raum_id"),
                        resultSet.getString("bezeichnung"),
                        resultSet.getString("gebaeude"),
                        resultSet.getDouble("breite_in_cm"),
                        resultSet.getDouble("laenge_in_cm")
                );
                raeumeListe.add(raum);
            }
        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // soweit sollte es bei bestehenden, validen Daten aus der DB nie kommen
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DataGatewayException(e.getMessage());
            }
        }
        return raeumeListe;
    }

    public int erstelle(Raum raum) throws DataGatewayException {
        try {
            String sqlQuery = "INSERT INTO raeume (bezeichnung, gebaeude, laenge_in_cm, breite_in_cm) VALUES (?,?,?,?)";

            preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            preparedStatement.setString(++param, raum.getGebaeude());
            preparedStatement.setDouble(++param, raum.getLaengeInCm());
            preparedStatement.setDouble(++param, raum.getBreiteInCm());
            preparedStatement.executeUpdate();

            // get the last added ID
            ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
            if(generatedKeysResultSet.next()) {
                raum.setId( generatedKeysResultSet.getInt(1) );
                return raum.getId();
            }

        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DataGatewayException(e.getMessage());
            }
        }
        return -1;
    }

    public void aktualisiere(Raum raum) throws DataGatewayException {
        try {
            String sqlQuery = "UPDATE `raeume` SET bezeichnung=?, gebaeude=?, laenge_in_cm=?, breite_in_cm=? WHERE raum_id=?";

            preparedStatement = connection.prepareStatement(sqlQuery);
            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            preparedStatement.setString(++param, raum.getGebaeude());
            preparedStatement.setDouble(++param, raum.getLaengeInCm());
            preparedStatement.setDouble(++param, raum.getBreiteInCm());
            preparedStatement.setInt(++param, raum.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DataGatewayException(e.getMessage());
            }
        }
    }

    public void loesche(int id) throws DataGatewayException {

        try {
            String sqlQuery = "DELETE FROM raeume WHERE raum_id=?";

            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DataGatewayException(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new DataGatewayException(e.getMessage());
            }
        }
    }

    public void loesche(Raum raum) throws DataGatewayException {
        loesche(raum.getId());
    }
}
