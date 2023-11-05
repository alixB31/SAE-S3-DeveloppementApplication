/*
 * ParametreCategorieController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vue.Main;

/**
 * Contrôleur de la vue ihmParametreCategorie
 * @author rayanibrahime
 *
 */
public class ParametreCategorieController {

    @FXML
    private Button btnCategorie;

    @FXML
    private Button btnCategorie2;

    @FXML
    private Button btnCategorie3;

    @FXML
    private Button btnModifierNomCategorie;

    @FXML
    private Button btnNouvelleQuestion;

    @FXML
    private Button btnRetour1;

    @FXML
    private Button btnSupprimer1;

    @FXML
    void ModifierNomCategorie(ActionEvent event) {
        /*on crée l'interface pour modifier le nom d'une catégorie*/
        TextInputDialog boiteSaisie = new TextInputDialog("?"); 
        boiteSaisie.setHeaderText("Modifier le nom de la catégorie"); 
        boiteSaisie.setTitle("Catégorie"); 
        boiteSaisie.setContentText("Entrez votre nouveau nom de catégorie: ");
        boiteSaisie.showAndWait();
    }

    @FXML
    void NouvelleQuestion(ActionEvent event) {
        /*on crée l'interface pour ajouter une nouvelle question*/
        //TODO
        
    }

    @FXML
    void retourMenu(ActionEvent event) {
        Main.lancerParametre();
    }
    
    @FXML
    void voirParam(ActionEvent event) {
        Main.voirParamQuestion();
    }
}
