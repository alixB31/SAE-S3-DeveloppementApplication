/*
 * ChoixVontroller.java                                      16 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.Main;

/** 
 * Controleur de la vue Choix.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class ChoixController {

    @FXML
    private Button btnEnvoyer;

    @FXML
    private Button btnRecevoir;

    @FXML
    private Button btnRetour;

    @FXML
    void EnvoyerFichier(ActionEvent event) {
    	
    	Main.ihmExportation();
    }

    @FXML
    void RecevoirFichier(ActionEvent event) {
    	Main.ihmRecevoir();
    }

    @FXML
    void retourMenu(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

}
