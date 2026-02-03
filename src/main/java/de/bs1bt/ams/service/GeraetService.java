package de.bs1bt.ams.service;

import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.repositories.GeraetJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeraetService {

    private final GeraetJpaRepository geraetRepository;

    @Autowired
    public GeraetService(GeraetJpaRepository geraetRepository) {
        this.geraetRepository = geraetRepository;
    }

    public List<Geraet> getAllGeraete() {
        return geraetRepository.findAll();
    }

    public Optional<Geraet> getGeraetById(int id) {
        return geraetRepository.findById(id);
    }

    public Geraet createGeraet(Geraet geraet) {
        return geraetRepository.save(geraet);
    }

    public Optional<Geraet> updateGeraet(int id, Geraet geraetDetails) {
        return geraetRepository.findById(id).map(geraet -> {
            geraet.setBezeichnung(geraetDetails.getBezeichnung());
            geraet.setTyp(geraetDetails.getTyp());
            geraet.setRaumId(geraetDetails.getRaumId());
            geraet.setInventarnummer(geraetDetails.getInventarnummer());
            return geraetRepository.save(geraet);
        });
    }

    public void deleteGeraet(int id) {
        geraetRepository.deleteById(id);
    }
}
