package de.bs1bt.ams.ui;

import de.bs1bt.ams.model.Geraet;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class GeraetDialogController extends Dialog<Geraet> {
    private Geraet geraetModel;

    @FXML
    private TextField textFieldBezeichnung;
    @FXML
    private TextField textFieldTyp;
    @FXML
    private TextField textFieldRaumId;
    @FXML
    private TextField textFieldInventarnummer;

    public Geraet getGeraet() {
        geraetModel.setBezeichnung(textFieldBezeichnung.getText());
        geraetModel.setTyp(textFieldTyp.getText());
        try {
            geraetModel.setRaumId(Integer.parseInt(textFieldRaumId.getText()));
        } catch (NumberFormatException e) {
            geraetModel.setRaumId(-1); // Oder Fehlerbehandlung
        }
        geraetModel.setInventarnummer(textFieldInventarnummer.getText());
        return geraetModel;
    }

    public void setGeraet(Geraet geraetModel) {
        this.geraetModel = geraetModel;
        textFieldBezeichnung.setText(geraetModel.getBezeichnung());
        textFieldTyp.setText(geraetModel.getTyp());
        textFieldRaumId.setText(String.valueOf(geraetModel.getRaumId()));
        textFieldInventarnummer.setText(geraetModel.getInventarnummer());
    }
}
