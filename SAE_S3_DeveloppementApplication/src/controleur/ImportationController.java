package controleur;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import vue.Main;

public class ImportationController {

    @FXML
    private Button btnParcourir;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnSuivantExport;

    @FXML
    private TextField cheminDeFichier;

    @FXML
    private Button feedBackQuestionCinq;

    @FXML
    private Button feedBackQuestionDeux;

    @FXML
    private Button feedBackQuestionQuatre;

    @FXML
    private Button feedBackQuestionTrois;

    @FXML
    private Button feedBackQuestionUn;

    @FXML
    private TextField ipExport;

    @FXML
    void btnParcourirAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Titre de la fenêtre de parcours
        fileChooser.setTitle("Parcourir");

        // Filtres pour les types de fichiers (vous pouvez les personnaliser)
        fileChooser.getExtensionFilters().addAll(
            new ExtensionFilter("Fichiers texte", "*.txt"),
            new ExtensionFilter("Images", "*.png", "*.jpg", "*.gif"),
            new ExtensionFilter("Tous les fichiers", "*.*")
        );

        // Accéder à la fenêtre principale (Stage) à partir de l'événement
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Afficher la boîte de dialogue de parcours et obtenir le fichier sélectionné
        File selectedFile = fileChooser.showOpenDialog(stage);

        // Traiter le fichier sélectionné (vous pouvez ajouter votre logique ici)
        if (selectedFile != null) {
            System.out.println("Fichier sélectionné : " + selectedFile.getAbsolutePath());
            // Ajoutez ici le code pour traiter le fichier sélectionné
        } else {
            System.out.println("Aucun fichier sélectionné.");
        }
    }

        

    @FXML
    void btnRetour(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

    @FXML
    void btnSuivantExportAction(ActionEvent event) {

    }

}
