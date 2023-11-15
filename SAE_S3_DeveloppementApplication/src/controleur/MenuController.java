package controleur;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import vue.Main;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
 */
public class MenuController {

    @FXML
    private Button btnImportExport;

    @FXML
    private Button btnLancerPartie;

    @FXML
    private Button btnNotice;

    @FXML
    private Button btnParam;

    @FXML
    private Button btnQuitter;
    
    @FXML
    private Button btnJoueur;

    @FXML
    void quitterQuiz(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML 
    void VoirNotice(ActionEvent event) {
        Main.voirNotice();
    }

    @FXML 
    void lancerPartie(ActionEvent event) {
    	
       Main.lancerPartie();
    }
    
    @FXML 
    void lancerParam(ActionEvent event) {
       Main.lancerParametre();
    }
    
    @FXML
    void btnImportExportAction(ActionEvent event) {
    	Main.ihmImportationExportation();
    }
}
