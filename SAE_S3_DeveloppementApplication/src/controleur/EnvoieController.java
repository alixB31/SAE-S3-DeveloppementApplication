/*
 * EnvoieController.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import vue.Main;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/** 
 * Ce contrôleur gère la vue ihmEnvoie
 * @author rayanibrahime
 *
 */
public class EnvoieController {

    @FXML
    private Button btnEnvoi;

    @FXML
    private Button btnIP;

    @FXML
    private Button btnRetour;
    
    private static String adresseIP;
    
    @FXML
    private TextField saisieIP;

    @FXML
    void envoieFichier(ActionEvent event) {

    }

    @FXML
    void retourMenuPrecedent(ActionEvent event) {
        Main.ihmExportation();
    }
    
    /** 
     * Afficher l'adresse IP local 
     * @param titre
     * @param message
     */
    public static void afficherIP(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Récupère l'adresse IP du joueur 
     * @return adresseIP
     */
    public static String getAdresseIP() {
        return adresseIP;
    }

}

