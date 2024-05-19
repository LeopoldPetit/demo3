package com.helha.java.q2.terminal.Controllers;

import com.helha.java.q2.terminal.Views.BancontactViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Main extends Application {

    private static BancontactViewController bancontactViewController;

    public static void main(String[] args) {
        // Lancez le serveur dans un thread séparé
        new Thread(Main::startServer).start();

        // Lancez l'application JavaFX
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helha/java/q2/terminal/bancontact.fxml"));
        Parent root = loader.load();
        bancontactViewController = loader.getController();

        // Setup the stage
        primaryStage.setTitle("Bancontact Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private static void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Serveur démarré. En attente de connexions...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connecté depuis " + clientSocket.getInetAddress());

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String montantStr = reader.readLine();
                Double montant = Double.parseDouble(montantStr);

                CountDownLatch latch = new CountDownLatch(1);

                if (bancontactViewController != null) {
                    bancontactViewController.setMontant(montant);
                    bancontactViewController.setClientSocket(clientSocket);
                    bancontactViewController.setLatch(latch);

                    // Attendre que la réponse soit reçue avant de fermer le socket
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("bancontactViewController est null.");
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
