package de.bs1bt.ams.ui;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.repository.RaumRepository;
import de.bs1bt.ams.repository.GeraetRepository;
import de.bs1bt.ams.repository.GebaeudeRepository;
import de.bs1bt.ams.repository.RepositoryException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class MainController {

    private RaumRepository raumRepository;
    private GeraetRepository geraetRepository;

    @FXML
    private Label labelGesamtflaecheInQm;
    @FXML
    private TableView<Raum> raumTable;
    @FXML
    private TableColumn columnRaumId;
    @FXML
    private TableColumn columnRaumBezeichnung;
    @FXML
    private TableColumn columnRaumGebaeude;
    @FXML
    private TableColumn columnRaumFlaecheInQm;

    @FXML
    private TableView<Geraet> geraetTable;
    @FXML
    private TableColumn columnGeraetId;
    @FXML
    private TableColumn columnGeraetBezeichnung;
    @FXML
    private TableColumn columnGeraetTyp;
    @FXML
    private TableColumn columnGeraetRaumId;
    @FXML
    private TableColumn columnGeraetInventarnummer;

    public void setRaumRepository(RaumRepository raumRepository) {
        this.raumRepository = raumRepository;
    }

    public RaumRepository getRaumRepository() {
        return raumRepository;
    }

    public void setGeraetRepository(GeraetRepository geraetRepository) {
        this.geraetRepository = geraetRepository;
    }

    public GeraetRepository getGeraetRepository() {
        return geraetRepository;
    }

    public void mnuUeberAMS(ActionEvent actionEvent) {
        Alert ueberAMS = new Alert(Alert.AlertType.INFORMATION);
        ueberAMS.setTitle("Über AMS");
        ueberAMS.setHeaderText("BS1 BT GmbH");
        ueberAMS.setContentText(
                "Diese didaktische Software wird von den Schülerinnen und Schülern der Berufsschule 1 in Bayreuth entwickelt.");
        ueberAMS.show();
    }

    public void mnuSchliessen(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void zeigeRaeumeInTabelle() {
        /*
         * https://www.informatik-aktuell.de/entwicklung/programmiersprachen/mvvm-mit-
         * javafx.html
         * https://jenkov.com/tutorials/javafx/tableview.html
         */
        raumTable.getItems().clear();

        columnRaumId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnRaumBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnRaumGebaeude.setCellValueFactory(new PropertyValueFactory<>("gebaeude"));
        columnRaumFlaecheInQm.setCellValueFactory(new PropertyValueFactory<>("flaecheInQm"));

        // Iterator Pattern
        try {

            List<Raum> raeumeListe = raumRepository.holeAlle();
            Iterator<Raum> iterator = raeumeListe.iterator();
            while (iterator.hasNext()) {
                Raum raum = iterator.next();
                raumTable.getItems().add(raum);
            }
            raumTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (RepositoryException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Die Räume können nicht aus der Datenbank ausgelesen werden.");
            alert.show();
        }
    }

    void zeigeDatenbankAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.setTitle("Datenbank-Fehler");
        alert.setHeaderText("Datenbank-Fehler");
        alert.show();
    }

    public void zeigeGesamtflaeche() {
        // Berechnung der Gesamtfläche aller Räume
        try {
            List<Raum> raeumeListe = raumRepository.holeAlle();
            double gesamtflaeche = 0.0;

            for (Raum raum : raeumeListe) {
                gesamtflaeche += raum.getFlaecheInQm();
            }

            // Formatierte Ausgabe mit zwei Nachkommastellen
            labelGesamtflaecheInQm.setText(String.format("%.2f qm", gesamtflaeche));
        } catch (RepositoryException e) {
            labelGesamtflaecheInQm.setText("Fehler beim Berechnen");
            zeigeDatenbankAlert("Die Gesamtfläche konnte nicht berechnet werden: " + e.getMessage());
        }
    }

    private GebaeudeRepository gebaeudeRepository;

    public void setGebaeudeRepository(GebaeudeRepository gebaeudeRepository) {
        this.gebaeudeRepository = gebaeudeRepository;
    }

    public GebaeudeRepository getGebaeudeRepository() {
        return gebaeudeRepository;
    }

    // ... (existing code)

    public Raum zeigeRaumDialogView(String title, Raum raumModel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation((getClass().getResource("raum-dialog-view.fxml")));
            DialogPane raumDialogPane = fxmlLoader.load();

            RaumDialogController raumDialogController = fxmlLoader.getController();

            // Pass available buildings to the controller
            if (gebaeudeRepository != null) {
                raumDialogController.setMoeglicheGebaeude(gebaeudeRepository.holeAlle());
            }

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(raumDialogPane);
            dialog.setTitle(title);

            raumDialogController.setRaum(raumModel);

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                return raumDialogController.getRaum();
            }

        } catch (IOException | RepositoryException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Fehler");
            alert.show();
        }
        return null;
    }

    public void btnRaumAnlegenAction(ActionEvent actionEvent) {
        try {
            Raum neuerRaum = new Raum("Bezeichnung", null);
            if (null != zeigeRaumDialogView("Raum anlegen", neuerRaum)) {
                raumRepository.erstelle(neuerRaum);
                zeigeRaeumeInTabelle();
                zeigeGesamtflaeche();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    public void btnRaumBearbeitenAction(ActionEvent actionEvent) {
        Raum raumZurBearbeitung = raumTable.getSelectionModel().getSelectedItem();

        if (null == raumZurBearbeitung) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie einen Raum aus der Liste aus.");
            alert.setHeaderText("Kein Raum selektiert");
            alert.show();
            return;
        }

        if (null != zeigeRaumDialogView("Raum bearbeiten", raumZurBearbeitung)) {
            try {
                raumRepository.aktualisiere(raumZurBearbeitung);
                zeigeRaeumeInTabelle();
                zeigeGesamtflaeche();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }

    public void btnRaumLoeschenAction(ActionEvent actionEvent) {
        Raum raumZumLoeschen = raumTable.getSelectionModel().getSelectedItem();

        if (null == raumZumLoeschen) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie einen Raum aus der Liste aus.");
            alert.setHeaderText("Kein Raum selektiert");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie den Raum wirklich löschen?");
        alert.setHeaderText("Kein Raum selektiert");
        Optional<ButtonType> clickedButton = alert.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            try {
                raumRepository.loesche(raumZumLoeschen);
                zeigeRaeumeInTabelle();
                zeigeGesamtflaeche();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }

    public void zeigeGeraeteInTabelle() {
        geraetTable.getItems().clear();

        columnGeraetId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnGeraetBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnGeraetTyp.setCellValueFactory(new PropertyValueFactory<>("typ"));
        columnGeraetRaumId.setCellValueFactory(new PropertyValueFactory<>("raumId"));
        columnGeraetInventarnummer.setCellValueFactory(new PropertyValueFactory<>("inventarnummer"));

        try {
            List<Geraet> geraeteListe = geraetRepository.holeAlle();
            for (Geraet geraet : geraeteListe) {
                geraetTable.getItems().add(geraet);
            }
            geraetTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (RepositoryException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Die Geräte können nicht aus der Datenbank ausgelesen werden.");
            alert.show();
        }
    }

    public Geraet zeigeGeraetDialogView(String title, Geraet geraetModel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation((getClass().getResource("geraet-dialog-view.fxml")));
            DialogPane geraetDialogPane = fxmlLoader.load();

            GeraetDialogController geraetDialogController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(geraetDialogPane);
            dialog.setTitle(title);

            geraetDialogController.setGeraet(geraetModel);

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                return geraetDialogController.getGeraet();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Fehler");
            alert.show();
        }
        return null;
    }

    public void btnGeraetAnlegenAction(ActionEvent actionEvent) {
        try {
            Geraet neuesGeraet = new Geraet("Bezeichnung", "Typ");
            if (null != zeigeGeraetDialogView("Gerät anlegen", neuesGeraet)) {
                geraetRepository.erstelle(neuesGeraet);
                zeigeGeraeteInTabelle();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    public void btnGeraetBearbeitenAction(ActionEvent actionEvent) {
        Geraet geraetZurBearbeitung = geraetTable.getSelectionModel().getSelectedItem();

        if (null == geraetZurBearbeitung) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie ein Gerät aus der Liste aus.");
            alert.setHeaderText("Kein Gerät selektiert");
            alert.show();
            return;
        }

        if (null != zeigeGeraetDialogView("Gerät bearbeiten", geraetZurBearbeitung)) {
            try {
                geraetRepository.aktualisiere(geraetZurBearbeitung);
                zeigeGeraeteInTabelle();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }

    public void btnGeraetLoeschenAction(ActionEvent actionEvent) {
        Geraet geraetZumLoeschen = geraetTable.getSelectionModel().getSelectedItem();

        if (null == geraetZumLoeschen) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie ein Gerät aus der Liste aus.");
            alert.setHeaderText("Kein Gerät selektiert");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie das Gerät wirklich löschen?");
        alert.setHeaderText("Kein Gerät selektiert");
        Optional<ButtonType> clickedButton = alert.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            try {
                geraetRepository.loesche(geraetZumLoeschen);
                zeigeGeraeteInTabelle();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }

    @FXML
    private TableView<Gebaeude> gebaeudeTable;
    @FXML
    private TableColumn columnGebaeudeId;
    @FXML
    private TableColumn columnGebaeudeBezeichnung;
    @FXML
    private TableColumn columnGebaeudeAdresse;

    public void zeigeGebaeudeInTabelle() {
        gebaeudeTable.getItems().clear();

        columnGebaeudeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnGebaeudeBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnGebaeudeAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        try {
            List<Gebaeude> gebaeudeListe = gebaeudeRepository.holeAlle();
            for (Gebaeude gebaeude : gebaeudeListe) {
                gebaeudeTable.getItems().add(gebaeude);
            }
            gebaeudeTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        } catch (RepositoryException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Die Gebäude können nicht aus der Datenbank ausgelesen werden.");
            alert.show();
        }
    }

    public Gebaeude zeigeGebaeudeDialogView(String title, Gebaeude gebaeudeModel) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation((getClass().getResource("gebaeude-dialog-view.fxml")));
            DialogPane gebaeudeDialogPane = fxmlLoader.load();

            GebaeudeDialogController gebaeudeDialogController = fxmlLoader.getController();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(gebaeudeDialogPane);
            dialog.setTitle(title);

            gebaeudeDialogController.setGebaeude(gebaeudeModel);

            Optional<ButtonType> clickedButton = dialog.showAndWait();
            if (clickedButton.get() == ButtonType.OK) {
                return gebaeudeDialogController.getGebaeude();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.setTitle("Fehler");
            alert.show();
        }
        return null;
    }

    public void btnGebaeudeAnlegenAction(ActionEvent actionEvent) {
        try {
            Gebaeude neuesGebaeude = new Gebaeude("Bezeichnung", "Adresse");
            if (null != zeigeGebaeudeDialogView("Gebäude anlegen", neuesGebaeude)) {
                gebaeudeRepository.erstelle(neuesGebaeude);
                zeigeGebaeudeInTabelle();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
        }
    }

    public void btnGebaeudeBearbeitenAction(ActionEvent actionEvent) {
        Gebaeude gebaeudeZurBearbeitung = gebaeudeTable.getSelectionModel().getSelectedItem();

        if (null == gebaeudeZurBearbeitung) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie ein Gebäude aus der Liste aus.");
            alert.setHeaderText("Kein Gebäude selektiert");
            alert.show();
            return;
        }

        if (null != zeigeGebaeudeDialogView("Gebäude bearbeiten", gebaeudeZurBearbeitung)) {
            try {
                gebaeudeRepository.aktualisiere(gebaeudeZurBearbeitung);
                zeigeGebaeudeInTabelle();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }

    public void btnGebaeudeLoeschenAction(ActionEvent actionEvent) {
        Gebaeude gebaeudeZumLoeschen = gebaeudeTable.getSelectionModel().getSelectedItem();

        if (null == gebaeudeZumLoeschen) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Bitte wählen Sie ein Gebäude aus der Liste aus.");
            alert.setHeaderText("Kein Gebäude selektiert");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Wollen Sie das Gebäude wirklich löschen?");
        alert.setHeaderText("Kein Gebäude selektiert");
        Optional<ButtonType> clickedButton = alert.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            try {
                gebaeudeRepository.loesche(gebaeudeZumLoeschen);
                zeigeGebaeudeInTabelle();
            } catch (RepositoryException e) {
                zeigeDatenbankAlert(e.getMessage());
            }
        }
    }
}
