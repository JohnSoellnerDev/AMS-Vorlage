package de.bs1bt.ams.service;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.repository.jpa.GebaeudeJpaRepository;
import de.bs1bt.ams.repository.jpa.RaumJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RaumService {

    private final RaumJpaRepository raumRepository;
    private final GebaeudeJpaRepository gebaeudeRepository;

    @Autowired
    public RaumService(RaumJpaRepository raumRepository, GebaeudeJpaRepository gebaeudeRepository) {
        this.raumRepository = raumRepository;
        this.gebaeudeRepository = gebaeudeRepository;
    }

    public List<Raum> getAllRaeume() {
        return raumRepository.findAll();
    }

    public Optional<Raum> getRaumById(Integer id) {
        return raumRepository.findById(id);
    }

    public Raum createRaum(Raum raum) {
        return raumRepository.save(raum);
    }

    public Optional<Raum> updateRaum(int id, Raum raumDetails) {
        return raumRepository.findById(id).map(raum -> {
            raum.setBezeichnung(raumDetails.getBezeichnung());
            raum.setGebaeude(raumDetails.getGebaeude());
            try {
                raum.setBreiteInCm(raumDetails.getBreiteInCm());
                raum.setLaengeInCm(raumDetails.getLaengeInCm());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return raumRepository.save(raum);
        });
    }

    public void deleteRaum(int id) {
        raumRepository.deleteById(id);
    }
}
