package de.bs1bt.ams.repositories;

import de.bs1bt.ams.model.Raum;

import java.util.List;

public interface RaumRepository {
    
    Raum hole(int id) throws RepositoryException;
    
    List<Raum> holeAlle() throws RepositoryException;

    int erstelle(Raum raum) throws RepositoryException;

    void aktualisiere(Raum raum) throws RepositoryException;

    void loesche(int id) throws RepositoryException;

    void loesche(Raum raum) throws RepositoryException;
}

