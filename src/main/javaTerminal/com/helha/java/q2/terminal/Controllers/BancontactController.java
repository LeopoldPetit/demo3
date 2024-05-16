package com.helha.java.q2.terminal.Controllers;

import com.helha.java.q2.terminal.Views.BancontactViewController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BancontactController {
    private BancontactViewController bancontactViewController;

    public void setBancontactViewController(BancontactViewController bancontactViewController) {
        this.bancontactViewController = bancontactViewController;
    }

    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/terminal/bancontact.fxml"));
            Parent root = loader.load();
            setBancontactViewController(loader.getController());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateMontantLabel(Double montant) {
        // Exécutez cette mise à jour dans le thread de l'interface utilisateur
        Platform.runLater(() -> {
            // Assurez-vous que bancontactViewController n'est pas null avant d'appeler setMontant
            if (bancontactViewController != null) {
                bancontactViewController.setMontant(montant);
            } else {
                System.err.println("bancontactViewController est null");
            }
        });
    }
}
