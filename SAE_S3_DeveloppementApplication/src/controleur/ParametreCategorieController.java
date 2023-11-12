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
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
	private Label nomCategorie = new Label();

	@FXML
	private ComboBox<String> comboBoxCategorie;

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie.setText(nomCategorie);
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
		textField.setPromptText("?");
		TextField textFieldVrai = new TextField();
		textFieldVrai.setPromptText("?");
		TextField textFieldFaux = new TextField();
		textFieldFaux.setPromptText("?");

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

		// Bouton de validation
		Button boutonValider = new Button("Valider");
		Button boutonAnnuler = new Button("Annuler");
		boutonValider.setDisable(true);

		boutonValider.setOnAction(e -> {
			if (!textField.getText().isEmpty() && !textFieldVrai.getText().isEmpty() && !textFieldFaux.getText().isEmpty()
					&& (radio1.isSelected() || radio2.isSelected() || radio3.isSelected())) {
				System.out.println("Intitulé de la question : " + textField.getText());
				comboBoxCategorie.getItems().add(textField.getText());
				System.out.println("Difficulté de la question : " +
						(radio1.isSelected() ? "1" : (radio2.isSelected() ? "2" : "3")));
				System.out.println("Réponse vraie : " + textFieldVrai.getText());
				System.out.println("Réponse fausse : " + textFieldFaux.getText());
				popupStage.close();
			} else {
				// Afficher une alerte pour informer l'utilisateur de remplir tous les champs
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Champs manquants");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez remplir tous les champs.");
				alert.showAndWait();
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
//		// Activation du bouton de validation lorsque tous les champs sont remplis
//		textField.textProperty().addListener((observable, oldValue, newValue) ->
//		boutonValider.setDisable(newValue.trim().isEmpty()));
//
//		textFieldVrai.textProperty().addListener((observable, oldValue, newValue) ->
//		boutonValider.setDisable(newValue.trim().isEmpty()));
//
//		textFieldFaux.textProperty().addListener((observable, oldValue, newValue) ->
//		boutonValider.setDisable(newValue.trim().isEmpty()));

		// Ajout du bouton de validation et d'annulation au layout de la popup
		popupLayout.addRow(4, boutonValider);
		popupLayout.addRow(4, boutonAnnuler);

		// Configuration de la scène de la popup
		Scene popupScene = new Scene(popupLayout, 500, 200);
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
			comboBoxCategorie.getItems().remove(comboBoxCategorie.getValue());
		}
	}
}
