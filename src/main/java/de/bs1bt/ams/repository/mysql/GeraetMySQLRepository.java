package de.bs1bt.ams.repository.mysql;

import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.config.DBCredentialsSingleton;
import de.bs1bt.ams.repository.GeraetRepository;
import de.bs1bt.ams.repository.RepositoryException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeraetMySQLRepository implements GeraetRepository {

    private Connection getConnection() {
        return DBCredentialsSingleton.getCredentials().getConnection();
    }

    public void erstelleTabelle() throws RepositoryException {
        var sqlQuery = "CREATE TABLE `geraete` (geraet_id integer PRIMARY KEY AUTO_INCREMENT, " +
                "bezeichnung varchar(50), " +
                "typ varchar(30), " +
                "raum_id integer, " +
                "inventarnummer varchar(30))";
        System.out.println(sqlQuery);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public void loescheTabelle() throws RepositoryException {
        var sqlQuery = "DROP TABLE geraete";
        System.out.println(sqlQuery);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Geraet hole(int id) throws RepositoryException {
        Geraet geraet = null;
        var sqlQuery = "SELECT * FROM geraete WHERE geraet_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                int count = 0;
                while (resultSet.next()) {
                    if (count > 0) {
                        throw new RepositoryException("Der Datensatz ist nicht einzigartig.");
                    }

                    geraet = new Geraet(
                            resultSet.getInt("geraet_id"),
                            resultSet.getString("bezeichnung"),
                            resultSet.getString("typ"),
                            resultSet.getInt("raum_id"),
                            resultSet.getString("inventarnummer"));
                    count++;
                }

                if (count == 0) {
                    throw new RepositoryException("Es ist kein Gerät mit der geraet_id=" + id + " vorhanden.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }

        return geraet;
    }

    @Override
    public List<Geraet> holeAlle() throws RepositoryException {
        List<Geraet> geraeteListe = new ArrayList<>();
        String sqlQuery = "SELECT * FROM geraete";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Geraet geraet = new Geraet(
                        resultSet.getInt("geraet_id"),
                        resultSet.getString("bezeichnung"),
                        resultSet.getString("typ"),
                        resultSet.getInt("raum_id"),
                        resultSet.getString("inventarnummer"));
                geraeteListe.add(geraet);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }

        return geraeteListe;
    }

    @Override
    public List<Geraet> holeNachRaumId(int raumId) throws RepositoryException {
        List<Geraet> geraeteListe = new ArrayList<>();
        String sqlQuery = "SELECT * FROM geraete WHERE raum_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, raumId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Geraet geraet = new Geraet(
                            resultSet.getInt("geraet_id"),
                            resultSet.getString("bezeichnung"),
                            resultSet.getString("typ"),
                            resultSet.getInt("raum_id"),
                            resultSet.getString("inventarnummer"));
                    geraeteListe.add(geraet);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }

        return geraeteListe;
    }

    @Override
    public int erstelle(Geraet geraet) throws RepositoryException {
        String sqlQuery = "INSERT INTO geraete (bezeichnung, typ, raum_id, inventarnummer) VALUES (?,?,?,?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
                        Statement.RETURN_GENERATED_KEYS)) {

            int param = 0;
            preparedStatement.setString(++param, geraet.getBezeichnung());
            preparedStatement.setString(++param, geraet.getTyp());
            preparedStatement.setInt(++param, geraet.getRaumId());
            preparedStatement.setString(++param, geraet.getInventarnummer());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys()) {
                if (generatedKeysResultSet.next()) {
                    geraet.setId(generatedKeysResultSet.getInt(1));
                    return geraet.getId();
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }

        return -1;
    }

    @Override
    public void aktualisiere(Geraet geraet) throws RepositoryException {
        String sqlQuery = "UPDATE `geraete` SET bezeichnung=?, typ=?, raum_id=?, inventarnummer=? WHERE geraet_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            int param = 0;
            preparedStatement.setString(++param, geraet.getBezeichnung());
            preparedStatement.setString(++param, geraet.getTyp());
            preparedStatement.setInt(++param, geraet.getRaumId());
            preparedStatement.setString(++param, geraet.getInventarnummer());
            preparedStatement.setInt(++param, geraet.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        String sqlQuery = "DELETE FROM geraete WHERE geraet_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(Geraet geraet) throws RepositoryException {
        loesche(geraet.getId());
    }
}
