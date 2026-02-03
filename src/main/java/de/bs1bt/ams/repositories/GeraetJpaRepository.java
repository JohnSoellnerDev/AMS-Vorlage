package de.bs1bt.ams.repositories;

import de.bs1bt.ams.model.Geraet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeraetJpaRepository extends JpaRepository<Geraet, Integer> {
    List<Geraet> findByRaumId(int raumId);
}
