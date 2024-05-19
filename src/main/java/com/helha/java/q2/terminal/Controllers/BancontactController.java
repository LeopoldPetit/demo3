package com.helha.java.q2.terminal.Controllers;

import com.helha.java.q2.terminal.Views.BancontactViewController;
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
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
