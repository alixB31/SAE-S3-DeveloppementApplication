package controleur;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
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
    
    @FXML
    void VoirIP(ActionEvent event) throws Exception {
        // Obtenez l'adresse IPv4 de la machine
        String ipV4 = getIPv4Address();

        // Affichez l'adresse IP
        ImportationController.afficherInformation("Adresse IP", "L'adresse IP est : " + ipV4);
    }

    // Méthode pour obtenir l'adresse IPv4
    private String getIPv4Address() throws Exception {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();

        while (interfaces.hasMoreElements()) {
            NetworkInterface iface = interfaces.nextElement();
            
            // Ignorez les interfaces virtuelles et inactives
            if (iface.isLoopback() || !iface.isUp()) {
                continue;
            }

            Enumeration<InetAddress> addresses = iface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress addr = addresses.nextElement();

                // Vérifiez si c'est une adresse IPv4
                if (addr.getHostAddress().matches("\\d+\\.\\d+\\.\\d+\\.\\d+")) {
                    return addr.getHostAddress();
                }
            }
        }

        // Si aucune adresse IPv4 n'est trouvée, retournez une chaîne vide ou une valeur par défaut
        return "Adresse IP non disponible";
    }

    @FXML
    void connexion(ActionEvent event) {
    	// Créez une nouvelle fenêtre Stage
        Stage enCoursStage = new Stage();
        enCoursStage.initModality(Modality.APPLICATION_MODAL);

        // Utilisez DECORATED avec une bordure
        enCoursStage.initStyle(StageStyle.DECORATED);

        // Créez une étiquette avec le message
        Label messageLabel = new Label("En cours de traitement...");

        // Créez une barre de chargement (spinner)
        ProgressIndicator progressIndicator = new ProgressIndicator();

        // Ajoutez les éléments à une mise en page
        VBox layout = new VBox(20); // Utilisez VBox pour aligner les éléments verticalement
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, progressIndicator);
        layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px;");

        // Créez une nouvelle scène
        Scene scene = new Scene(layout, 350, 120);

        // Configurez la scène
        enCoursStage.setScene(scene);
        
        enCoursStage.setOnCloseRequest(WindowEvent::consume);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread recevoirThread = new Thread(() -> {
            // Exécutez ici votre code pour envoyer le fichier
            boolean estRecu = Serveur.gererConnexion();

            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
                // Fermez la fenêtre Stage
                enCoursStage.close();

                // Créez la fenêtre d'information pour afficher le résultat
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Envoi de fichier");
                alert.setHeaderText(null);

                if (estRecu) {
                    alert.setContentText("Fichier reçu avec succès.");
                } else {
                    alert.setAlertType(AlertType.ERROR);
                    alert.setContentText("Erreur lors de la connexion entre les machines.");
                }

                // Affichez la fenêtre d'information
                alert.showAndWait();
            });
        });

        // Démarrez le thread
        recevoirThread.start();

        // Affichez la fenêtre Stage
        enCoursStage.show();
    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }
}
