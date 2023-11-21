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
    public TextField adresseIPServer;

    @FXML
    private Button btnEnvoi;

    @FXML
    private Button btnRetour;

    @FXML
    public TextField numDePort;

    @FXML
    void envoieFichier(ActionEvent event) {
        if (Client.envoie(getAdresseIPSaisie(), getNumDePortInt())) {
		    ImportationController.afficherInformation("Statut fichier :", "Le fichier a bien été envoyé !");
		} else {
		    ImportationController.afficherInformation("Statut fichier :", "Erreur... receveur inexistant avec les paramètres actuels !");
		}
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
