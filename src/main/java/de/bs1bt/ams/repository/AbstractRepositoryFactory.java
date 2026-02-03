package de.bs1bt.ams.repository;

public abstract class AbstractRepositoryFactory {

    public abstract RaumRepository createRaumRepository();

    public abstract GeraetRepository createGeraetRepository();

    public abstract GebaeudeRepository createGebaeudeRepository();
}
