package de.bs1bt.algorithmen;

import de.bs1bt.algorithmen.task4_naechste_Abfahrten.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task4NaechsteAbfahrtenTest {

    @Test
    void testGetNaechsteAbfahrten_mitBeispieldaten() {

        FahrplanService service = new FahrplanService();
        FahrplanController controller = new FahrplanController(service);

        Abfahrt[] next = controller.getNaechsteAbfahrten(250, 3);

        // Beispielausgabe
        System.out.println("Nächste Abfahrten ab " + DateTime.now() + " (Fake-aktuelles Datum):");
        for (Abfahrt a : next) {
            System.out.println(a);
        }

        // Assertions (automatisches Feedback) ----
        assertNotNull(next[0]);
        assertNotNull(next[1]);
        assertNotNull(next[2]);

        assertEquals(1003, next[0].getVerbindungsId()); // 10:30
        assertEquals(1004, next[1].getVerbindungsId()); // 10:45
        assertEquals(1005, next[2].getVerbindungsId()); // 11:00
    }
}
