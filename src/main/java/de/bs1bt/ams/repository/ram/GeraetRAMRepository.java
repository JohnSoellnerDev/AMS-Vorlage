package de.bs1bt.ams.repository.ram;

import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.repository.GeraetRepository;
import de.bs1bt.ams.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeraetRAMRepository implements GeraetRepository {

    private final List<Geraet> geraete = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Geraet hole(int id) throws RepositoryException {
        return geraete.stream()
                .filter(geraet -> geraet.getId() == id)
                .findFirst()
                .orElseThrow(
                        () -> new RepositoryException("Es ist kein Gerät mit der geraet_id=" + id + " vorhanden."));
    }

    @Override
    public List<Geraet> holeAlle() throws RepositoryException {
        return new ArrayList<>(geraete);
    }

    @Override
    public List<Geraet> holeNachRaumId(int raumId) throws RepositoryException {
        return geraete.stream()
                .filter(geraet -> geraet.getRaumId() == raumId)
                .collect(Collectors.toList());
    }

    @Override
    public int erstelle(Geraet geraet) throws RepositoryException {
        geraet.setId(nextId++);
        geraete.add(geraet);
        return geraet.getId();
    }

    @Override
    public void aktualisiere(Geraet geraet) throws RepositoryException {
        for (int i = 0; i < geraete.size(); i++) {
            if (geraete.get(i).getId() == geraet.getId()) {
                geraete.set(i, geraet);
                return;
            }
        }
        throw new RepositoryException("Gerät mit ID " + geraet.getId() + " nicht gefunden.");
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        boolean removed = geraete.removeIf(geraet -> geraet.getId() == id);
        if (!removed) {
            throw new RepositoryException("Gerät mit ID " + id + " nicht gefunden.");
        }
    }

    @Override
    public void loesche(Geraet geraet) throws RepositoryException {
        loesche(geraet.getId());
    }

    /**
     * Hilfsmethode zum Hinzufügen von Testdaten.
     * 
     * @param geraet Das hinzuzufügende Gerät
     */
    public void fuegeTestdatenHinzu(Geraet geraet) {
        if (geraet.getId() <= 0) {
            geraet.setId(nextId++);
        } else {
            nextId = Math.max(nextId, geraet.getId() + 1);
        }
        geraete.add(geraet);
    }
}
