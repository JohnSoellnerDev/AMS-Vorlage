package de.bs1bt.ams.repositories;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.singleton.DBCredentialsSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaumMySQLRepository implements RaumRepository {
    
    private Connection getConnection() {
        return DBCredentialsSingleton.getCredentials().getConnection();
    }

    public void erstelleTabelle() throws RepositoryException {
        var sqlQuery = "CREATE TABLE `raeume` (raum_id integer PRIMARY KEY AUTO_INCREMENT, " +
                "bezeichnung varchar(20), " +
                "gebaeude varchar(20), " +
                "laenge_in_cm double, " +
                "breite_in_cm double, " +
                "verantwortlicher varchar(20))";
        System.out.println(sqlQuery);
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public void loescheTabelle() throws RepositoryException {
        var sqlQuery = "DROP TABLE raeume";
        System.out.println(sqlQuery);
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Raum hole(int id) throws RepositoryException {
        Raum raum = null;
        var sqlQuery = "SELECT * FROM raeume WHERE raum_id=?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            
            preparedStatement.setInt(1, id);
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int count = 0;
                while (resultSet.next()) {
                    if (count > 0) {
                        throw new RepositoryException("Der Datensatz ist nicht einzigartig.");
                    }

                    raum = new Raum(
                            resultSet.getInt("raum_id"),
                            resultSet.getString("bezeichnung"),
                            resultSet.getString("gebaeude"),
                            resultSet.getDouble("breite_in_cm"),
                            resultSet.getDouble("laenge_in_cm")
                    );
                    count++;
                }
                
                if (count == 0 || raum == null) {
                    throw new RepositoryException("Es ist kein Raum mit der raum_id=" + id + " vorhanden.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RepositoryException("Fehler beim Laden des Raums: " + e.getMessage(), e);
        }

        return raum;
    }

    @Override
    public List<Raum> holeAlle() throws RepositoryException {
        List<Raum> raeumeListe = new ArrayList<>();
        String sqlQuery = "SELECT * FROM raeume";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
                Raum raum = new Raum(
                        resultSet.getInt("raum_id"),
                        resultSet.getString("bezeichnung"),
                        resultSet.getString("gebaeude"),
                        resultSet.getDouble("breite_in_cm"),
                        resultSet.getDouble("laenge_in_cm")
                );
                raeumeListe.add(raum);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        } catch (Exception e) {
            throw new RepositoryException("Fehler beim Laden der Räume: " + e.getMessage(), e);
        }
        
        return raeumeListe;
    }

    @Override
    public int erstelle(Raum raum) throws RepositoryException {
        String sqlQuery = "INSERT INTO raeume (bezeichnung, gebaeude, laenge_in_cm, breite_in_cm) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
            
            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            preparedStatement.setString(++param, raum.getGebaeude());
            preparedStatement.setDouble(++param, raum.getLaengeInCm());
            preparedStatement.setDouble(++param, raum.getBreiteInCm());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys()) {
                if (generatedKeysResultSet.next()) {
                    raum.setId(generatedKeysResultSet.getInt(1));
                    return raum.getId();
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
        
        return -1;
    }

    @Override
    public void aktualisiere(Raum raum) throws RepositoryException {
        String sqlQuery = "UPDATE `raeume` SET bezeichnung=?, gebaeude=?, laenge_in_cm=?, breite_in_cm=? WHERE raum_id=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            
            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            preparedStatement.setString(++param, raum.getGebaeude());
            preparedStatement.setDouble(++param, raum.getLaengeInCm());
            preparedStatement.setDouble(++param, raum.getBreiteInCm());
            preparedStatement.setInt(++param, raum.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        String sqlQuery = "DELETE FROM raeume WHERE raum_id=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(Raum raum) throws RepositoryException {
        loesche(raum.getId());
    }
}

