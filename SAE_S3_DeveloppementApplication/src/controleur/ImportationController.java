/*
 * ImportationController.java                                      20 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import vue.Main;

/** 
 * Controleur de la vue Importation.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class ImportationController {

    @FXML
    private Button btnParcourir;

    @FXML
    private Button btnRetour;
    
    @FXML
    private Button btnValider;

    @FXML
    private TextField cheminDeFichier;

    @FXML
    void btnParcourirAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Titre de la fenêtre de parcours
        fileChooser.setTitle("Parcourir");

        // Accéder à la fenêtre principale (Stage) à partir de l'événement
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Définir le répertoire initial comme le répertoire du projet
        String projectDirectory = System.getProperty("user.dir");
        
        fileChooser.setInitialDirectory(new File(projectDirectory));
        
        // Afficher la boîte de dialogue de parcours et obtenir le fichier sélectionné
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Traiter le fichier sélectionné (vous pouvez ajouter votre logique ici)
        if (selectedFile != null) {
        	cheminDeFichier.setText(selectedFile.getAbsolutePath());
            if (Main.stockage.importCSV(selectedFile.getAbsolutePath())) {
            	afficherInformation("Fichier importé", "Le fichier a bien été importé");
            } else {
            	afficherInformation("Fichier non importé", "Le fichier n'a pas été importé");
            }
            // Ajoutez ici le code pour traiter le fichier sélectionné
        }
    }

    @FXML
    void btnRetour(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

    @FXML
    void btnSuivantExportAction(ActionEvent event) {
    	Main.ihmExportation();
    }
    
    /** 
     * Affiche un message pour confirmer l'import
     * d'un fichier csv
     * @param titre
     * @param message
     */
    public static void afficherInformation(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    void btnValiderAction(ActionEvent event) {
    	if (Main.stockage.importCSV(cheminDeFichier.getText())) {
			afficherInformation("Fichier importé", "Le fichier a bien été importé");
		} else {
			afficherInformation("Fichier non importé", "Le fichier n'a pas été importé");
		}
    }
}
