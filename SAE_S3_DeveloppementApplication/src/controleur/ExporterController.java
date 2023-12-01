/*
 * ExporterController.java                                      17 nov. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modele.Categorie;
import modele.Question;
import modele.Chiffrement;
import vue.Main;

/** 
 * Controleur de la vue Exportation.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class ExporterController {

	@FXML
	private Button btnRetour;

	@FXML
	private Button btnExporter;

	@FXML
	private Button btnSelectionnerTout;

	@FXML
	public ScrollPane scrollBarContenu;

	@FXML
	public ComboBox<String> comboBoxCategorie;

	private ArrayList<Question> arrayListQuestion;

	private boolean toutSelectionne = false;

	private String CategorieSelection;

	public ArrayList<String> questionCheck = new ArrayList<>();
	public VBox vBox = new VBox();


	@FXML
	void btnRetour(ActionEvent event) {
		//scrollBarContenu.getChildrenUnmodifiable().remove(0);
		Main.ihmChoix();
	}

	@FXML
	void btnSelectionnerToutAction(ActionEvent event) {

	}

	@FXML
	void comboBoxCategorieAction(ActionEvent event) {
		//clear la liste des questions affichés
		CategorieSelection = comboBoxCategorie.getValue();
		vBox.getChildren().clear();

		if (CategorieSelection!= null) {
			//cas ou l'on veut toutes les questions de toutes les catégories
			if (CategorieSelection.equals("Toutes les catégories") && !Main.onViensArriver) {
				setCheckBoxQuestion(Main.stockage.getListeQuestion());
				
			} else {
				//cas ou l'on veut que les questions d'une catégorie
				ArrayList<Question> listeQuestionParCategorie = Main.stockage.listeQuestionParCategorie((Categorie)Main.stockage.getListeCategorie().get(CategorieSelection));
				for (int i = 0; i<listeQuestionParCategorie.size(); i++) {
					CheckBox dynamicCheckBox = new CheckBox(listeQuestionParCategorie.get(i).getIntituleQuestion());
					dynamicCheckBox.setOnAction(event2 -> modifierListeCheck(dynamicCheckBox));
					if (questionCheck.contains(listeQuestionParCategorie.get(i).getIntituleQuestion())) {
						dynamicCheckBox.setSelected(true);
					}
					vBox.getChildren().add(dynamicCheckBox);
					// Gestionnaire d'événements pour chaque CheckBox
				}
				scrollBarContenu.setContent(vBox);
			}
			Main.onViensArriver=false;
		}
	}


	/**
	 * Initialise une comboBox avec tout les categories existantes.
	 * @param listeCategorie hashMap de la liste des catégories
	 */
	public void setComboBoxCategorie(HashMap<String, Categorie> listeCategorie) {
		// Ajoute l'option toutes les catégories a la comboBox
		comboBoxCategorie.getItems().add("Toutes les catégories");
		comboBoxCategorie.getSelectionModel().selectFirst();
		// Ajoute toute les catégories une a une a la comboBox
		for (Map.Entry entry : listeCategorie.entrySet()) {
			comboBoxCategorie.getItems().add(((Categorie)entry.getValue()).getIntituleCategorie());
			
		}
	}
	
	/**
	 * Initialise une comboBox avec tout les categories existantes.
	 * @param listeCategorie hashMap de la liste des catégories
	 */
	public void setCheckBoxQuestion(HashMap<String, Question> listeQuestion) {
		for (Map.Entry entry : listeQuestion.entrySet()) {
			CheckBox dynamicCheckBox = new CheckBox(((Question)entry.getValue()).getIntituleQuestion());
			dynamicCheckBox.setOnAction(event2 -> modifierListeCheck(dynamicCheckBox));
			if (questionCheck.contains(((Question)entry.getValue()).getIntituleQuestion())) {
				dynamicCheckBox.setSelected(true);
			}
			vBox.getChildren().add(dynamicCheckBox);
		}
		scrollBarContenu.setContent(vBox);
	}
	
	/**
	 * @return la comboBox des catégorie 
	 */
	public ComboBox getComboBoxCategorie() {
		return comboBoxCategorie;
	}

	/**
	 * @return la vBox qui contient les checkBox des question 
	 */
	public VBox getCheckListeQuestion() {
		return vBox;
	}

	/**
	 * Lorsqu'une checkbox est sélectionné ajoute l'intitule de la qustion
	 * à la liste des question sélectionné 
	 */
	private void modifierListeCheck(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			questionCheck.add(checkBox.getText());
		} else {
			questionCheck.remove(checkBox.getText());
		}
	}

	@FXML
	void btnExporterAction(ActionEvent event) {
		arrayListQuestion = new ArrayList<>();
		HashMap<String, Question> listeQuestion = Main.stockage.getListeQuestion();
		
		// Regarde une a une les questions pour voir si leur intitulé appartient a la liste des questions sélectionné
		for (Question question : listeQuestion.values()) {
			if (questionCheck.contains(question.getIntituleQuestion())) {
				// Ajoute la question correspondante a la liste des questions a exporté
				arrayListQuestion.add(question);
			}
		}
		// Créer le csv des questions
		Main.stockage.exportCSV(arrayListQuestion);
		// Change de vue pour celle d'envoie
		Main.ihmEnvoie();
	}

}
