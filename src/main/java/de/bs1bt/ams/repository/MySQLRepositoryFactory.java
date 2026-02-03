package de.bs1bt.ams.repository;

public class MySQLRepositoryFactory extends AbstractRepositoryFactory {

    public MySQLRepositoryFactory() {
    }

    @Override
    public RaumRepository createRaumRepository() {
        return new RaumMySQLRepository();
    }

    @Override
    public GeraetRepository createGeraetRepository() {
        return new GeraetMySQLRepository();
    }

    @Override
    public GebaeudeRepository createGebaeudeRepository() {
        return new GebaeudeMySQLRepository();
    }
}
