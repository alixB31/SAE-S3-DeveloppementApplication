package controleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modele.Categorie;
import modele.ListeQuestion;
import modele.Question;
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

	private ArrayList<String> questionCheck = new ArrayList<>();
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
