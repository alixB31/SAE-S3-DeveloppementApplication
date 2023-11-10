/*
 * ParametreQuestionController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import vue.Main;

/**
 * Contrôleur de la vue ihmParametreQuestion
 * @author rayanibrahime
 *
 */
public class ParametreQuestionController {

    @FXML
    private Button btnEditerRepNOk1;

    @FXML
    private Button btnEditerRepNOk2;

    @FXML
    private Button btnEditerRepNOk3;

    @FXML
    private Button btnEditerRepNOk4;

    @FXML
    private Button btnEditerRepOk;

    @FXML
    private Button btnFeedback;

    @FXML
    private Button btnModifierQuestion;

    @FXML
    private Button btnNouvelleReponse;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnSuppRepNOk1;

    @FXML
    private Button btnSuppRepNOk2;

    @FXML
    private Button btnSuppRepNOk3;

    @FXML
    private Button btnSuppRepNOk4;

    @FXML
    void EditerRep(ActionEvent event) {
        /*on crée l'interface pour modifier l'intitulé d'une question*/
        TextInputDialog boiteSaisie = new TextInputDialog("?"); 
        boiteSaisie.setHeaderText("Modifier la réponse"); 
        boiteSaisie.setTitle("Question"); 
        boiteSaisie.setContentText("Entrez votre nouvelle réponsse: ");
        boiteSaisie.showAndWait();
    }

    @FXML
    void ModifierQuestion(ActionEvent event) {
        TextInputDialog boiteSaisie = new TextInputDialog("?"); 
        boiteSaisie.setHeaderText("Modifier la question"); 
        boiteSaisie.setTitle("Question"); 
        boiteSaisie.setContentText("Entrez votre nouvelle question: ");
        boiteSaisie.showAndWait();
    }

    @FXML
    void NouvelleReponses(ActionEvent event) {
        //TODO
    }

    @FXML
    void afficherFeedback(ActionEvent event) {
        //TODO
    }

    @FXML
    void confirmerSupression(ActionEvent event) {
        /*
         * Création d'une boîte d'alerte de type confirmation.
         * Elle est dotée de 3 boutons : oui, non, et annuler
         */
        Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION, 
                "Confirmez-vous votre choix ?",
                ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        boiteAlerte.showAndWait();  
    }

    @FXML
    void retourCategorie(ActionEvent event) {
    	//
        Main.voirParamCategorie("Général");
    }

}

