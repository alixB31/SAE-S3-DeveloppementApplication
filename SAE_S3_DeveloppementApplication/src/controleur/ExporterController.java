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



	public void setComboBoxCategorie(HashMap<String, Categorie> listeCategorie) {
		comboBoxCategorie.getItems().add("Toutes les catégories");
		comboBoxCategorie.getSelectionModel().selectFirst();
		for (Map.Entry entry : listeCategorie.entrySet()) {
			comboBoxCategorie.getItems().add(((Categorie)entry.getValue()).getIntituleCategorie());
			
		}
	}

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

	public ComboBox getComboBoxCategorie() {
		return comboBoxCategorie;
	}

	public VBox getCheckListeQuestion() {
		return vBox;
	}

	// Méthode pour gérer les événements de CheckBox
	private void modifierListeCheck(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			questionCheck.add(checkBox.getText());
		} else {
			questionCheck.remove(checkBox.getText());
		}
		System.out.println(questionCheck);
	}

	@FXML
	void btnExporterAction(ActionEvent event) {
		arrayListQuestion = new ArrayList<>();
		HashMap<String, Question> listeQuestion = Main.stockage.getListeQuestion();
		for (Question question : listeQuestion.values()) {
			if (questionCheck.contains(question.getIntituleQuestion())) {
				arrayListQuestion.add(question);
			}
		}
		Main.stockage.exportCSV(arrayListQuestion);
		Main.ihmEnvoie();
	}

}
