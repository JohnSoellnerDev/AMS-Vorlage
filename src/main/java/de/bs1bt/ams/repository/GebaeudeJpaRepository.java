package de.bs1bt.ams.repository;

import de.bs1bt.ams.model.Gebaeude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GebaeudeJpaRepository extends JpaRepository<Gebaeude, Integer> {
}
