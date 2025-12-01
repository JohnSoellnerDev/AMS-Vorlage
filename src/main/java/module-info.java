module de.bs1bt.ams.ams {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens de.bs1bt.ams.model to javafx.fxml;
    exports de.bs1bt.ams.model;

    opens de.bs1bt.ams.mvc to javafx.fxml;
    exports de.bs1bt.ams.mvc;
    
    opens de.bs1bt.ams.repositories to javafx.fxml;
    exports de.bs1bt.ams.repositories;
    
    exports de.bs1bt.ams;
    opens de.bs1bt.ams to javafx.fxml;
}