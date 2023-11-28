/*
 * NoticeController.java                                      27 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.Main;


/** 
 * Controleur de la vue de la Notice.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */

public class NoticeController {

    @FXML
    private Button btnRetour;

    @FXML
    void retourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }

}

