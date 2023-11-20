package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import vue.Main;

public class RecevoirController {

    @FXML
    private Button btnConnexion;

    @FXML
    private Button btnIP;

    @FXML
    private Button btnNumPort;

    @FXML
    private Button btnRetour;

    @FXML
    void VoirIP(ActionEvent event) {

    }

    @FXML
    void VoirNumPort(ActionEvent event) {

    }

    @FXML
    void connexion(ActionEvent event) {

    }

    @FXML
    void retourChoix(ActionEvent event) {
    	Main.ihmChoix();
    }

}
