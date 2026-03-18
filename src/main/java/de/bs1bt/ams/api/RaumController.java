package de.bs1bt.ams.api;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.service.RaumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raeume")
public class RaumController {

    private final RaumService raumService;

    @Autowired
    public RaumController(RaumService raumService) {
        this.raumService = raumService;
    }

    @GetMapping
    public List<Raum> getAllRaeume() {
        return raumService.getAllRaeume();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Raum> getRaumById(@PathVariable("id") int id) {
        return raumService.getRaumById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Raum createRaum(@RequestBody Raum raum) {
        return raumService.createRaum(raum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Raum> updateRaum(@PathVariable("id") int id, @RequestBody Raum raumDetails) {
        return raumService.updateRaum(id, raumDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRaum(@PathVariable("id") int id) {
        raumService.deleteRaum(id);
        return ResponseEntity.ok().build();
    }
}
