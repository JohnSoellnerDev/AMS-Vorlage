package de.bs1bt.ams;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.model.Geraet;
import de.bs1bt.ams.ui.MainController;
import de.bs1bt.ams.repository.factory.AbstractRepositoryFactory;
import de.bs1bt.ams.repository.RepositoryType;
import de.bs1bt.ams.repository.ram.RaumRAMRepository;
import de.bs1bt.ams.repository.RaumRepository;
import de.bs1bt.ams.model.Gebaeude;
import de.bs1bt.ams.repository.ram.GebaeudeRAMRepository;
import de.bs1bt.ams.repository.GebaeudeRepository;
import de.bs1bt.ams.repository.ram.GeraetRAMRepository;
import de.bs1bt.ams.repository.GeraetRepository;
import de.bs1bt.ams.repository.factory.SimpleRepositoryFactoryFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AMSApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AMSApplication.class.getResource("ui/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BS1 BT - Asset Management System");
        stage.setScene(scene);

        MainController mainController = fxmlLoader.getController();

        SimpleRepositoryFactoryFactory factoryFactory = new SimpleRepositoryFactoryFactory();

        AbstractRepositoryFactory repositoryFactory = factoryFactory.create(RepositoryType.DEV);

        RaumRepository raumRepository = repositoryFactory.createRaumRepository();

        // Deprecated call removed

        GeraetRepository geraetRepository = repositoryFactory.createGeraetRepository();

        if (geraetRepository instanceof GeraetRAMRepository) {
            addTestData((GeraetRAMRepository) geraetRepository);
        }

        GebaeudeRepository gebaeudeRepository = repositoryFactory.createGebaeudeRepository();
        if (gebaeudeRepository instanceof GebaeudeRAMRepository) {
            addTestData((GebaeudeRAMRepository) gebaeudeRepository);
        }

        mainController.setRaumRepository(raumRepository);
        mainController.setGeraetRepository(geraetRepository);
        mainController.setGebaeudeRepository(gebaeudeRepository);

        if (raumRepository instanceof RaumRAMRepository && gebaeudeRepository instanceof GebaeudeRAMRepository) {
            addTestData((RaumRAMRepository) raumRepository, (GebaeudeRAMRepository) gebaeudeRepository);
        }

        mainController.zeigeRaeumeInTabelle();
        mainController.zeigeGeraeteInTabelle();
        mainController.zeigeGebaeudeInTabelle();
        mainController.zeigeGesamtflaeche();

        stage.show();
    }

    private void addTestData(RaumRAMRepository ramRepo, GebaeudeRAMRepository gebRepo) {
        try {
            Gebaeude haupt = gebRepo.hole(1);
            Gebaeude neben = gebRepo.hole(2);

            ramRepo.fuegeTestdatenHinzu(new Raum(1, "A1.01", haupt, 500, 800));
            ramRepo.fuegeTestdatenHinzu(new Raum(2, "A1.02", haupt, 400, 600));
            ramRepo.fuegeTestdatenHinzu(new Raum(3, "B2.01", neben, 300, 500));
        } catch (Exception e) {
            System.err.println("Fehler beim Hinzufügen von Raum-Testdaten: " + e.getMessage());
        }
    }

    private void addTestData(GebaeudeRAMRepository gebRepo) {
        try {
            gebRepo.erstelle(new Gebaeude("Hauptgebäude", "Musterstr. 1"));
            gebRepo.erstelle(new Gebaeude("Nebengebäude", "Musterstr. 2"));
        } catch (Exception e) {
            System.err.println("Fehler beim Hinzufügen von Gebäude-Testdaten: " + e.getMessage());
        }
    }

    private void addTestData(GeraetRAMRepository ramRepo) {
        try {
            ramRepo.fuegeTestdatenHinzu(new Geraet(1, "Beamer", "Projektor", 1, "INV-001"));
            ramRepo.fuegeTestdatenHinzu(new Geraet(2, "Whiteboard", "Tafel", 1, "INV-002"));
            ramRepo.fuegeTestdatenHinzu(new Geraet(3, "PC Lehrer", "Computer", 2, "INV-003"));
        } catch (Exception e) {
            System.err.println("Fehler beim Hinzufügen von Testdaten: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
