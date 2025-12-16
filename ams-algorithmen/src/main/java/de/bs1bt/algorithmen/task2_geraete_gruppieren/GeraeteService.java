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
        // TODO Aufgabe 2b – GROUP BY
        //
        // - Gehen Sie davon aus, dass das Geräte-Array bereits sortiert ist.
        // - Ermitteln Sie für jede Bezeichnung die Anzahl der Geräte.
        // - Nutzen Sie keine Maps oder Collections.
        // - Erkennen Sie Gruppenwechsel beim Durchlaufen des Arrays.

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

        // TODO Aufgabe 2b – GROUP BY
        //
        // - Gehen Sie davon aus, dass das Geräte-Array bereits sortiert ist.
        // - Ermitteln Sie für jede Bezeichnung die Anzahl der Geräte.
        // - Nutzen Sie keine Maps oder Collections.
        // - Erkennen Sie Gruppenwechsel beim Durchlaufen des Arrays.
        //
        // Hinweis: Nutzen Sie zum Drucken der Überschriften und Zeilen die obigen Methoden
        // - printHeadlineB();
        // - printZeileB(aktuelleBez, anzahl);

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


        // TODO Aufgabe 2c – GROUP BY mit Aggregaten
        //
        // - Ermitteln Sie pro Bezeichnung:
        //   * Gesamtanzahl
        //   * Anzahl defekter Geräte
        //   * Anzahl nicht defekter Geräte
        // - Nutzen Sie weiterhin das sortierte Array.
        // - Denken Sie an den letzten Gruppenwechsel am Ende des Arrays.
        //
        // Hinweis: Nutzen Sie zum Drucken der Überschriften und Zeilen die obigen Methoden
        // - printHeadlineC();
        // - printZeileC(aktuelleBez, anzahl, defekt, nichtDefekt);

    }
}
