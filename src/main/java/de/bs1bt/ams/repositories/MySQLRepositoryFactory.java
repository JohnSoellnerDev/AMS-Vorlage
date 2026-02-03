package de.bs1bt.ams.repositories;

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
}
