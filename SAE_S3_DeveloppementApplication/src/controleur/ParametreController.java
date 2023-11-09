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
    private SplitMenuButton CategorieChoisie;

    @FXML
    private MenuItem bd;

    @FXML
    private Button btnNouvelleCategorie;

    @FXML
    private Button btnRetour1;

    @FXML
    private MenuItem general;

    @FXML
    private MenuItem java;
    
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
}
