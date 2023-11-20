package controleur;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
    void VoirIP(ActionEvent event) {
    	// Obtenir l'adresse IP de la machine locale
    	try {
    	    Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

    	    while (interfaces.hasMoreElements()) {
    	        NetworkInterface networkInterface = interfaces.nextElement();

    	        if (networkInterface.isUp() && !networkInterface.isLoopback()) {
    	            Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

    	            while (addresses.hasMoreElements()) {
    	                InetAddress address = addresses.nextElement();

    	                // Vérifier si c'est une adresse IPv4
    	                if (address.getHostAddress().indexOf(':') == -1) {
    	                    // Cette condition filtre les adresses IPv4
    	                    adresseIP = address.getHostAddress();
    	                    break; // Arrête la boucle dès qu'une adresse IPv4 est trouvée
    	                }
    	            }
    	        }
    	    }

    	    if (adresseIP != null) {
    	        afficherInfo("AdresseIP", "Votre adresseIP est : " + adresseIP);
    	    } else {
    	        afficherInfo("", "test");
    	    }

    	} catch (SocketException e) {
    	    e.printStackTrace();
    	}
    }

    @FXML
    void VoirNumPort(ActionEvent event) {
    	afficherInfo("Numéro de port","Le numéro de port est : "+ Serveur.getNumPort());
    }

    @FXML
    void connexion(ActionEvent event) {
    	afficherInfo("Connexion avec votre ami","Attente de connexion...");
    	Serveur.gererConnexion();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
    
    /** 
     * Afficher l'adresse IP local 
     * @param titre
     * @param message
     */
    public static void afficherInfo(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    /**
     * Récupère l'adresse IP du receveur 
     * @return adresseIP
     */
    public static String getAdresseIP() {
        return adresseIP;
    }

}
