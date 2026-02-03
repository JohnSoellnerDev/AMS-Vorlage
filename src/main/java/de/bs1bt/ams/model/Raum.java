package de.bs1bt.ams.model;

public class Raum {

    private int id;
    private String bezeichnung;
    private String gebaeude;
    private double breiteInCm;
    private double laengeInCm;

    public Raum() {
    }

    public Raum(String bezeichnung, String gebaeude) throws Exception {
        setId(-1);
        setBezeichnung(bezeichnung);
        setGebaeude(gebaeude);
        setBreiteInCm(0);
        setLaengeInCm(0);
    }

    public Raum(int id, String bezeichnung, String gebaeude, double breiteInCm, double laengeInCm) throws Exception {
        setId(id);
        setBezeichnung(bezeichnung);
        setGebaeude(gebaeude);
        setBreiteInCm(breiteInCm);
        setLaengeInCm(laengeInCm);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getGebaeude() {
        return gebaeude;
    }

    public void setGebaeude(String gebaeude) {
        this.gebaeude = gebaeude;
    }

    public double getBreiteInCm() {
        return breiteInCm;
    }

    public void setBreiteInCm(double breiteInCm) throws Exception {
        if (laengeInCm < 0) {
            throw new Exception("Invalider Wert für Parameter breiteInCm (>0 cm!): " + breiteInCm);
        }
        this.breiteInCm = breiteInCm;
    }

    public double getLaengeInCm() {
        return laengeInCm;
    }

    public void setLaengeInCm(double laengeInCm) throws Exception {
        if (laengeInCm < 0) {
            throw new Exception("Invalider Wert für Parameter laengeInCm (>0 cm!): " + laengeInCm);
        }
        this.laengeInCm = laengeInCm;
    }

    public void setGroesse(double seitenLaengeInCm) throws Exception {
        setLaengeInCm(seitenLaengeInCm);
        setBreiteInCm(seitenLaengeInCm);
    }

    public void setGroesse(double laengeInCm, double breiteInCm) throws Exception {
        setLaengeInCm(laengeInCm);
        setBreiteInCm(breiteInCm);
    }

    /**
     * Berechnet die Fläche des Raums in Quadratmetern.
     * 
     * @return Die Fläche in qm (Länge * Breite, umgerechnet von cm² zu m²)
     */
    public double getFlaecheInQm() {
        // cm² zu m² umrechnen: Division durch 10000 (100 cm pro Meter, 100² = 10000)
        double flaecheInQm = (laengeInCm * breiteInCm) / 10000.0;
        return flaecheInQm;
    }

    @Override
    public String toString() {
        return "Raum{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", gebaeude='" + gebaeude + '\'' +
                ", breiteInCm=" + breiteInCm +
                ", laengeInCm=" + laengeInCm +
                ", flaecheInQm=" + getFlaecheInQm() +
                '}';
    }
}
