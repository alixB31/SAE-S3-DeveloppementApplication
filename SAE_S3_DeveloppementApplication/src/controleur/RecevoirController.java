package controleur;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

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
    void VoirIP(ActionEvent event) {
        // Obtenir l'adresse IP de la machine locale
        boolean adresseIPv4Trouvee = false;

        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

            while (interfaces.hasMoreElements() && !adresseIPv4Trouvee) {
                NetworkInterface networkInterface = interfaces.nextElement();

                if (networkInterface.isUp() && !networkInterface.isLoopback()) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();

                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();

                        // Vérifier si c'est une adresse IPv4
                        if (address instanceof Inet4Address) {
                            // Cette condition filtre les adresses IPv4
                            adresseIP = address.getHostAddress();
                            ImportationController.afficherInformation("AdresseIP", "Votre adresse IP est : " + adresseIP);
                            adresseIPv4Trouvee = true;
                        }
                    }
                }
            }

            if (!adresseIPv4Trouvee) {
                ImportationController.afficherInformation("AdresseIP", "Adresse IP non trouvée");
                System.out.println("Adresse IP non trouvée");
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
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
