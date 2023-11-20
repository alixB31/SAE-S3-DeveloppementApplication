package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.Main;

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
    	
    }

    @FXML
    void retourMenu(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

}
