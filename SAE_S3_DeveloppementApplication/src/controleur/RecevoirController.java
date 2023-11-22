package controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
    	// Créez une nouvelle fenêtre Stage
        Stage enCoursStage = new Stage();
        enCoursStage.initModality(Modality.APPLICATION_MODAL);

        // Utilisez DECORATED avec une bordure
        enCoursStage.initStyle(StageStyle.DECORATED);

        // Créez une étiquette avec le message
        Label messageLabel = new Label("En cours de traitement...");

        // Créez une barre de chargement (spinner)
        ProgressIndicator progressIndicator = new ProgressIndicator();

        // Ajoutez les éléments à une mise en page
        VBox layout = new VBox(20); // Utilisez VBox pour aligner les éléments verticalement
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, progressIndicator);
        layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px;");

        // Créez une nouvelle scène
        Scene scene = new Scene(layout, 400, 150);

        // Configurez la scène
        enCoursStage.setScene(scene);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread recevoirThread = new Thread(() -> {
            // Exécutez ici votre code pour envoyer le fichier
            boolean estRecu = Serveur.gererConnexion();

            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
                // Fermez la fenêtre Stage
                enCoursStage.close();

                // Créez la fenêtre d'information pour afficher le résultat
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Envoi de fichier");
                alert.setHeaderText(null);

                if (estRecu) {
                    alert.setContentText("Fichier reçu avec succès.");
                } else {
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Erreur lors de la connexion entre les machines.");
                }

                // Affichez la fenêtre d'information
                alert.showAndWait();
            });
        });

        // Démarrez le thread
        recevoirThread.start();

        // Affichez la fenêtre Stage
        enCoursStage.show();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
}
