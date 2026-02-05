package de.bs1bt.ams.repository.factory;

import de.bs1bt.ams.repository.*;
import de.bs1bt.ams.repository.mysql.GebaeudeMySQLRepository;
import de.bs1bt.ams.repository.mysql.GeraetMySQLRepository;
import de.bs1bt.ams.repository.mysql.RaumMySQLRepository;

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
