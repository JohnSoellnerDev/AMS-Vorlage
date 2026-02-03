package de.bs1bt.ams.ui;

import de.bs1bt.ams.model.Gebaeude;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

public class GebaeudeDialogController extends Dialog<Gebaeude> {
    private Gebaeude gebaeudeModel;

    @FXML
    private TextField textFieldBezeichnung;
    @FXML
    private TextField textFieldAdresse;

    public Gebaeude getGebaeude() {
        gebaeudeModel.setBezeichnung(textFieldBezeichnung.getText());
        gebaeudeModel.setAdresse(textFieldAdresse.getText());
        return gebaeudeModel;
    }

    public void setGebaeude(Gebaeude gebaeudeModel) {
        this.gebaeudeModel = gebaeudeModel;
        textFieldBezeichnung.setText(gebaeudeModel.getBezeichnung());
        textFieldAdresse.setText(gebaeudeModel.getAdresse());
    }
}
