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
    void VoirNumPort(ActionEvent event) {
    	ImportationController.afficherInformation("Numéro de port","Le numéro de port est : " + Serveur.NUM_PORT);
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
    	Serveur.gererConnexion();
    	Alert boiteAlerte =new Alert(Alert.AlertType.INFORMATION,"Si vous voulez rententer la réception, cliquez sur ok",ButtonType.OK,ButtonType.CANCEL);
    			boiteAlerte.setTitle("Connexion");
    			boiteAlerte.setHeaderText("Connexion échouée");
    			// Ajouter un écouteur sur le bouton par défaut (ici, le bouton OK)
    	        Optional<ButtonType> result = boiteAlerte.showAndWait();
    	        
    	        result.ifPresent(buttonType -> {
    	            if (buttonType == ButtonType.OK) {
    	                Serveur.gererConnexion();
    	                if (Serveur.gererConnexion() == false) {
    	                	Alert boitefin =new Alert(Alert.AlertType.INFORMATION,"La connexion n'a pas pu être établi, veuillez recommencer",ButtonType.OK);
    	                	boitefin.setTitle("Connexion");
    	                	boitefin.setHeaderText("Connexion échouée");
    	                	boitefin.showAndWait();
    	                }
    	            }
    	            if (buttonType == ButtonType.CANCEL) {
    	            	
    	            }
    	        });
    }
}
