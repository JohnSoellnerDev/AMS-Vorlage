package de.bs1bt.algorithmen;

import de.bs1bt.algorithmen.task1_raumbelegung_sortieren.RaumBelegungsService;
import de.bs1bt.algorithmen.task1_raumbelegung_sortieren.Raum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task1RaumSortiererTest {

    private void ausgabe(String titel, Raum[] raeume) {
        System.out.println("### " + titel + " ###");
        for(Raum r : raeume) {
            System.out.println("Raum: " + r.getRaumNr() + " (" + r.getBelegung() + ")");
        }
    }

    private Raum[] beispielRaeume() {
        return new Raum[] {
                new Raum("A101", "A", 28, true),
                new Raum("B201", "B", 0,  false),
                new Raum("A102", "A", 12, true),
                new Raum("C301", "C", 5,  true),
                new Raum("B202", "B", 18, true)
        };
    }

    private int[] belegungen(Raum[] raeume) {
        int[] arr = new int[raeume.length];
        for (int i = 0; i < raeume.length; i++) {
            arr[i] = raeume[i].getBelegung();
        }
        return arr;
    }

    private int[] erwarteteBelegungenAufsteigend(Raum[] raeume) {
        int[] expected = belegungen(raeume);
        Arrays.sort(expected);
        return expected;
    }

    @Test
    void einfachAusgebenOhneTest() {
        Raum[] raeume = beispielRaeume();
        ausgabe("Beispielraum", raeume);
    }

    @Test
    void sortiereRaumArray_sortiertAufsteigendNachBelegung() {
        Raum[] raeume = beispielRaeume();
        int[] expected = erwarteteBelegungenAufsteigend(raeume);

        ausgabe("Vor dem Sortieren", raeume);
        RaumBelegungsService.sortiereRaumArray(raeume);
        ausgabe("Nach dem Sortieren", raeume);

        assertArrayEquals(expected, belegungen(raeume));
    }
}
