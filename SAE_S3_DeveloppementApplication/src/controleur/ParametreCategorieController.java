/*
 * ParametreCategorieController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
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
        
        // Créer une boîte de dialogue personnalisée
        Dialog<String> dialog = new Dialog<>();
        dialog.setHeaderText("Ajouter une nouvelle question");
        dialog.setTitle("Question");
        
     // Créer des boutons radio
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton radio1 = new RadioButton("1");
        radio1.setToggleGroup(toggleGroup);
        RadioButton radio2 = new RadioButton("2");
        radio2.setToggleGroup(toggleGroup);
        RadioButton radio3 = new RadioButton("3");
        radio3.setToggleGroup(toggleGroup);
        
     // Ajouter des marges aux boutons radio
        GridPane.setMargin(radio1, new Insets(5, 0, 0, 10));
        GridPane.setMargin(radio2, new Insets(5, 0, 0, 65));
        GridPane.setMargin(radio3, new Insets(5, 0, 0, 120));
            
        // Créer un champ de saisie (TextField)
        TextField textField = new TextField();
        textField.setPromptText("?");
        TextField textFieldVrai = new TextField();
        textFieldVrai.setPromptText("?");
        TextField textFieldFaux = new TextField();
        textFieldFaux.setPromptText("?");
        
        // Ajouter le champ de saisie à la boîte de dialogue
        GridPane grid = new GridPane();
        grid.add(new Label("Entrez l'intitulé de votre question: "), 0, 0);
        grid.add(new Label("Choisissez un niveau de difficultés :"), 0 , 1 );
        grid.add(new Label("Proposez une réponse vraie :"), 0, 2);
        grid.add(new Label("Proposez une réponse fausse :"), 0, 3);
        grid.add(radio1, 1, 1);
        grid.add(radio2, 1, 1);
        grid.add(radio3, 1, 1);
        grid.add(textField, 1, 0);
        grid.add(textFieldVrai, 1, 2);
        grid.add(textFieldFaux, 1, 3);
        dialog.getDialogPane().setContent(grid);
        
        // Ajouter les boutons OK et Annuler
        ButtonType okButton = new ButtonType("OK", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        // Vérifier quelle radio a été sélectionnée
        RadioButton selectedRadio = (RadioButton) toggleGroup.getSelectedToggle();
        
        // Récupérer le résultat sélectionné lorsque le bouton OK est cliqué
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButton) {
            	if (!textField.getText().trim().isEmpty() && !textFieldVrai.getText().trim().isEmpty()) {
                    return textField.getText();
                }
            }
            return null;
        });

        // Afficher la boîte de dialogue et attendre la réponse
        dialog.showAndWait();
        comboBoxCategorie.getItems().add(dialog.getResult());
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
