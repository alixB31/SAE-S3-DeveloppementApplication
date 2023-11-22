/*
 * EnvoieController.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
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
    public TextField numDePort;

    @FXML
    void envoieFichier(ActionEvent event) {
    	Alert enCours = new Alert(AlertType.INFORMATION);
    	enCours.setTitle("En cours de traitement...");
    	enCours.setHeaderText(null);
    	enCours.setContentText("Test");
    	enCours.show();
    	
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Envoi de fichier");
        alert.setHeaderText(null);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread envoiThread = new Thread(() -> {
            // Exécutez ici votre code pour envoyer le fichier
            boolean estEnvoye = Client.envoie(getAdresseIPSaisie());
            
            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
            	enCours.close();
                if (estEnvoye) {
                    alert.setContentText("Fichier envoyé avec succès.");
                    alert.showAndWait();
                } else {
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Erreur lors de l'envoi du fichier.");
                    alert.showAndWait();
                }
            });
        });

        // Démarrez le thread
        envoiThread.start();
    }


    @FXML
    void retourMenuPrecedent(ActionEvent event) {
    	Main.ihmExportation();
    }
    
    public  String getAdresseIPSaisie() {
    	return adresseIPServer.getText();
    }
    
    public String getNumDePort() {
    	return numDePort.getText();
    }
    
    public int getNumDePortInt() {
    	return Integer.parseInt(getNumDePort());
    }
}
