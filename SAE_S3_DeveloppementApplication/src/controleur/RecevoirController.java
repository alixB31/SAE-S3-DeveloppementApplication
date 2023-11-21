package controleur;

import java.net.InetAddress;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    	Serveur.gererConnexion();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
    
    /**
     * Récupère l'adresse IP du receveur 
     * @return adresseIP
     */
    public static String getAdresseIP() {
        return adresseIP;
    }
}
