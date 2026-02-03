package de.bs1bt.ams.repository;

import de.bs1bt.ams.model.Gebaeude;
import java.util.ArrayList;
import java.util.List;

public class GebaeudeRAMRepository implements GebaeudeRepository {

    private final List<Gebaeude> gebaeudeListe;
    private int nextId;

    public GebaeudeRAMRepository() {
        gebaeudeListe = new ArrayList<>();
        nextId = 1;
    }

    @Override
    public Gebaeude hole(int id) throws RepositoryException {
        return gebaeudeListe.stream()
                .filter(g -> g.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RepositoryException("Gebäude nicht gefunden: " + id));
    }

    @Override
    public List<Gebaeude> holeAlle() throws RepositoryException {
        return new ArrayList<>(gebaeudeListe);
    }

    @Override
    public int erstelle(Gebaeude gebaeude) throws RepositoryException {
        gebaeude.setId(nextId++);
        gebaeudeListe.add(gebaeude);
        return gebaeude.getId();
    }

    @Override
    public void aktualisiere(Gebaeude gebaeude) throws RepositoryException {
        Gebaeude existing = hole(gebaeude.getId());
        existing.setBezeichnung(gebaeude.getBezeichnung());
        existing.setAdresse(gebaeude.getAdresse());
    }

    @Override
    public void loesche(int id) throws RepositoryException {
        gebaeudeListe.removeIf(g -> g.getId() == id);
    }

    @Override
    public void loesche(Gebaeude gebaeude) throws RepositoryException {
        loesche(gebaeude.getId());
    }
}
