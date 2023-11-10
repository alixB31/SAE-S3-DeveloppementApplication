/*
 * ParametreCategorieController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import vue.Main;

/**
 * Contrôleur de la vue ihmParametreCategorie
 * @author rayanibrahime
 *
 */
public class ParametreCategorieController {

    @FXML
    private Button ajouterQuestion;

    @FXML
    private Button btnRetour1;

    @FXML
    private Button btnSuivant;

    @FXML
    private ComboBox<String> comboBoxCategorie;

    @FXML
    void ajouterQuestion(ActionEvent event) {
        /* On crée l'interface pour ajouter une nouvelle catégorie à la liste déroulante */
        TextInputDialog boiteSaisieQuestion = new TextInputDialog("?");
        boiteSaisieQuestion.setHeaderText("Ajouter une nouvelle question");
        boiteSaisieQuestion.setTitle("Question");
        boiteSaisieQuestion.setContentText("Entrez l'intitulé de votre question: ");
        boiteSaisieQuestion.showAndWait();
        comboBoxCategorie.getItems().add(boiteSaisieQuestion.getResult());
    }

    @FXML
    void pageSuivante(ActionEvent event) {
        Main.voirParamQuestion();
    }

    @FXML
    void retourMenu(ActionEvent event) {
        Main.lancerParametre();
    }

    @FXML
    void editerIntitulerQuestion(ActionEvent event) {
        String categorieCourante = comboBoxCategorie.getValue();
        TextInputDialog boiteSaisie = new TextInputDialog(categorieCourante);
        boiteSaisie.setHeaderText("Modifier la question : " + categorieCourante);
        boiteSaisie.setTitle("question");
        boiteSaisie.setContentText("Entrez le nouveau intitulé de votre question : ");
        boiteSaisie.showAndWait();
        comboBoxCategorie.getItems().set(comboBoxCategorie.getItems().indexOf(categorieCourante),boiteSaisie.getResult());
    }

    @FXML
    void deleteQuestion(ActionEvent event) {
        /*
         * Création d'une boîte d'alerte de type confirmation.
         * Elle est dotée de 2 boutons : oui et non
         */
        Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION,
                        "Confirmez-vous votre choix ?",
                        ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> option = boiteAlerte.showAndWait();
        if (option.get() == ButtonType.YES) { // clic sur "oui"
            comboBoxCategorie.getItems().remove(comboBoxCategorie.getValue());
        }
    }
}
