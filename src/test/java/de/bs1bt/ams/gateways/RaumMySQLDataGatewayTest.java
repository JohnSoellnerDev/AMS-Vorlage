package de.bs1bt.ams.gateways;

import de.bs1bt.ams.model.Raum;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class RaumMySQLDataGatewayTest {

    @BeforeAll
    static void beforeAll() {
        RaumMySQLDataGateway raumGateway = new RaumMySQLDataGateway();
        try {
            raumGateway.loescheTabelle();
        } catch (DataGatewayException e) {
            // in Ordnung, falls Tabelle noch nicht existiert
        }
    }

    @Test
    void testKomplett() {
        RaumMySQLDataGateway raumGateway = new RaumMySQLDataGateway();
        try {
            raumGateway.erstelleTabelle();

            int id = raumGateway.erstelle(new Raum("U1", "IT-C"));
            id = raumGateway.erstelle(new Raum("U2", "IT-C"));

            ArrayList<Raum> raeume = raumGateway.holeAlle();
            assertEquals(2, raeume.size());

            assertEquals("U1", raeume.get(0).getBezeichnung());
            assertEquals("IT-C", raeume.get(0).getGebaeude());
            assertEquals("U2", raeume.get(1).getBezeichnung());
            assertEquals("IT-C", raeume.get(1).getGebaeude());

            // hole zuletzt erstellen Datensatz
            Raum raum = raumGateway.hole(id);
            assertEquals("U2", raum.getBezeichnung());
            assertEquals("IT-C", raum.getGebaeude());


            // Erstelle Raum zum Aktualisieren und späteren Löschen
            Raum raumNeu = new Raum("121", "Hauptgebäude");
            id = raumGateway.erstelle(raumNeu);
            // Test über Auslesen
            Raum raumVergleich = raumGateway.hole(id);
            assertEquals("121", raumVergleich.getBezeichnung());
            assertEquals("Hauptgebäude", raumVergleich.getGebaeude());
            assertEquals(3, raumGateway.holeAlle().size());

            // aktualisiere Raum
            raumNeu.setBezeichnung("123");
            raumGateway.aktualisiere(raumNeu);
            raumVergleich = raumGateway.hole(id);
            assertEquals("123", raumVergleich.getBezeichnung());

            raumGateway.loesche(raumVergleich);

            assertEquals(2, raumGateway.holeAlle().size());

            raumGateway.erstelle(new Raum("U3", "IT-C"));
            raumGateway.erstelle(new Raum("U4", "IT-C"));
            raumGateway.erstelle(new Raum("U5", "IT-C"));
            raumGateway.erstelle(new Raum("U6", "IT-C"));
            raumGateway.erstelle(new Raum("U7", "IT-C"));

            assertEquals(7, raumGateway.holeAlle().size());
        } catch (DataGatewayException e) {
            fail(e.getMessage());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
