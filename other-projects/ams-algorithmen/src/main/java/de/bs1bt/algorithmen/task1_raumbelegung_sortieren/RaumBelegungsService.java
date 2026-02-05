package de.bs1bt.algorithmen.task1_raumbelegung_sortieren;

public class RaumBelegungsService {

    /**
     * Sortiert ein Raum-Array aufsteigend nach Raum.getBelegung().
     * Erwartet ein unsortieres Raum-Array, das nach dem Aufruf sortiert ist (Call by Reference).
     * @param raeume in/out
     */
    public static void sortiereRaumArray(Raum[] raeume) {
        for (int i = 0; i < raeume.length - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < raeume.length; j++) {
                if (raeume[j].getBelegung() <= raeume[minIndex].getBelegung()) {
                    minIndex = j;
                }
            }

            var temp = raeume[i];
            raeume[i] = raeume[minIndex];
            raeume[minIndex] = temp;
        }
    }
}
