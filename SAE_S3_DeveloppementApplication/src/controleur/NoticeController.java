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
 * Cette classe est le contrôleur de la vue ihmNotice.java
 * @author rayanibrahime
 */

public class NoticeController {

    @FXML
    private Button btnRetour;

    @FXML
    void retourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }

}

