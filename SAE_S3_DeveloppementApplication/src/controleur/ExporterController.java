package controleur;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import modele.Categorie;
import vue.Main;

public class ExporterController {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnExporter;

    @FXML
    private Button btnSelectionnerTout;
    
    @FXML
    private ScrollBar scrollBar;

    @FXML
	public ComboBox<String> comboBoxCategorie;

    @FXML
    void btnRetour(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

    @FXML
    void btnSelectionnerToutAction(ActionEvent event) {

    }

    @FXML
    void comboBoxCategorieAction(ActionEvent event) {

    }
    
    @FXML
    void btnExporterAction(ActionEvent event) {
    	Main.ihmEnvoie();
    }

}
