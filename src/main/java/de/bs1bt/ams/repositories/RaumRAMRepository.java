package de.bs1bt.ams.repositories;

import de.bs1bt.ams.model.Raum;

import java.util.ArrayList;
import java.util.List;

public class RaumRAMRepository implements RaumRepository {
    
    private final List<Raum> raeume = new ArrayList<>();
    private int nextId = 1;

    @Override
    public Raum hole(int id) throws RepositoryException {
        return raeume.stream()
                .filter(raum -> raum.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Es ist kein Raum mit der raum_id=" + id + " vorhanden."));
    }

    @Override
    public List<Raum> holeAlle() throws RepositoryException {
        return new ArrayList<>(raeume);
    }

    @Override
    public int erstelle(Raum raum) throws RepositoryException {
        raum.setId(nextId++);
        raeume.add(raum);
        return raum.getId();
    }

    @Override
    public void aktualisiere(Raum raum) throws RepositoryException {
        for (int i = 0; i < raeume.size(); i++) {
            if (raeume.get(i).getId() == raum.getId()) {
                raeume.set(i, raum);
                return;
            }
        }
        throw new RepositoryException("Raum mit ID " + raum.getId() + " nicht gefunden.");
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        boolean removed = raeume.removeIf(raum -> raum.getId() == id);
        if (!removed) {
            throw new RepositoryException("Raum mit ID " + id + " nicht gefunden.");
        }
    }

    @Override
    public void loesche(Raum raum) throws RepositoryException {
        loesche(raum.getId());
    }
    
    /**
     * Hilfsmethode zum Hinzufügen von Testdaten.
     * @param raum Der hinzuzufügende Raum
     */
    public void fuegeTestdatenHinzu(Raum raum) {
        if (raum.getId() <= 0) {
            raum.setId(nextId++);
        } else {
            nextId = Math.max(nextId, raum.getId() + 1);
        }
        raeume.add(raum);
    }
}

