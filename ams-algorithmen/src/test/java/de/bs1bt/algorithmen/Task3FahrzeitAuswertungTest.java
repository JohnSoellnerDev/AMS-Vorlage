package de.bs1bt.algorithmen;

import de.bs1bt.algorithmen.task3_abfahrtzeiten_verspaetungen.Abfahrtszeit;
import de.bs1bt.algorithmen.task3_abfahrtzeiten_verspaetungen.FahrzeitAuswertung;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3FahrzeitAuswertungTest {

    @Test
    void testErmittleFahrzeiten_mitBeispieldaten() {

        Abfahrtszeit[] daten = new Abfahrtszeit[] {

                // ===== 01.09.2024 =====
                new Abfahrtszeit(LocalDate.of(2024, 9, 1), 1, 480, 480),
                new Abfahrtszeit(LocalDate.of(2024, 9, 1), 2, 483, 489), // +6 statt +3 → Treffer HST 2
                new Abfahrtszeit(LocalDate.of(2024, 9, 1), 3, 485, 491), // +2 statt +2 → kein Treffer
                new Abfahrtszeit(LocalDate.of(2024, 9, 1), 4, 488, 497), // +6 statt +3 → Treffer HST 4
                new Abfahrtszeit(LocalDate.of(2024, 9, 1), 5, 490, 497), // +0 statt +2 → kein Treffer

                // ===== 02.09.2024 =====
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 1, 480, 482),
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 2, 483, 490), // +8 statt +3 → Treffer HST 2
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 3, 485, 493), // +3 statt +2 → kein Treffer
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 4, 488, 499), // +6 statt +3 → Treffer HST 4
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 5, 490, 501), // +3 statt +2 → kein Treffer
                new Abfahrtszeit(LocalDate.of(2024, 9, 2), 6, 493, 505)  // +4 statt +3 → kein Treffer
        };

        Integer[] ergebnis = FahrzeitAuswertung.ermittleFahrzeiten(daten);

        // ---- Sichtbare Beispielausgabe (für Schüler) ----
        System.out.println("Verspätungen je Haltestelle:");
        for (int i = 0; i < ergebnis.length; i++) {
            if (ergebnis[i] > 0) {
                System.out.printf("Haltestelle %d: %d%n", i + 1, ergebnis[i]);
            }
        }

        // ---- Assertions (automatisches Feedback) ----
        assertEquals(2, ergebnis[1]); // Haltestelle 2
        assertEquals(2, ergebnis[3]); // Haltestelle 4
        assertEquals(0, ergebnis[0]); // Haltestelle 1
        assertEquals(0, ergebnis[2]); // Haltestelle 3
        assertEquals(0, ergebnis[4]); // Haltestelle 5
    }
}
