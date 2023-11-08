/*
 * ParametreController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vue.Main;

public class ParametreController {

    @FXML
    private Button btnNouvelleCategorie;

    @FXML
    private Button btnRetour1;

        @FXML
        void NouvelleCategorie(ActionEvent event) {
            /*on crée l'interface pour ajouter une nouvelle catégorie*/
            TextInputDialog boiteSaisie = new TextInputDialog("?"); 
            boiteSaisie.setHeaderText("Ajouter une nouvelle catégorie"); 
            boiteSaisie.setTitle("Catégorie"); 
            boiteSaisie.setContentText("Entrez le nom de votre nouvelle catégorie: ");
            boiteSaisie.showAndWait();
        }

        @FXML
        void retourMenu(ActionEvent event) {
            Main.retourMenuPrincipal();
        }
}
