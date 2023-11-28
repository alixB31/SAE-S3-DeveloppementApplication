/*
 * ParametreCategorieController.java                                      4 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;

import vue.Main;

/** 
 * Contrôleur de la vue ParametreCategorie.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */

public class ParametreCategorieController {

	@FXML
	private Button ajouterQuestion;

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnTerminer;

	/** TODO comment field role (attribute, association) */
	@FXML
	public Label nomCategorie;

	/** Catégorie de la page actuelle*/
	private String categorieChoisi;

	/** Difficulté d'une question de niveau 1 à 3*/
	private int difficulte;

	/** Feedback d'une question*/
	private String feedBack;

	/** Liste des réponses fausses d'une question, il y en a de 1 à 4*/
	private ArrayList<String> listeReponsesFausses;

	/** ComboBox de la liste des questions d'une catégorie*/
	@FXML
	public ComboBox<String> comboBoxQuestion;

	/** ComboBox de la liste des catégories*/
	@FXML
	public ComboBox<String> comboBox;

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
		GridPane.setMargin(radio1, new Insets(5, 0, 0, 5));
		GridPane.setMargin(radio2, new Insets(5, 0, 0, - 465));
		GridPane.setMargin(radio3, new Insets(5, 0, 0, - 435));

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

		// Agrandir la taille du TextField
		textField.setPrefWidth(500);

		Stage popupStage = new Stage();
		popupStage.initModality(Modality.APPLICATION_MODAL);
		popupStage.setTitle("Nouvelle Question");

		// Configuration du layout de la popup
		GridPane popupLayout = new GridPane();
		popupLayout.setPadding(new Insets(10, 10, 10, 10));
		popupLayout.setVgap(8);
		popupLayout.setHgap(10);


		// Ajout des éléments au layout de la popup
		popupLayout.addRow(0, new Label("Intitulé de la question:"), textField);
		popupLayout.addRow(1, new Label("Difficulté de la question:"), radio1, radio2, radio3);
		popupLayout.addRow(2, new Label("Réponse vraie:"), textFieldVrai);
		popupLayout.addRow(3, new Label("Réponse fausse:"), textFieldFaux);
		popupLayout.addRow(4, new Label(""), textFieldFaux2);
		popupLayout.addRow(5, new Label(""), textFieldFaux3);
		popupLayout.addRow(6, new Label(""), textFieldFaux4);
		popupLayout.addRow(7, new Label("FeedBack:"), textFieldFeedBack);

		// Bouton de validation
		Button boutonValider = new Button("Valider");
		Button boutonAnnuler = new Button("Annuler");
		boutonValider.setDisable(true);

		// Categorie courante de la question a créer
		Categorie categorieCourante = (Categorie) Main.stockage.getListeCategorie().get(categorieChoisi);
		
		// Cas ou on clique sur valider
		boutonValider.setOnAction(e -> {
			if (!textField.getText().isEmpty() && !textFieldVrai.getText().isEmpty() && !textFieldFaux.getText().isEmpty()
					&& (radio1.isSelected() || radio2.isSelected() || radio3.isSelected())) {

				// Recuperation de la radio sélectionnez 
				difficulte = (radio1.isSelected()) ? 1 : (radio2.isSelected()) ? 2 : (radio3.isSelected()) ? 3 : 0;
				// Création de l'array list des réponses fausses.
				listeReponsesFausses = new ArrayList<>();

				String textFieldVraiTexte = textFieldVrai.getText().trim();
				// Ajout des reponses fausses a l'array list si elles sont différent de null et de ""
				String textFieldFauxTexte = textFieldFaux.getText().trim();
				String textFieldFauxTexte2 = textFieldFaux2.getText().trim();
				String textFieldFauxTexte3 = textFieldFaux3.getText().trim();
				String textFieldFauxTexte4 = textFieldFaux4.getText().trim();

				listeReponsesFausses.add(textFieldFauxTexte);
				if (textFieldFaux2.getText() !=null && !textFieldFaux2.getText().isBlank() && textFieldFauxTexte2 != "") {
					if (!listeReponsesFausses.contains(textFieldFauxTexte2)) {
						listeReponsesFausses.add(textFieldFauxTexte2);
					} 
				}
				if (textFieldFaux3.getText() !=null && !textFieldFaux3.getText().isBlank() && textFieldFauxTexte3 != "") {
					if (!listeReponsesFausses.contains(textFieldFauxTexte3)) {
						listeReponsesFausses.add(textFieldFauxTexte3);
					}
				}
				if (textFieldFaux4.getText() !=null && !textFieldFaux4.getText().isBlank() && textFieldFauxTexte4 != "") {
					if (!listeReponsesFausses.contains(textFieldFauxTexte4)) {
						listeReponsesFausses.add(textFieldFauxTexte4);
					}
				}
				feedBack = textFieldFeedBack.getText();

				if (!listeReponsesFausses.contains(textFieldVraiTexte)) {
					// Création de la nouvelle question et ajout a la liste des questions
					if (Main.stockage.ajouterQuestion(new Question(textField.getText().trim(), categorieCourante, difficulte, listeReponsesFausses, textFieldVraiTexte, feedBack))) {
						comboBoxQuestion.getItems().add(textField.getText());
						comboBoxQuestion.setValue(textField.getText());
						// Ferme la popUp une fois que tout est bon
						popupStage.close();
					} else {
						ParametreController.afficherAlerte("Question non ajoutable","Une question avec le même intitulé existe déjà.");
					}
				} else {
					ParametreController.afficherAlerte("Question non ajoutable","Une réponse fausse est la même que la réponse vrai.");
				}

			}
		});
		
		// Ferme la popUp quand on clique sur annuler
		boutonAnnuler.setOnAction(e -> {
			popupStage.close();
		});

		// Regarde que tout les textField sois remplis
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
		Scene popupScene = new Scene(popupLayout, 750, 350);
		popupStage.setScene(popupScene);
		popupStage.showAndWait();

	}


	@FXML
	void deleteQuestion(ActionEvent event) {
		// Vérifie qu'une question a bien était sélectionnée
		if (comboBoxQuestion.getValue() == null) {
			ParametreController.afficherAlerte("Choisissez une question","Vous devez sélectionner une question pour pouvoir la supprimer");
		} else {
			Alert boiteAlerte = new Alert(Alert.AlertType.CONFIRMATION,
					"Confirmez-vous votre choix ?",
					ButtonType.YES, ButtonType.NO);
			Optional<ButtonType> option = boiteAlerte.showAndWait();

			if (option.get() == ButtonType.YES) { // clic sur "oui"
				if(Main.stockage.supprimerElementListeQuestion(comboBoxQuestion.getValue()+categorieChoisi)) {

					comboBoxQuestion.getItems().remove(comboBoxQuestion.getValue());
				} else {
					ParametreController.afficherAlerte("Suppresion Question","La suppresion de la question a échoué");
				}
			}
		}
	}

	@FXML
	void editerIntitulerQuestion(ActionEvent event) {

		// Vérifie qu'une question a bien était sélectionnée
		if (comboBoxQuestion.getValue() == null) {
			ParametreController.afficherAlerte("Choisissez une question","Vous devez sélectionner une question pour pouvoir la modifier");
		} else {

			// Ancien intitulé 
			String ancienIntitule = comboBoxQuestion.getValue();
			// Categorie courante de la question a modifier
			Categorie categorieCourante = (Categorie) Main.stockage.getListeCategorie().get(categorieChoisi);
			// Question courante
			String concatenation = ancienIntitule+categorieCourante.getIntituleCategorie();



			Question questionCourante = (Question) Main.stockage.getListeQuestion().get(concatenation);
			// Reponse vrai précédente
			String ancienneReponseVrai = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponseJusteQuestion();
			// Ancien feedback
			String ancienFeedback = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getFeedBackQuestion();	
			// Nombre de reponses fausses de l'ancienne question 
			int nombreReponseFausse = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().size();
			// Difficulté de l'ancienne question 
			int ancienneDifficulte = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getDifficulteQuestion();

			// ComboBox de toute les catégories
			comboBox = new ComboBox<>();
			HashMap<String, Categorie> map = Main.stockage.getListeCategorie();
			for (Map.Entry mapEntry: map.entrySet()) {
				comboBox.getItems().add(((Categorie) mapEntry.getValue()).getIntituleCategorie());

			}
			// Préselection de la catégorie actuelle de la question
			comboBox.setValue(categorieCourante.getIntituleCategorie());
			// Créer des boutons radio
			ToggleGroup toggleGroup = new ToggleGroup();
			RadioButton radio1 = new RadioButton("1");


			radio1.setToggleGroup(toggleGroup);
			RadioButton radio2 = new RadioButton("2");
			radio2.setToggleGroup(toggleGroup);
			RadioButton radio3 = new RadioButton("3");
			radio3.setToggleGroup(toggleGroup);
			//préselectionne l'ancienne difficuté
			switch (ancienneDifficulte) {
			case 1:
				radio1.setSelected(true);
				break;
			case 2:
				radio2.setSelected(true);
				break;
			default: 
				radio3.setSelected(true);
			}


			// Ajouter des marges aux boutons radio
			GridPane.setMargin(radio1, new Insets(5, 0, 0, 5));
			GridPane.setMargin(radio2, new Insets(5, 0, 0, - 465));
			GridPane.setMargin(radio3, new Insets(5, 0, 0, - 490));

			// Créer un champ de saisie (TextField)
			TextField textField = new TextField();
			textField.setText(ancienIntitule);
			TextField textFieldVrai = new TextField();
			textFieldVrai.setText(ancienneReponseVrai);

			// Affiche la premiere reponse fausse
			TextField textFieldFaux = new TextField();
			if (((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(0) !=null && 
					((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(0) != "") {
				String ancienneFausse1 = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(0);
				textFieldFaux.setText(ancienneFausse1);
			} 	
			TextField textFieldFaux2 = new TextField();
			// Si il y a plus de 1 reponse fausse, affiche les autres
			if (nombreReponseFausse>1) {
				if (((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(1) !=null && 
						((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(1) != "") {
					String ancienneFausse2 = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(1);
					textFieldFaux2.setText(ancienneFausse2);
				}
			} 
			TextField textFieldFaux3 = new TextField();
			if (nombreReponseFausse>2) {
				if (((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(2) !=null && 
						((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(2) != "") {
					String ancienneFausse3 = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(2);
					textFieldFaux3.setText(ancienneFausse3);
				}
			}
			TextField textFieldFaux4 = new TextField();
			if (nombreReponseFausse>3) {
				if (((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(3) !=null && 
						((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(3) != "") {
					String ancienneFausse4 = ((Question) Main.stockage.getListeQuestion().get(concatenation)).getReponsesFaussesQuestion().get(3);
					textFieldFaux4.setText(ancienneFausse4);
				}
			}


			TextField textFieldFeedBack = new TextField();
			textFieldFeedBack.setText(ancienFeedback);

			// Agrandir la taille du TextField
			textField.setPrefWidth(500);

			Stage popupStage = new Stage();
			popupStage.initModality(Modality.APPLICATION_MODAL);
			popupStage.setTitle("Modifier Question");

			// Configuration du layout de la popup
			GridPane popupLayout = new GridPane();
			popupLayout.setPadding(new Insets(10, 10, 10, 10));
			popupLayout.setVgap(8);
			popupLayout.setHgap(10);


			// Ajout des éléments au layout de la popup
			popupLayout.addRow(0, new Label("Intitulé de la question:"), textField);
			popupLayout.addRow(1, new Label("Difficulté de la question:"), radio1, radio2, radio3);
			popupLayout.addRow(2, new Label("Réponse vrai:"), textFieldVrai);
			popupLayout.addRow(3, new Label("Réponse fausse:"), textFieldFaux);
			popupLayout.addRow(4, new Label(""), textFieldFaux2);
			popupLayout.addRow(5, new Label(""), textFieldFaux3);
			popupLayout.addRow(6, new Label(""), textFieldFaux4);
			popupLayout.addRow(7, new Label("FeedBack:"), textFieldFeedBack);
			popupLayout.addRow(8, new Label("Categorie:"), comboBox);

			// Bouton de validation
			Button boutonValider = new Button("Valider");
			Button boutonAnnuler = new Button("Annuler");
			boutonValider.setDisable(true);
			
			// Cas ou on clique sur valider
			boutonValider.setOnAction(e -> {
				if (!textField.getText().isEmpty() && !textFieldVrai.getText().isEmpty() && !textFieldFaux.getText().isEmpty()
						&& (radio1.isSelected() || radio2.isSelected() || radio3.isSelected())) {

					//recuperation de la radio sélectionnez 
					difficulte = (radio1.isSelected()) ? 1 : (radio2.isSelected()) ? 2 : (radio3.isSelected()) ? 3 : 0;

					String textFieldVraiTexte = textFieldVrai.getText().trim();
					// création de l'array list des réponses fausses.
					listeReponsesFausses = new ArrayList<>();
					//ajout des reponses fausses a l'array list si elles sont différent de null et de ""
					String textFieldFauxTexte = textFieldFaux.getText().trim();
					String textFieldFauxTexte2 = textFieldFaux2.getText().trim();
					String textFieldFauxTexte3 = textFieldFaux3.getText().trim();
					String textFieldFauxTexte4 = textFieldFaux4.getText().trim();

					listeReponsesFausses.add(textFieldFauxTexte);
					if (textFieldFaux2.getText() !=null && !textFieldFaux2.getText().isBlank() && textFieldFauxTexte2 != "") {
						if (!listeReponsesFausses.contains(textFieldFauxTexte2)) {
							listeReponsesFausses.add(textFieldFauxTexte2);
						} 
					}
					if (textFieldFaux3.getText() !=null && !textFieldFaux3.getText().isBlank() && textFieldFauxTexte3 != "") {
						if (!listeReponsesFausses.contains(textFieldFauxTexte3)) {
							listeReponsesFausses.add(textFieldFauxTexte3);
						}
					}
					if (textFieldFaux4.getText() !=null && !textFieldFaux4.getText().isBlank() && textFieldFauxTexte4 != "") {
						if (!listeReponsesFausses.contains(textFieldFauxTexte4)) {
							listeReponsesFausses.add(textFieldFauxTexte4);
						}
					}

					feedBack = textFieldFeedBack.getText();
					Categorie nouvelleCategorie = (Categorie) Main.stockage.getListeCategorie().get(comboBox.getValue()); 

					//nouvelle concatenation qui seras créer si la question change de catégorie.
					String NouvelleConcatenation = textField.getText()+nouvelleCategorie.getIntituleCategorie();

					//modification de la question
					if (!listeReponsesFausses.contains(textFieldVraiTexte)) {
						//création de la nouvelle question et ajout a la liste des questions
						if (nouvelleCategorie.equals(categorieCourante) 
								&&	Main.stockage.modifierQuestion(questionCourante,textField.getText(), nouvelleCategorie, difficulte, listeReponsesFausses, textFieldVraiTexte, feedBack, concatenation)
								) {

							comboBoxQuestion.getItems().set(comboBoxQuestion.getItems().indexOf(ancienIntitule), textField.getText().trim());
							comboBoxQuestion.setValue(textField.getText().trim());
							
							//ferme la popUp
							popupStage.close();
							
							//on regarde si la question existe deja dans la nouvelle catégorie
						} else if(!nouvelleCategorie.equals(categorieCourante) && !Main.stockage.getListeQuestion().containsKey(NouvelleConcatenation)) {			
							Main.stockage.modifierQuestion(questionCourante,textField.getText(), nouvelleCategorie, difficulte, listeReponsesFausses, textFieldVraiTexte, feedBack, concatenation);
							comboBoxQuestion.getItems().remove(comboBoxQuestion.getValue());				
							//si elle existe pas on l'enleve de la comboBox de la categorie courrante,sinon on ne peut pas la trasférer et rien ne se passe
							
							//ferme la popUp
							popupStage.close();
						} else if(nouvelleCategorie != categorieCourante && Main.stockage.getListeQuestion().containsKey(NouvelleConcatenation)){
							ParametreController.afficherAlerte("Question non transférable","Une question avec le même intitulé existe déjà dans la catégorie cible");
						} else {
							ParametreController.afficherAlerte("Question non modifiable","Une question avec le même intitulé existe déjà dans cette catégorie");
						}

					} else {
						ParametreController.afficherAlerte("Question non ajoutable","Une réponse fausse et la même que la réponse vrai.");
					}					
				}
			});
			
			// Ferme la popo Up quand on clique sur annuler
			boutonAnnuler.setOnAction(e -> {
				popupStage.close();
			});

			// Regarde que tout les textField sois remplis
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
			Scene popupScene = new Scene(popupLayout, 800, 400);
			popupStage.setScene(popupScene);
			popupStage.showAndWait();
		}

	}

	/** 
	 * Definis la categorie courante
	 * @param categorie courante
	 */
	public void setNomCategorie(String categorie) {
		categorieChoisi = categorie;
	}

	/** 
	 * Initialiase la liste des questions de la catégorie
	 */
	public void setComboBoxQuestion() {
		ArrayList<Question> listeQuestionParCategorie = Main.stockage.listeQuestionParCategorie((Categorie)Main.stockage.getListeCategorie().get(categorieChoisi));
		for (int i = 0; i<listeQuestionParCategorie.size(); i++) {
			comboBoxQuestion.getItems().add(listeQuestionParCategorie.get(i).getIntituleQuestion());
		}

	}
	

	@FXML
	void terminer(ActionEvent event) {
		Main.retourMenuPrincipal();
	}
	
	@FXML
	void retourMenu(ActionEvent event) {
		Main.lancerParametre();
	}
}

