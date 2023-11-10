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
import javafx.scene.control.MenuItem; // Correction de l'import

import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import vue.Main;

/** TODO comment class responsibility (SRP)
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
        /* On crée l'interface pour ajouter une nouvelle catégorie à la liste déroulante */
        TextInputDialog boiteSaisie = new TextInputDialog("");
        boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
        boiteSaisie.setTitle("Catégorie");
        boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
        boiteSaisie.showAndWait();
        comboBox.getItems().add(boiteSaisie.getResult());
    }
    
    @FXML
    void deleteCategorieAction(ActionEvent event) {
    	/*
    	 * Création d'une boîte d'alerte de type confirmation.
    	 * Elle est dotée de 2 boutons : oui et non
    	 */
    	Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION,
    			"Confirmez-vous votre choix ?",
    			ButtonType.YES, ButtonType.NO);

    	Optional<ButtonType> option = boiteAlerte.showAndWait();
    	if (option.get() == ButtonType.YES) { // clic sur "oui"
    		comboBox.getItems().remove(comboBox.getValue());
    	}
    }

    @FXML
    void modifierCategorieAction(ActionEvent event) {
    	String categorieCourante =  comboBox.getValue();
    	TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
        boiteSaisie.setHeaderText("Modifier la catégorie : " + categorieCourante);
        boiteSaisie.setTitle("Catégorie");
        boiteSaisie.setContentText("Entrez le nouveau nom de votre catégorie : ");
        boiteSaisie.showAndWait();
        comboBox.getItems().set(comboBox.getItems().indexOf(categorieCourante),boiteSaisie.getResult());
    }

    @FXML
    void suivant(ActionEvent event) {
    	Main.voirParamCategorie();
    }
    
    @FXML
    void retourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }

}
