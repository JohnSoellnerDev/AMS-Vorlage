package de.bs1bt.ams.repositories;

import de.bs1bt.ams.model.Geraet;

import java.util.List;

public interface GeraetRepository {

    Geraet hole(int id) throws RepositoryException;

    List<Geraet> holeAlle() throws RepositoryException;

    List<Geraet> holeNachRaumId(int raumId) throws RepositoryException;

    int erstelle(Geraet geraet) throws RepositoryException;

    void aktualisiere(Geraet geraet) throws RepositoryException;

    void loesche(int id) throws RepositoryException;

    void loesche(Geraet geraet) throws RepositoryException;
}
