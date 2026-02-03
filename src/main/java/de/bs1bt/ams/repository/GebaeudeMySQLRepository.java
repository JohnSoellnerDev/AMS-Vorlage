package de.bs1bt.ams.repository;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.config.DBCredentialsSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GebaeudeMySQLRepository implements GebaeudeRepository {

    private Connection getConnection() {
        return DBCredentialsSingleton.getCredentials().getConnection();
    }

    public void erstelleTabelle() throws RepositoryException {
        var sqlQuery = "CREATE TABLE `gebaeude` (gebaeude_id integer PRIMARY KEY AUTO_INCREMENT, " +
                "bezeichnung varchar(50), " +
                "adresse varchar(100))";
        System.out.println(sqlQuery);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public void loescheTabelle() throws RepositoryException {
        var sqlQuery = "DROP TABLE gebaeude";
        System.out.println(sqlQuery);

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public Gebaeude hole(int id) throws RepositoryException {
        Gebaeude gebaeude;
        var sqlQuery = "SELECT * FROM gebaeude WHERE gebaeude_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    gebaeude = new Gebaeude();
                    gebaeude.setId(resultSet.getInt("gebaeude_id"));
                    gebaeude.setBezeichnung(resultSet.getString("bezeichnung"));
                    gebaeude.setAdresse(resultSet.getString("adresse"));
                } else {
                    throw new RepositoryException("Es ist kein Gebäude mit der id=" + id + " vorhanden.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
        return gebaeude;
    }

    @Override
    public List<Gebaeude> holeAlle() throws RepositoryException {
        List<Gebaeude> liste = new ArrayList<>();
        String sqlQuery = "SELECT * FROM gebaeude";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Gebaeude gebaeude = new Gebaeude();
                gebaeude.setId(resultSet.getInt("gebaeude_id"));
                gebaeude.setBezeichnung(resultSet.getString("bezeichnung"));
                gebaeude.setAdresse(resultSet.getString("adresse"));
                liste.add(gebaeude);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
        return liste;
    }

    @Override
    public int erstelle(Gebaeude gebaeude) throws RepositoryException {
        String sqlQuery = "INSERT INTO gebaeude (bezeichnung, adresse) VALUES (?,?)";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery,
                        Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, gebaeude.getBezeichnung());
            preparedStatement.setString(2, gebaeude.getAdresse());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys()) {
                if (generatedKeysResultSet.next()) {
                    gebaeude.setId(generatedKeysResultSet.getInt(1));
                    return gebaeude.getId();
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
        return -1;
    }

    @Override
    public void aktualisiere(Gebaeude gebaeude) throws RepositoryException {
        String sqlQuery = "UPDATE gebaeude SET bezeichnung=?, adresse=? WHERE gebaeude_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setString(1, gebaeude.getBezeichnung());
            preparedStatement.setString(2, gebaeude.getAdresse());
            preparedStatement.setInt(3, gebaeude.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        String sqlQuery = "DELETE FROM gebaeude WHERE gebaeude_id=?";

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void loesche(Gebaeude gebaeude) throws RepositoryException {
        loesche(gebaeude.getId());
    }
}
