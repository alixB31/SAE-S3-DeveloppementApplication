package controleur;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import vue.Main;
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
    	afficherNotification();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
    
    private void afficherNotification() {
    	// Afficher une Pop-up pendant la connexion
    	Alert boiteinfo = new Alert(Alert.AlertType.INFORMATION,"");
    	boiteinfo.setTitle("Connexion");
    	boiteinfo.show();
    	Serveur.gererConnexion();
    	
    	if(Serveur.gererConnexion() == true) {
    		boiteinfo.close();
        	Alert boitefinOK = new Alert(Alert.AlertType.INFORMATION,"La connexion OK, votre fichier se trouve " + Serveur.getNomFichier(),ButtonType.OK);
        	boitefinOK.setTitle("Connexion");
        	boitefinOK.setHeaderText("Connexion réussie");
        	boitefinOK.showAndWait();
        } else {
        	boiteinfo.close();
    	Alert boiteAlerte = new Alert(Alert.AlertType.INFORMATION,"Si vous voulez rententer la réception, cliquez sur ok",ButtonType.OK,ButtonType.CANCEL);
    			boiteAlerte.setTitle("Connexion");
    			boiteAlerte.setHeaderText("Connexion échouée");
    			// Ajouter un écouteur sur le bouton par défaut (ici, le bouton OK)
    	        Optional<ButtonType> result = boiteAlerte.showAndWait();
    	        result.ifPresent(buttonType -> {
    	            if (buttonType == ButtonType.OK) {
    	            	boiteinfo.show();
    	                Serveur.gererConnexion();
    	                if (Serveur.gererConnexion() == false) {
    	                	boiteinfo.close();
    	                	Alert boitefin = new Alert(Alert.AlertType.INFORMATION,"La connexion n'a pas pu être établi, veuillez réesayer",ButtonType.OK);
    	                	boitefin.setTitle("Connexion");
    	                	boitefin.setHeaderText("Connexion échouée");
    	                	boitefin.showAndWait();
    	                }else {
	    	            	boiteinfo.close();
	    	            	Alert boitefinOK = new Alert(Alert.AlertType.INFORMATION,"La connexion OK, votre fichier se trouve " + Serveur.getNomFichier(),ButtonType.OK);
	    	            	boitefinOK.setTitle("Connexion");
	    	            	boitefinOK.setHeaderText("Connexion réussie");
	    	            	boitefinOK.showAndWait();
    	                }
    	            }
    	        });
        }
    }
}
