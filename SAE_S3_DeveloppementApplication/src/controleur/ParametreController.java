/*
 * ParametreController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    void NouvelleCategorie(ActionEvent event) {
        /* On crée l'interface pour ajouter une nouvelle catégorie à la liste déroulante */
        TextInputDialog boiteSaisie = new TextInputDialog("?");
        boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
        boiteSaisie.setTitle("Catégorie");
        boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
        boiteSaisie.showAndWait();
        comboBox.getItems().add(boiteSaisie.getResult());
    }

    @FXML
    void retourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }
    
    @FXML
    void deleteCategorieAction(ActionEvent event) {
    	
    }

    @FXML
    void modifierCategorieAction(ActionEvent event) {
    	String categorieCourante =  comboBox.getSelectedItem().toString();
    	TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
        boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie");
        boiteSaisie.setTitle("Catégorie");
        boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
        boiteSaisie.showAndWait();
    }

    @FXML
    void suivant(ActionEvent event) {
    	Main.voirParamCategorie();
    }

}
