/*
 * ParametreCategorieController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.ArrayList;
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
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;
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
	public Label nomCategorie;

	private String categorieChoisi;
	
	private int difficulte;
	
	private String feedBack;
	
	/** Liste des réponses fausses d'une question, il y en a de 1 à 4*/
	private ArrayList<String> listeReponsesFausses;
	
	@FXML
	public ComboBox<String> comboBoxCategorie;

	public void setNomCategorie(String categorie) {
		categorieChoisi = categorie;
		System.out.println(categorie);
	}

	@FXML
	void ajouterQuestion(ActionEvent event) {

		// Créer des boutons radio
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton radio1 = new RadioButton("1");
		radio1.setToggleGroup(toggleGroup);
		RadioButton radio2 = new RadioButton("2");
		radio2.setToggleGroup(toggleGroup);
		RadioButton radio3 = new RadioButton("3");
		radio3.setToggleGroup(toggleGroup);

		// Ajouter des marges aux boutons radio
		GridPane.setMargin(radio1, new Insets(5, 0, 0, 30));
		GridPane.setMargin(radio2, new Insets(5, 0, 0, 50));
		GridPane.setMargin(radio3, new Insets(5, 0, 0, 70));

		// Créer un champ de saisie (TextField)
		TextField textField = new TextField();
		textField.setPromptText("obligatoire");
		TextField textFieldVrai = new TextField();
		textFieldVrai.setPromptText("obligatoire");
		TextField textFieldFaux = new TextField();
		textFieldFaux.setPromptText("obligatoire");
		TextField textFieldFaux2 = new TextField();
		textFieldFaux2.setPromptText("optionnel");
		TextField textFieldFaux3 = new TextField();
		textFieldFaux3.setPromptText("optionnel");
		TextField textFieldFaux4 = new TextField();
		textFieldFaux4.setPromptText("optionnel");
		TextField textFieldFeedBack = new TextField();
		textFieldFeedBack.setPromptText("optionnel");

		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.setTitle("Nouvelle Question");

		// Configuration du layout de la popup
		GridPane popupLayout = new GridPane();
		popupLayout.setPadding(new Insets(10, 10, 10, 10));
		popupLayout.setVgap(8);
		popupLayout.setHgap(10);


		// Ajout des éléments au layout de la popup
		popupLayout.addRow(0, new Label("intitilé de la question:"), textField);
		popupLayout.addRow(1, new Label("Difficulté de la question:"), radio1, radio2, radio3);
		popupLayout.addRow(2, new Label("Réponse vrai:"), textFieldVrai);
		popupLayout.addRow(3, new Label("Réponse fausse:"), textFieldFaux);
		popupLayout.addRow(4, new Label("Réponse fausse:"), textFieldFaux2);
		popupLayout.addRow(5, new Label("Réponse fausse:"), textFieldFaux3);
		popupLayout.addRow(6, new Label("Réponse fausse:"), textFieldFaux4);
		popupLayout.addRow(7, new Label("FeedBack:"), textFieldFeedBack);

		// Bouton de validation
		Button boutonValider = new Button("Valider");
		Button boutonAnnuler = new Button("Annuler");
		boutonValider.setDisable(true);
		
		//categorie courante de la question a créer
		Categorie CategorieCourante = (Categorie) Main.stockage.getListeCategorie().get(categorieChoisi);
		
		boutonValider.setOnAction(e -> {
			if (!textField.getText().isEmpty() && !textFieldVrai.getText().isEmpty() && !textFieldFaux.getText().isEmpty()
					&& (radio1.isSelected() || radio2.isSelected() || radio3.isSelected())) {
				
				//recuperation de la radio sélectionnez 
				difficulte = (radio1.isSelected()) ? 1 : (radio2.isSelected()) ? 2 : (radio3.isSelected()) ? 3 : 0;
				// création de l'array list des réponses fausses.
				listeReponsesFausses = new ArrayList<>();
				//ajout des reponses fausses a l'array list si elles sont différent de null et de ""
				listeReponsesFausses.add(textFieldFaux.getText());
				if (textFieldFaux2.getText() !=null && !textFieldFaux2.getText().isBlank() && textFieldFaux2.getText().trim() != "" ) {
					listeReponsesFausses.add(textFieldFaux2.getText());
				}
				if (textFieldFaux3.getText() !=null && !textFieldFaux3.getText().isBlank() && textFieldFaux3.getText().trim() != "" ) {
					listeReponsesFausses.add(textFieldFaux3.getText());
				}
				if (textFieldFaux4.getText() !=null && !textFieldFaux4.getText().isBlank() && textFieldFaux4.getText().trim() != "" ) {
					listeReponsesFausses.add(textFieldFaux4.getText());
				}
				if (textFieldFeedBack.getText() !=null && !textFieldFeedBack.getText().isBlank() && textFieldFeedBack.getText().trim() != "" ) {
					feedBack = textFieldFeedBack.getText();
				}

				//création de la nouvelle question
				if (Main.stockage.ajouterQuestion(new Question(textField.getText().trim(), CategorieCourante, difficulte, null, textFieldVrai.getText(), feedBack))) {
					comboBoxCategorie.getItems().add(textField.getText());
					
            		System.out.println(Main.stockage.getListeQuestion());
            	} else {
            		// peu etre modifier cela 
            		Alert alert = new Alert(Alert.AlertType.WARNING);
    				alert.setTitle("Question déja existante");
    				alert.setHeaderText(null);
    				alert.setContentText("L'intitulé de la question que vous voulez créer existe déjà dans cette catégorie.");
    				alert.showAndWait();
            	}
				popupStage.close();
				
			}
		});

		boutonAnnuler.setOnAction(e -> {
			popupStage.close();
		});
		
		//regarde que tout les textField sois remplis
		textField.textProperty().addListener((observable, oldValue, newValue) ->
		boutonValider.setDisable(
				newValue.trim().isEmpty() ||
				textFieldVrai.getText().trim().isEmpty() ||
				textFieldFaux.getText().trim().isEmpty() ||
				(toggleGroup.getSelectedToggle() == null)
				));

		textFieldVrai.textProperty().addListener((observable, oldValue, newValue) ->
		boutonValider.setDisable(
				textField.getText().trim().isEmpty() ||
				newValue.trim().isEmpty() ||
				textFieldFaux.getText().trim().isEmpty() ||
				(toggleGroup.getSelectedToggle() == null)
				));

		textFieldFaux.textProperty().addListener((observable, oldValue, newValue) ->
		boutonValider.setDisable(
				textField.getText().trim().isEmpty() ||
				textFieldVrai.getText().trim().isEmpty() ||
				newValue.trim().isEmpty() ||
				(toggleGroup.getSelectedToggle() == null)
				));

		toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
		boutonValider.setDisable(
				textField.getText().trim().isEmpty() ||
				textFieldVrai.getText().trim().isEmpty() ||
				textFieldFaux.getText().trim().isEmpty() ||
				(newValue == null)
				));

		// Ajout du bouton de validation et d'annulation au layout de la popup
		popupLayout.addRow(8, boutonValider);
		popupLayout.addRow(8, boutonAnnuler);

		// Configuration de la scène de la popup
		Scene popupScene = new Scene(popupLayout, 500, 350);
		popupStage.setScene(popupScene);
		popupStage.showAndWait();

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
			if(Main.stockage.supprimerElementListeQuestion(comboBoxCategorie.getValue())) {
				comboBoxCategorie.getItems().remove(comboBoxCategorie.getValue());
			} else {
				// La suppression a échoué - Pop-up
			}
		}
	}
}
