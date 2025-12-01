package de.bs1bt.ams.repositories;

public class RAMRepositoryFactory extends AbstractRepositoryFactory {
    
    public RAMRepositoryFactory() {

    }
    
    @Override
    public RaumRepository createRaumRepository() {
        return new RaumRAMRepository();
    }
    
    @Override
    public GeraetRepository createGeraetRepository() {
        // TODO: return new GeraetRAMRepository();
        return null;
    }
}

