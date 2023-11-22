/*
 * EnvoieController.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.Alert.AlertType;
import modele.Client;
import vue.Main;

public class EnvoieController {

    @FXML
    public TextField adresseIPServer;

    @FXML
    private Button btnEnvoi;

    @FXML
    private Button btnRetour;

    @FXML
    void envoieFichier(ActionEvent event) {
        // Créez une nouvelle fenêtre Stage
        Stage enCoursStage = new Stage();
        enCoursStage.initModality(Modality.APPLICATION_MODAL);
        enCoursStage.initStyle(StageStyle.UNDECORATED);

        // Créez une étiquette avec le message
        Label messageLabel = new Label("En cours de traitement...");

        // Ajoutez l'étiquette à une mise en page
        StackPane layout = new StackPane(messageLabel);
        layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px;");

        // Créez une nouvelle scène
        Scene scene = new Scene(layout, 400, 150);

        // Configurez la scène
        enCoursStage.setScene(scene);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread envoiThread = new Thread(() -> {
            // Exécutez ici votre code pour envoyer le fichier
            boolean estEnvoye = Client.envoie(getAdresseIPSaisie());

            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
                // Fermez la fenêtre Stage
                enCoursStage.close();

                // Créez la fenêtre d'information pour afficher le résultat
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Envoi de fichier");
                alert.setHeaderText(null);

                if (estEnvoye) {
                    alert.setContentText("Fichier envoyé avec succès.");
                } else {
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Erreur lors de l'envoi du fichier.");
                }

                // Affichez la fenêtre d'information
                alert.showAndWait();
            });
        });

        // Démarrez le thread
        envoiThread.start();

        // Affichez la fenêtre Stage
        enCoursStage.show();
    }



    @FXML
    void retourMenuPrecedent(ActionEvent event) {
    	Main.ihmExportation();
    }
    
    public  String getAdresseIPSaisie() {
    	return adresseIPServer.getText();
    }
}
