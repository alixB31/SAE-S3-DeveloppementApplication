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

	@FXML
	private VBox vBoxQuestions;
	
	private boolean allSelected = false;
	
	private String CategorieSelection;

	public VBox vBox = new VBox();

	@FXML
	void btnRetour(ActionEvent event) {
		Main.ihmChoix();
	}

	@FXML
	void btnSelectionnerToutAction(ActionEvent event) {

		allSelected = !allSelected;

        for (javafx.scene.Node node : vBox.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                checkBox.setSelected(allSelected);
            }
        }
	}

		@FXML
		void comboBoxCategorieAction(ActionEvent event) {

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
						vBox.getChildren().add(dynamicCheckBox);
					}
				}

			} 

			ArrayList<Question> listeQuestionParCategorie = Main.stockage.listeQuestionParCategorie((Categorie)Main.stockage.getListeCategorie().get(CategorieSelection));
			for (int i = 0; i<listeQuestionParCategorie.size(); i++) {
				CheckBox dynamicCheckBox = new CheckBox(listeQuestionParCategorie.get(i).getIntituleQuestion());
				vBox.getChildren().add(dynamicCheckBox);
			}

			scrollBarContenu.setContent(vBox);
		}

		@FXML
		void btnExporterAction(ActionEvent event) {
			Main.ihmEnvoie();
		}

	}
