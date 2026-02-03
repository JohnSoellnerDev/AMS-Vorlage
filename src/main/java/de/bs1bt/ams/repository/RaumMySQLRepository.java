package de.bs1bt.ams.repository;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.config.DBCredentialsSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaumMySQLRepository implements RaumRepository {

    private Connection getConnection() {
        return DBCredentialsSingleton.getCredentials().getConnection();
    }

    public void erstelleTabelle() throws RepositoryException {
        // Updated to use gebaeude_id
        var sqlQuery = "CREATE TABLE `raeume` (raum_id integer PRIMARY KEY AUTO_INCREMENT, " +
                "bezeichnung varchar(20), " +
                "gebaeude_id integer, " +
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
        // JOIN to get Gebaeude details
        var sqlQuery = "SELECT r.*, g.bezeichnung as geb_bez, g.adresse as geb_adr " +
                "FROM raeume r LEFT JOIN gebaeude g ON r.gebaeude_id = g.gebaeude_id " +
                "WHERE r.raum_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int count = 0;
                while (resultSet.next()) {
                    if (count > 0) {
                        throw new RepositoryException("Der Datensatz ist nicht einzigartig.");
                    }

                    Gebaeude gebaeude = null;
                    int gebId = resultSet.getInt("gebaeude_id");
                    if (!resultSet.wasNull() && gebId > 0) {
                        gebaeude = new Gebaeude();
                        gebaeude.setId(gebId);
                        gebaeude.setBezeichnung(resultSet.getString("geb_bez"));
                        gebaeude.setAdresse(resultSet.getString("geb_adr"));
                    }

                    raum = new Raum(
                            resultSet.getInt("raum_id"),
                            resultSet.getString("bezeichnung"),
                            gebaeude,
                            resultSet.getDouble("breite_in_cm"),
                            resultSet.getDouble("laenge_in_cm"));
                    count++;
                }

                if (count == 0) {
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
        var sqlQuery = "SELECT r.*, g.bezeichnung as geb_bez, g.adresse as geb_adr " +
                "FROM raeume r LEFT JOIN gebaeude g ON r.gebaeude_id = g.gebaeude_id";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Gebaeude gebaeude = null;
                int gebId = resultSet.getInt("gebaeude_id");
                if (!resultSet.wasNull() && gebId > 0) {
                    gebaeude = new Gebaeude();
                    gebaeude.setId(gebId);
                    gebaeude.setBezeichnung(resultSet.getString("geb_bez"));
                    gebaeude.setAdresse(resultSet.getString("geb_adr"));
                }

                Raum raum = new Raum(
                        resultSet.getInt("raum_id"),
                        resultSet.getString("bezeichnung"),
                        gebaeude,
                        resultSet.getDouble("breite_in_cm"),
                        resultSet.getDouble("laenge_in_cm"));
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
        String sqlQuery = "INSERT INTO raeume (bezeichnung, gebaeude_id, laenge_in_cm, breite_in_cm) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
                        Statement.RETURN_GENERATED_KEYS)) {

            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            if (raum.getGebaeude() != null) {
                preparedStatement.setInt(++param, raum.getGebaeude().getId());
            } else {
                preparedStatement.setNull(++param, Types.INTEGER);
            }
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
        String sqlQuery = "UPDATE `raeume` SET bezeichnung=?, gebaeude_id=?, laenge_in_cm=?, breite_in_cm=? WHERE raum_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            int param = 0;
            preparedStatement.setString(++param, raum.getBezeichnung());
            if (raum.getGebaeude() != null) {
                preparedStatement.setInt(++param, raum.getGebaeude().getId());
            } else {
                preparedStatement.setNull(++param, Types.INTEGER);
            }
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
