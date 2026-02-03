package de.bs1bt.ams.api;

import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.service.GeraetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/geraete")
public class GeraetController {

    private final GeraetService geraetService;

    @Autowired
    public GeraetController(GeraetService geraetService) {
        this.geraetService = geraetService;
    }

    @GetMapping
    public List<Geraet> getAllGeraete() {
        return geraetService.getAllGeraete();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Geraet> getGeraetById(@PathVariable Integer id) {
        return geraetService.getGeraetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Geraet createGeraet(@RequestBody Geraet geraet) {
        return geraetService.createGeraet(geraet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Geraet> updateGeraet(@PathVariable int id, @RequestBody Geraet geraetDetails) {
        return geraetService.updateGeraet(id, geraetDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeraet(@PathVariable int id) {
        geraetService.deleteGeraet(id);
        return ResponseEntity.ok().build();
    }
}
