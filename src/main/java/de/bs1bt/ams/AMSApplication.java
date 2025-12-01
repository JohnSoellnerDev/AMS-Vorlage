package de.bs1bt.ams;

import de.bs1bt.ams.model.Raum;
import de.bs1bt.ams.mvc.MainController;
import de.bs1bt.ams.repositories.AbstractRepositoryFactory;
import de.bs1bt.ams.repositories.RepositoryType;
import de.bs1bt.ams.repositories.RaumRAMRepository;
import de.bs1bt.ams.repositories.RaumRepository;
import de.bs1bt.ams.repositories.SimpleRepositoryFactoryFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AMSApplication extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AMSApplication.class.getResource("mvc/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BS1 BT - Asset Management System");
        stage.setScene(scene);

        MainController mainController = fxmlLoader.getController();

        SimpleRepositoryFactoryFactory factoryFactory = new SimpleRepositoryFactoryFactory();

        AbstractRepositoryFactory repositoryFactory = factoryFactory.create(RepositoryType.DEV);

        RaumRepository raumRepository = repositoryFactory.createRaumRepository();

        if (raumRepository instanceof RaumRAMRepository) {
            addTestData((RaumRAMRepository) raumRepository);
        }

        mainController.setRaumRepository(raumRepository);
        
        mainController.zeigeRaeumeInTabelle();
        mainController.zeigeGesamtflaeche();

        stage.show();
    }
    
    private void addTestData(RaumRAMRepository ramRepo) {
        try {
            ramRepo.fuegeTestdatenHinzu(new Raum(1, "A1.01", "Hauptgebäude", 500, 800));
            ramRepo.fuegeTestdatenHinzu(new Raum(2, "A1.02", "Hauptgebäude", 400, 600));
            ramRepo.fuegeTestdatenHinzu(new Raum(3, "B2.01", "Nebengebäude", 300, 500));
        } catch (Exception e) {
            System.err.println("Fehler beim Hinzufügen von Testdaten: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
