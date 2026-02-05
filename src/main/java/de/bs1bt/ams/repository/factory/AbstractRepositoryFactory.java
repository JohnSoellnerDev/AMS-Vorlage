package de.bs1bt.ams.repository.factory;

import de.bs1bt.ams.repository.GebaeudeRepository;
import de.bs1bt.ams.repository.GeraetRepository;
import de.bs1bt.ams.repository.RaumRepository;

public abstract class AbstractRepositoryFactory {

    public abstract RaumRepository createRaumRepository();

    public abstract GeraetRepository createGeraetRepository();

    public abstract GebaeudeRepository createGebaeudeRepository();
}
