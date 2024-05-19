package com.helha.java.q2.terminal.Views;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class BancontactViewController {

    @FXML
    private Label montantLabel;

    @FXML
    private Button acceptButton;

    @FXML
    private Button rejectButton;

    private Socket clientSocket;
    private PrintWriter writer;
    private CountDownLatch latch;

    @FXML
    private void initialize() {
        if (montantLabel == null) {
            System.out.println("montantLabel est null. Assurez-vous que le FXML est correctement chargé.");
        }

        acceptButton.setOnAction(event -> {
            sendResponseToClient("Accepter");
        });

        rejectButton.setOnAction(event -> {
            sendResponseToClient("Refuser");
        });
    }

    public void setMontant(double montant) {
        Platform.runLater(() -> {
            if (montantLabel != null) {
                montantLabel.setText(String.format("%.2f", montant));
            } else {
                System.out.println("montantLabel est null. Assurez-vous que le FXML est correctement chargé.");
            }
        });
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            writer = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    private void sendResponseToClient(String response) {
        try {
            if (writer != null) {
                writer.println(response);
                System.out.println("Réponse envoyée au client: " + response);
            } else {
                System.out.println("Erreur: Le writer vers le socket client est null.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (latch != null) {
                latch.countDown(); // Décrémente le CountDownLatch
            }
        }
    }
}
