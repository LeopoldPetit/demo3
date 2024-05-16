package com.helha.java.q2.terminal.Controllers;

import com.helha.java.q2.terminal.Views.BancontactViewController;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main extends Application {

    public static void main(String[] args) {
        // Lancez le serveur dans un thread séparé
        new Thread(Main::startServer).start();

        // Lancez l'application JavaFX
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BancontactController bancontactController = new BancontactController();

        // Créez une instance de BancontactViewController
        BancontactViewController bancontactViewController = new BancontactViewController();

        // Passez le contrôleur BancontactViewController à BancontactController
        bancontactController.setBancontactViewController(bancontactViewController);

        // Démarrez la scène avec BancontactController
        bancontactController.start(primaryStage);
        
    }

    private static void startServer() {
        // Créez une instance de BancontactController
        BancontactController bancontactController = new BancontactController();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis " + clientSocket.getInetAddress());

                // Lire le montant envoyé par le client
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String montantStr = reader.readLine();
                Double montant = Double.parseDouble(montantStr);

                // Mettre à jour le montant dans l'interface utilisateur JavaFX
                // Utilisez la méthode définie dans BancontactController
                bancontactController.updateMontantLabel(montant);

                // Fermez la connexion avec le client
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
