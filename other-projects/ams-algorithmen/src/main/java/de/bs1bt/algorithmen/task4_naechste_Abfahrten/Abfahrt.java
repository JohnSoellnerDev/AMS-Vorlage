package de.bs1bt.algorithmen.task4_naechste_Abfahrten;

public class Abfahrt {
    private final int verbindungsId;
    private final int streckenAbschnitt;
    private final DateTime abfahrtsZeit;

    public Abfahrt(int verbindungsId, int streckenAbschnitt, DateTime abfahrtsZeit) {
        this.verbindungsId = verbindungsId;
        this.streckenAbschnitt = streckenAbschnitt;
        this.abfahrtsZeit = abfahrtsZeit;
    }

    public int getVerbindungsId() {
        return verbindungsId;
    }

    public int getStreckenAbschnitt() {
        return streckenAbschnitt;
    }

    public DateTime getAbfahrtsZeit() {
        return abfahrtsZeit;
    }

    @Override
    public String toString() {
        return "Abfahrt{id=" + verbindungsId + ", abschnitt=" + streckenAbschnitt + ", zeit=" + abfahrtsZeit + "}";
    }
}
