package de.bs1bt.ams.repository.jpa;

import de.bs1bt.ams.model.Raum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RaumJpaRepository extends JpaRepository<Raum, Integer> {
    List<Raum> findByGebaeudeId(int gebaeudeId);
}
