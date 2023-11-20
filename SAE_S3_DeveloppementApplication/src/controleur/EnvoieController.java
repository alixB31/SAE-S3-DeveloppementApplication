/*
 * EnvoieController.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modele.Client;
import vue.Main;

public class EnvoieController {

    @FXML
    private static TextField adresseIPServer;

    @FXML
    private Button btnEnvoi;

    @FXML
    private Button btnRetour;

    @FXML
    private static TextField numDePort;

    @FXML
    void envoieFichier(ActionEvent event) {
    	ImportationController.afficherInformation("Connexion avec votre ami","Fichier envoyé");
    	Client.connexionServeur();
    }

    @FXML
    void retourMenuPrecedent(ActionEvent event) {
    	Main.ihmExportation();
    }
    
    public static String getAdresseIPSaisie() {
    	return adresseIPServer.getText();
    }
    
    public static String getNumDePort() {
    	return numDePort.getText();
    }
}
