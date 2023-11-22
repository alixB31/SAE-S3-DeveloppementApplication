package controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import vue.Main;
import modele.Client;
import modele.Serveur;

public class RecevoirController {

    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnIP;

    @FXML
    private Button btnNumPort;

    @FXML
    private Button btnRetour;
    
    private static String adresseIP;

    @FXML
    void VoirIP(ActionEvent event) throws Exception {
    	InetAddress ip = InetAddress.getLocalHost();
    	ImportationController.afficherInformation("Adresse IP","L'adresse IP est : " + ip.getHostAddress());
    }

    @FXML
    void connexion(ActionEvent event) {
    	Alert enCours = new Alert(AlertType.INFORMATION);
    	enCours.setTitle("Tentative de connexion.");
    	enCours.setHeaderText("En cours de traitement...");
    	enCours.show();
    	
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Envoi de fichier");
        alert.setHeaderText(null);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread recoiThread = new Thread(() -> {
            // Exécutez ici votre code pour envoyer le fichier
            boolean estRecu = Serveur.gererConnexion();
            
            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
            	enCours.close();
                if (estRecu) {
                    alert.setContentText("Fichier reçu avec succès.");
                    alert.showAndWait();
                } else {
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Erreur, la connexion n'a pas été établie.");
                    alert.showAndWait();
                }
            });
        });

        // Démarrez le thread
        recoiThread.start();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
}
