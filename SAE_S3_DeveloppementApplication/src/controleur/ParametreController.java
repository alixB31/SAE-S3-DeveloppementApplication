/*
 * ParametreController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import vue.Main;
import modele.*;

/**
 * TODO comment class responsibility (SRP)
 * 
 * @author rayanibrahime
 * @author nathangirardin
 */
public class ParametreController {

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnSuivant;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    void nouvelleCategorieAction(ActionEvent event) {
        try {
            TextInputDialog boiteSaisie = new TextInputDialog("");
            boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
            boiteSaisie.setTitle("Catégorie");
            boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
            boiteSaisie.showAndWait();

            String result = boiteSaisie.getResult();
            if (result != null && !result.isEmpty()) {
            	
            	if (Main.stockage.ajouterCategorie(new Categorie(result))) {
            		comboBox.getItems().add(result);
            	} else {
            		//TODO afficher que pas ajouté
            	}
            }
        } catch (Exception e) {
            // Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
            e.printStackTrace();
        }
    }

    @FXML
    void deleteCategorieAction(ActionEvent event) {
        try {
            Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION, "Confirmez-vous votre choix ?",
                    ButtonType.YES, ButtonType.NO);

            Optional<ButtonType> option = boiteAlerte.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.YES) {
                comboBox.getItems().remove(comboBox.getValue());
            }
        } catch (Exception e) {
            // Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
            e.printStackTrace();
        }
    }

    @FXML
    void modifierCategorieAction(ActionEvent event) {
        try {
            String categorieCourante = comboBox.getValue();
            TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
            boiteSaisie.setHeaderText("Modifier la catégorie : " + categorieCourante);
            boiteSaisie.setTitle("Catégorie");
            boiteSaisie.setContentText("Entrez le nouveau nom de votre catégorie : ");
            boiteSaisie.showAndWait();

            String result = boiteSaisie.getResult();
            if (result != null && !result.isEmpty()) {
                comboBox.getItems().set(comboBox.getItems().indexOf(categorieCourante), result);
            }
        } catch (Exception e) {
            // Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
            e.printStackTrace();
        }
    }

    @FXML
    void suivant(ActionEvent event) {
        try {
            Main.voirParamCategorie();
        } catch (Exception e) {
            // Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
            e.printStackTrace();
        }
    }

    @FXML
    void retourMenu(ActionEvent event) {
        try {
            Main.retourMenuPrincipal();
        } catch (Exception e) {
            // Gérer l'exception (afficher un message d'erreur, journaliser, etc.)
            e.printStackTrace();
        }
    }
}
