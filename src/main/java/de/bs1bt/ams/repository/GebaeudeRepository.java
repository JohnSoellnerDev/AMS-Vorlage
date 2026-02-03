package de.bs1bt.ams.repository;

import de.bs1bt.ams.model.Gebaeude;
import java.util.List;

public interface GebaeudeRepository {
    Gebaeude hole(int id) throws RepositoryException;

    List<Gebaeude> holeAlle() throws RepositoryException;

    int erstelle(Gebaeude gebaeude) throws RepositoryException;

    void aktualisiere(Gebaeude gebaeude) throws RepositoryException;

    void loesche(int id) throws RepositoryException;

    void loesche(Gebaeude gebaeude) throws RepositoryException;
}
