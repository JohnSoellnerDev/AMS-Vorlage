package de.bs1bt.ams.model;

/**
 * Repräsentiert ein Gerät in einem Raum.
 */
public class Geraet {

    private int id;
    private String bezeichnung;
    private String typ;
    private int raumId;
    private String inventarnummer;

    public Geraet() {
    }

    public Geraet(String bezeichnung, String typ) {
        setId(-1);
        setBezeichnung(bezeichnung);
        setTyp(typ);
        setRaumId(-1);
        setInventarnummer("");
    }

    public Geraet(int id, String bezeichnung, String typ, int raumId, String inventarnummer) {
        setId(id);
        setBezeichnung(bezeichnung);
        setTyp(typ);
        setRaumId(raumId);
        setInventarnummer(inventarnummer);
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

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getRaumId() {
        return raumId;
    }

    public void setRaumId(int raumId) {
        this.raumId = raumId;
    }

    public String getInventarnummer() {
        return inventarnummer;
    }

    public void setInventarnummer(String inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    @Override
    public String toString() {
        return "Geraet{" +
                "id=" + id +
                ", bezeichnung='" + bezeichnung + '\'' +
                ", typ='" + typ + '\'' +
                ", raumId=" + raumId +
                ", inventarnummer='" + inventarnummer + '\'' +
                '}';
    }
}
