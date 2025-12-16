package de.bs1bt.algorithmen;

import de.bs1bt.algorithmen.task2_geraete_gruppieren.Geraet;
import de.bs1bt.algorithmen.task2_geraete_gruppieren.GeraeteService;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class Task2GeraeteBerichteTest {

    private Geraet[] beispielGeraete() {
        // entspricht inhaltlich Ihrer Beispiel-Tabelle (Drucker/Monitor/Netzwerkgerät)
        return new Geraet[] {
                new Geraet(1,  "Drucker",       false),
                new Geraet(12, "Drucker",       false),
                new Geraet(5,  "Drucker",       true),
                new Geraet(3,  "Monitor",       false),
                new Geraet(7,  "Monitor",       false),
                new Geraet(8,  "Netzwerkgerät", true),
                new Geraet(42, "Netzwerkgerät", false)
        };
    }

    @Test
    void aufgabeA_orderByBezeichnung_sortiertWieOrderBy() {
        Geraet[] geraete = beispielGeraete();

        GeraeteService.orderByBezeichnung(geraete);

        // Erwartung: Drucker..., Monitor..., Netzwerkgerät...
        assertEquals("Drucker", geraete[0].getBezeichnung());
        assertEquals("Drucker", geraete[1].getBezeichnung());
        assertEquals("Drucker", geraete[2].getBezeichnung());
        assertEquals("Monitor", geraete[3].getBezeichnung());
        assertEquals("Monitor", geraete[4].getBezeichnung());
        assertEquals("Netzwerkgerät", geraete[5].getBezeichnung());
        assertEquals("Netzwerkgerät", geraete[6].getBezeichnung());

        // Bei Gleichstand nach inventarnummer aufsteigend
        assertEquals(1, geraete[0].getInventarnummer());
        assertEquals(5, geraete[1].getInventarnummer());
        assertEquals(12, geraete[2].getInventarnummer());
    }

    @Test
    void aufgabeA() throws FileNotFoundException {
        Geraet[] geraete = beispielGeraete();
        GeraeteService.printBerichtA(geraete);
    }

    @Test
    void aufgabeB() throws FileNotFoundException {
        Geraet[] geraete = beispielGeraete();
        GeraeteService.printBerichtB(geraete);
    }

    @Test
    void aufgabeC() throws FileNotFoundException {
        Geraet[] geraete = beispielGeraete();
        GeraeteService.printBerichtC(geraete);
    }
}
