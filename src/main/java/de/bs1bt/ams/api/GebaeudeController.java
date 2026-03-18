package de.bs1bt.ams.api;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.service.GebaeudeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gebaeude")
public class GebaeudeController {

    private final GebaeudeService gebaeudeService;

    @Autowired
    public GebaeudeController(GebaeudeService gebaeudeService) {
        this.gebaeudeService = gebaeudeService;
    }

    @GetMapping
    public List<Gebaeude> getAllGebaeude() {
        return gebaeudeService.getAllGebaeude();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gebaeude> getGebaeudeById(@PathVariable("id") int id) {
        return gebaeudeService.getGebaeudeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Gebaeude createGebaeude(@RequestBody Gebaeude gebaeude) {
        return gebaeudeService.createGebaeude(gebaeude);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gebaeude> updateGebaeude(@PathVariable("id") int id, @RequestBody Gebaeude gebaeudeDetails) {
        return gebaeudeService.updateGebaeude(id, gebaeudeDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGebaeude(@PathVariable("id") int id) {
        gebaeudeService.deleteGebaeude(id);
        return ResponseEntity.ok().build();
    }
}
