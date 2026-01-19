package de.bs1bt.algorithmen.task2_geraete_gruppieren;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class GeraeteService {

    // Hilfsfunktionen
    private static void printHeadlineA() {
        System.out.println("Inventarnummer;Bezeichnung;defekt");
    }
    private static void printZeileA(int inventarnummer, String bezeichnung, boolean defekt) {
        System.out.println(inventarnummer + ";" + bezeichnung + ";" + defekt);
    }
    private static void printHeadlineB() {
        System.out.println("Bezeichnung;Anzahl");
    }
    private static void printZeileB(String bezeichnung, int anzahl) {
        System.out.println(bezeichnung + ";" + anzahl);
    }
    private static void printHeadlineC() {
        System.out.println("Bezeichnung;Anzahl;Defekt;Nicht defekt");
    }
    private static void printZeileC(String bezeichnung, int anzahl, int defekt, int nichtDefekt) {
        System.out.println(bezeichnung + ";" + anzahl + ";" + defekt + ";" + nichtDefekt);
    }

    /**
     * ORDER BY bezeichnung (aufsteigend), bei Gleichstand inventarnummer (aufsteigend).
     * Implementiert als BubbleSort.
     */
    public static void orderByBezeichnung(Geraet[] geraete) {
        for (int i = 0; i < geraete.length - 1; i++) {
            for (int j = 0; j < geraete.length - 1 - i; j++) {
                int vergleich = geraete[j].getBezeichnung().compareTo(geraete[j + 1].getBezeichnung());
                boolean isFrontNumberBigger = geraete[j].getInventarnummer() > geraete[j + 1].getInventarnummer();

                // Tausch wenn: Bezeichnung größer ODER Bezeichnung gleich und Inventarnummer größer
                if (vergleich > 0 || (vergleich == 0 && isFrontNumberBigger)) {
                    var temp = geraete[j];
                    geraete[j] = geraete[j + 1];
                    geraete[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Anzahl der Geräte pro Bezeichnung
     */
    public static void printBerichtA(Geraet[] geraete) throws FileNotFoundException {
        String title = "Bericht A";
        PrintStream fileOut = new PrintStream(title + ".csv");
        System.setOut(fileOut);
        if (geraete.length == 0) {
            System.out.println("Das zu sortierende Array ist leer.");
            return;
        }

        orderByBezeichnung(geraete);

        // Aufrufbeispiele der print-Funktionen
        printHeadlineA();
        for (Geraet g : geraete) {
            printZeileA(g.getInventarnummer(), g.getBezeichnung(), g.isDefekt());
        }
    }
    /**
     * Anzahl der Geräte pro Bezeichnung
     */
    public static void printBerichtB(Geraet[] geraete) throws FileNotFoundException {
        String title = "Bericht B";
        PrintStream fileOut = new PrintStream(title + ".csv");
        System.setOut(fileOut);

        if (geraete.length == 0) {
            System.out.println("Das zu sortierende Array ist leer.");
            return;
        }

        orderByBezeichnung(geraete);

        printHeadlineB();

        var aktuelleBezeichnung = geraete[0].getBezeichnung();
        var anzahl = 0;

        for (Geraet geraet : geraete) {
            if (!geraet.getBezeichnung().equals(aktuelleBezeichnung)) {
                printZeileB(aktuelleBezeichnung, anzahl);
                aktuelleBezeichnung = geraet.getBezeichnung();
                anzahl = 0;
            }
            anzahl++;
        }

        printZeileB(aktuelleBezeichnung, anzahl);
    }

    /**
     * Anzahl der Geräte pro Bezeichnung
     */
    public static void printBerichtC(Geraet[] geraete) throws FileNotFoundException {

        String title = "Bericht C";
        PrintStream fileOut = new PrintStream(title + ".csv");
        System.setOut(fileOut);

        if (geraete.length == 0) {
            System.out.println("Das zu sortierende Array ist leer.");
            return;
        }
        orderByBezeichnung(geraete);

        printHeadlineC();
        
        String aktuelleBezeichnung = geraete[0].getBezeichnung();
        int anzahl = 0;
        int defekt = 0;
        int nichtDefekt = 0;
        
        for (Geraet g : geraete) {
            if (!g.getBezeichnung().equals(aktuelleBezeichnung)) {
                // Gruppenwechsel: vorherige Gruppe ausgeben
                printZeileC(aktuelleBezeichnung, anzahl, defekt, nichtDefekt);
                aktuelleBezeichnung = g.getBezeichnung();
                anzahl = 0;
                defekt = 0;
                nichtDefekt = 0;
            }
            anzahl++;
            if (g.isDefekt()) {
                defekt++;
            } else {
                nichtDefekt++;
            }
        }
        // Letzte Gruppe ausgeben
        printZeileC(aktuelleBezeichnung, anzahl, defekt, nichtDefekt);
    }
}
