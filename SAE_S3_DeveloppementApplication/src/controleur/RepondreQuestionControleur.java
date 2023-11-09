package controleur;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import vue.Main;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 * @author nathangirardin
 */
public class RepondreQuestionControleur {

    @FXML
    private Text Question;

    @FXML
    private Button btnValider;

    @FXML
    private Text categorieQuestion;

    @FXML
    private Text difficultéQuestion;

    @FXML
    private Text nbPageMax;

    @FXML
    private Text pageCourante;

    @FXML
    private RadioButton reponse1;

    @FXML
    private RadioButton reponse2;

    @FXML
    private RadioButton reponse3;

    @FXML
    private RadioButton reponse4;

    @FXML
    private RadioButton reponse5;

    @FXML
    void btnValiderAction(ActionEvent event) {
    	Main.RepondreQuestion();
    }

}
