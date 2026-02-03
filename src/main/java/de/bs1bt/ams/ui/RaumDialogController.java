package de.bs1bt.ams.ui;

import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.model.Raum;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class RaumDialogController extends Dialog<Raum> {
    private Raum raumModel;

    @FXML
    private TextField textFieldBezeichnung;
    @FXML
    private ComboBox<Gebaeude> comboBoxGebaeude;
    @FXML
    private TextField textFieldLaengeInCm;
    @FXML
    private TextField textFieldBreiteInCm;
    @FXML
    private Label labelFlaecheInQm;

    public void setMoeglicheGebaeude(List<Gebaeude> gebaeudeList) {
        comboBoxGebaeude.getItems().setAll(gebaeudeList);
    }

    public Raum getRaum() throws Exception {
        raumModel.setBezeichnung(textFieldBezeichnung.getText());
        raumModel.setGebaeude(comboBoxGebaeude.getValue());
        raumModel.setBreiteInCm(Double.parseDouble(textFieldBreiteInCm.getText()));
        raumModel.setLaengeInCm(Double.parseDouble(textFieldLaengeInCm.getText()));
        return raumModel;
    }

    public void setRaum(Raum raumModel) {
        this.raumModel = raumModel;
        textFieldBezeichnung.setText(raumModel.getBezeichnung());
        comboBoxGebaeude.setValue(raumModel.getGebaeude());
        textFieldLaengeInCm.setText(String.valueOf(raumModel.getLaengeInCm()));
        textFieldBreiteInCm.setText(String.valueOf(raumModel.getBreiteInCm()));
        labelFlaecheInQm.setText(raumModel.getFlaecheInQm() + " m²");
    }

}
