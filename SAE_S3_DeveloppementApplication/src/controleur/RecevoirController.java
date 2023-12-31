/*
 * RecevoirController.java                                      14 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicBoolean;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import vue.Main;
import modele.Serveur;

/** 
 * Controleur de la vue Recevoir.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class RecevoirController {

    @FXML
    private Button btnConnexion;

    @FXML
	public Button btnIP;

    @FXML
    private Button btnNumPort;

    @FXML
    private Button btnRetour;
    

    /**
     * Action associée au bouton pour voir l'adresse IP.
     * 
     * @param event L'événement de clic.
     * @throws Exception En cas d'erreur lors de la récupération de l'adresse IP.
     */
    @FXML
    void VoirIP(ActionEvent event) throws Exception {
        // Obtenez l'adresse IPv4 de la machine
        String ipV4 = getIPv4Address();
        
        btnIP.setText(ipV4); 
    }

    /**
     * Méthode pour obtenir l'adresse IPv4.
     * 
     * @return L'adresse IPv4.
     * @throws Exception En cas d'erreur lors de la récupération de l'adresse IP.
     */
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

    /**
     * Action associée au bouton de connexion.
     * 
     * @param event L'événement de clic.
     */
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

        // Variable pour suivre si l'annulation a eu lieu
        // Utilisation d'une classe pour éviter l'erreur de compilation
        AtomicBoolean annulationEffectuee = new AtomicBoolean(false);

        Button annulerButton = new Button("Annuler");

        // Ajouter une action au bouton Annuler pour fermer la connexion
        annulerButton.setOnAction(e -> {
            Serveur.arreterConnexion();
            enCoursStage.close();
            annulationEffectuee.set(true);
            afficherInformation("Statut du fichier", "La connexion a bien été annulée.");
        });

        // Ajoutez les éléments à une mise en page
        VBox layout = new VBox(20); // Utilisez VBox pour aligner les éléments verticalement
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(messageLabel, progressIndicator, annulerButton);
        layout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); -fx-padding: 20px;");

        // Créez une nouvelle scène
        Scene scene = new Scene(layout, 350, 170);

        // Configurez la scène
        enCoursStage.setScene(scene);

        enCoursStage.setOnCloseRequest(WindowEvent::consume);

        // Créez un nouveau thread pour l'envoi du fichier
        Thread recevoirThread = new Thread(() -> {
            boolean estRecu = Serveur.gererConnexion();

            // Mettez à jour l'interface utilisateur depuis le thread de l'UI
            Platform.runLater(() -> {
                // Fermez la fenêtre Stage
                enCoursStage.close();

                // Créez la fenêtre d'information pour afficher le résultat
                if (estRecu && !annulationEffectuee.get()) {
                    // Affichez la fenêtre d'information si la connexion n'a pas été annulée
                    afficherInformation("Statut du fichier :", "Fichier reçu avec succès.");
                } else if (!annulationEffectuee.get()) {
                    // Afficher un message d'erreur si la connexion n'a pas été annulée
                    afficherErreur("Statut du fichier :", "Erreur lors de la connexion entre les machines.");
                }
            });
        });

        // Démarrez le thread
        recevoirThread.start();

        // Affichez la fenêtre Stage
        enCoursStage.show();
    }

    /**
     * Affiche un message d'information.
     * 
     * @param titre   Le titre de l'alerte.
     * @param message Le message à afficher.
     */
    public static void afficherInformation(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Affiche un message d'erreur.
     * 
     * @param titre   Le titre de l'alerte.
     * @param message Le message d'erreur à afficher.
     */
    public static void afficherErreur(String titre, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Action associée au bouton de retour.
     * 
     * @param event L'événement de clic.
     */
    @FXML
    void retourChoix(ActionEvent event) {
        Main.ihmChoix();
    }
}
