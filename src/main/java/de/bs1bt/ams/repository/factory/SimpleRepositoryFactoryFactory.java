package de.bs1bt.ams.repository.factory;

import de.bs1bt.ams.repository.RepositoryType;

public class SimpleRepositoryFactoryFactory {
    
    public AbstractRepositoryFactory create(String type) {
        if ("DEV".equals(type)) {
            return new RAMRepositoryFactory();
        } else if ("PROD".equals(type)) {
            return new MySQLRepositoryFactory();
        } else {
            throw new IllegalArgumentException("Unbekannter Repository-Typ: " + type);
        }
    }
    
    public AbstractRepositoryFactory create(RepositoryType type) {
        if (type == null) {
            throw new IllegalArgumentException("Repository-Typ darf nicht null sein");
        }

        return switch (type) {
            case DEV -> new RAMRepositoryFactory();
            case PROD -> new MySQLRepositoryFactory();
            default -> throw new IllegalArgumentException("Unbekannter Repository-Typ: " + type);
        };
    }
}

