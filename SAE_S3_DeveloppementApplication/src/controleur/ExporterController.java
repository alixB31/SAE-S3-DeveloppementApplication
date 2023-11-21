package controleur;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modele.Categorie;
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

	private boolean allSelected = false;

	private String CategorieSelection;

	private ArrayList<String> questionCheck = new ArrayList<>();
	public VBox vBox = new VBox();
	

	@FXML
	void btnRetour(ActionEvent event) {
		vBox.getChildren().clear();
		Main.ihmChoix();
	}

	@FXML
	void btnSelectionnerToutAction(ActionEvent event) {

		allSelected = !allSelected;

		for (javafx.scene.Node node : vBox.getChildren()) {
			if (node instanceof CheckBox) {
//				CheckBox checkBox = (CheckBox) node;
//				checkBox.setSelected(allSelected);
				CheckBox checkBox = (CheckBox) node;
	            checkBox.setSelected(allSelected);
	            // Si la CheckBox est cochée, l'ajouter à la liste
	            if (checkBox.isSelected()) {
	            	checkBox.setOnAction(event2 -> handleCheckBoxAction(checkBox));
	            }
			}
		}
	}

	@FXML
	void comboBoxCategorieAction(ActionEvent event) {
		System.out.println("On est la");
		//clear la liste des questions affichés

		vBox.getChildren().clear();
		CategorieSelection = comboBoxCategorie.getValue();
		HashMap<String, Categorie> listeCategorie = Main.stockage.getListeCategorie();

		//cas ou l'on veut toutes les questions de toutes les catégories
		if (CategorieSelection == "Toutes les catégories") {
			for (Categorie categorie : listeCategorie.values()) {
				ArrayList<Question> listeQuestion = Main.stockage.listeQuestionParCategorie((Categorie)Main.stockage.getListeCategorie().get(categorie.getIntituleCategorie()));
				for (int y = 0; y<listeQuestion.size(); y++) {
					CheckBox dynamicCheckBox = new CheckBox(listeQuestion.get(y).getIntituleQuestion());
					dynamicCheckBox.setOnAction(event2 -> handleCheckBoxAction(dynamicCheckBox));
					 // Cocher seulement la CheckBox correspondant au catégorie déja checké
				    if (questionCheck.contains(listeQuestion.get(y).getIntituleQuestion())) {
				        dynamicCheckBox.setSelected(true);
				    }
					vBox.getChildren().add(dynamicCheckBox);
				}
			}

		} 

		//cas ou l'on veut que les questions d'une catégorie
		ArrayList<Question> listeQuestionParCategorie = Main.stockage.listeQuestionParCategorie((Categorie)Main.stockage.getListeCategorie().get(CategorieSelection));
		for (int i = 0; i<listeQuestionParCategorie.size(); i++) {
			CheckBox dynamicCheckBox = new CheckBox(listeQuestionParCategorie.get(i).getIntituleQuestion());
			dynamicCheckBox.setOnAction(event2 -> handleCheckBoxAction(dynamicCheckBox));
			if (questionCheck.contains(listeQuestionParCategorie.get(i).getIntituleQuestion())) {
		        dynamicCheckBox.setSelected(true);
		    }
			vBox.getChildren().add(dynamicCheckBox);
			// Gestionnaire d'événements pour chaque CheckBox
			

		}

		scrollBarContenu.setContent(vBox);

	}

	// Méthode pour gérer les événements de CheckBox
	private void handleCheckBoxAction(CheckBox checkBox) {
		if (checkBox.isSelected()) {
			questionCheck.add(checkBox.getText());
			
		} else {
			questionCheck.remove(checkBox.getText());
		}
	}
	
	@FXML
	void btnExporterAction(ActionEvent event) {
		Main.ihmEnvoie();
	}

}
