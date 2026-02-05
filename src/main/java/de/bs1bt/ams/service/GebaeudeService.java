package de.bs1bt.ams.service;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.repository.jpa.GebaeudeJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GebaeudeService {

    private final GebaeudeJpaRepository gebaeudeRepository;

    @Autowired
    public GebaeudeService(GebaeudeJpaRepository gebaeudeRepository) {
        this.gebaeudeRepository = gebaeudeRepository;
    }

    public List<Gebaeude> getAllGebaeude() {
        return gebaeudeRepository.findAll();
    }

    public Optional<Gebaeude> getGebaeudeById(Integer id) {
        return gebaeudeRepository.findById(id);
    }

    public Gebaeude createGebaeude(Gebaeude gebaeude) {
        return gebaeudeRepository.save(gebaeude);
    }

    public Optional<Gebaeude> updateGebaeude(int id, Gebaeude gebaeudeDetails) {
        return gebaeudeRepository.findById(id).map(gebaeude -> {
            gebaeude.setBezeichnung(gebaeudeDetails.getBezeichnung());
            gebaeude.setAdresse(gebaeudeDetails.getAdresse());
            return gebaeudeRepository.save(gebaeude);
        });
    }

    public void deleteGebaeude(int id) {
        gebaeudeRepository.deleteById(id);
    }
}
