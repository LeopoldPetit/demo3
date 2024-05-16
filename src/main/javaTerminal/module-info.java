module com.helha.java.q2.terminal {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    exports com.helha.java.q2.terminal.Controllers;
    exports com.helha.java.q2.terminal.Views;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.helha.java.q2.terminal.Controllers to javafx.fxml;
    opens com.helha.java.q2.terminal.Views to javafx.fxml;
}