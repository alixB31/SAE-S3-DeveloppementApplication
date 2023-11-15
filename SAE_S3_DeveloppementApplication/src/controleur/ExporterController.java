package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import vue.Main;

public class ExporterController {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnRetour1;

    @FXML
    private Button btnSelectionnerTout;
    
    @FXML
    private ScrollBar scrollBar;

    @FXML
    private ComboBox<?> comboBoxCategorie;

    @FXML
    void btnRetour(ActionEvent event) {
    	Main.ihmImportationExportation();
    }

    @FXML
    void btnSelectionnerToutAction(ActionEvent event) {

    }

    @FXML
    void comboBoxCategorieAction(ActionEvent event) {

    }

}
