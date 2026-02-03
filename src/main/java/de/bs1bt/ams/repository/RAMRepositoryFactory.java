package de.bs1bt.ams.repository;

public class RAMRepositoryFactory extends AbstractRepositoryFactory {

    public RAMRepositoryFactory() {

    }

    @Override
    public RaumRepository createRaumRepository() {
        return new RaumRAMRepository();
    }

    @Override
    public GeraetRepository createGeraetRepository() {
        return new GeraetRAMRepository();
    }

    @Override
    public GebaeudeRepository createGebaeudeRepository() {
        return new GebaeudeRAMRepository();
    }
}
