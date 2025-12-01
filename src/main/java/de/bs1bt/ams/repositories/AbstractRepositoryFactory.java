package de.bs1bt.ams.repositories;

public abstract class AbstractRepositoryFactory {
    
    public abstract RaumRepository createRaumRepository();
    
    public abstract GeraetRepository createGeraetRepository();
}

