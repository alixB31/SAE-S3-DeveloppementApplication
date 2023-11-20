package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import vue.Main;

public class ExporterController {

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnExporter;

    @FXML
    private Button btnSelectionnerTout;
    
    @FXML
    private ScrollPane scrollBarContenu;
    
    @FXML
	public ComboBox<String> comboBoxCategorie;
    
    @FXML
    private VBox vBoxQuestions;
    
    @FXML
    void btnRetour(ActionEvent event) {
    	Main.retourMenuPrincipal();
    }

    @FXML
    void btnSelectionnerToutAction(ActionEvent event) {

    }

    @FXML
    void comboBoxCategorieAction(ActionEvent event) {
    	String CategorieSelection = comboBoxCategorie.getValue();
    	comboBoxCategorie.getChildren.clear();
    }
    
    @FXML
    void btnExporterAction(ActionEvent event) {
    	Main.ihmEnvoie();
    }

}
