package de.bs1bt.ams.repository.factory;

import de.bs1bt.ams.repository.*;
import de.bs1bt.ams.repository.ram.GebaeudeRAMRepository;
import de.bs1bt.ams.repository.ram.GeraetRAMRepository;
import de.bs1bt.ams.repository.ram.RaumRAMRepository;

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
