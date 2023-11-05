/*
 * PartieController.java                                      31 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.Main;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
 */
public class PartieController {

    @FXML
    private Button btnLancer;

    @FXML
    private Button btnRetour;

    @FXML
    void LancerQuiz(ActionEvent event) {
        
    }

    @FXML
    void RetourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }

}

