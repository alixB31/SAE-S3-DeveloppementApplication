/*
 * EnvoieController.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import vue.Main;
import java.net.InetAddress;
import java.net.UnknownHostException;

/** TODO comment class responsibility (SRP)
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

    @FXML
    void afficherIp(ActionEvent event) {
        // Obtenir l'adresse IP de la machine locale
        try {
            //InetAddress localhost = InetAddress.getLocalHost();
            String hostName = "MacBook-Air-de-Rayan.local"; 
            InetAddress adress = InetAddress.getByName(hostName);
            afficherIP("Adresse IP",adress.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void envoieFichier(ActionEvent event) {

    }

    @FXML
    void retourMenuPrecedent(ActionEvent event) {
        Main.ihmExportation();
    }
    
    /** 
     * Affiche l'adresse IP local 
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

}

