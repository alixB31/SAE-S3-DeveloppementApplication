/*
 * PartieController.java                                      31 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import vue.Main;
import modele.Quiz;
import modele.Categorie;
import modele.Joueur;
import modele.Question;

/** 
 * Controleur de la vue de la Partie.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class PartieController {

    @FXML
    private Button btnLancer;

    @FXML
    private Button btnRetour;

    @FXML
    private ToggleGroup nombreQuestion;
    
    @FXML
    private ToggleGroup choixDifficultes;
    
    @FXML
    private TextField pseudoJoueur;
    
    @FXML
    private ComboBox<String> comboBoxCategorie;
    
    public ComboBox getComboBoxCategorie() {
    	return comboBoxCategorie;
    }
    
    @FXML
    void LancerQuiz(ActionEvent event) {
    	Main.setPseudoJoueur(pseudoJoueur.getText());
		RadioButton difficulte = (RadioButton)choixDifficultes.getSelectedToggle();
		int difficulteReelle;
		if (difficulte.getText().equals("Indifférent")) {
			difficulteReelle = 0;
		} else {
			difficulteReelle = Integer.parseInt(difficulte.getText());
		}
		Categorie categorie;
		if (((String)comboBoxCategorie.getValue()).equals("Toutes les catégories")) {
			categorie = null;
		} else {
			categorie = (Categorie)Main.stockage.getListeCategorie().get((String)comboBoxCategorie.getValue());
		}
		RadioButton nbQuestion = (RadioButton)nombreQuestion.getSelectedToggle();
		Quiz quiz = new Quiz(difficulteReelle,Integer.parseInt(nbQuestion.getText()),categorie,
				Main.stockage);
		if (quiz.quantiteQuestionOk(Integer.parseInt(nbQuestion.getText()))) {
			Main.repondreQuestion(quiz , 0);
		} else if (quiz.getNombreQuestions()!=0){
			// Si le nombre ne correspond pas mais qu'il y a tout de même des questions qui correspondent,
			// alors on propose à l'utilisateur d'annuler le lancement du quiz ou de continuer en lui
			// précisant le nombre de questions correspondantes.
			Alert popUpContinuer = new Alert(AlertType.CONFIRMATION);
			popUpContinuer.setTitle("Pas assez de question");
			popUpContinuer.setHeaderText("Il y a seulement " + quiz.getNombreQuestions() + " questions qui correpondent à vos critères,"
					+ " cependant vous en demandez " + nbQuestion.getText());
			popUpContinuer.setContentText("Voulez vous continuer?");
			
			Optional<ButtonType> reponseContinuer = popUpContinuer.showAndWait();
			if (reponseContinuer.get() == ButtonType.OK) {
				Main.repondreQuestion(quiz, 0);
			}
			
		} else {
			// Il n'y a aucune questions correspondantes
			//Pop-up mais pas de possibilité de continuer
			Alert popUpPasDeQuestion = new Alert(AlertType.INFORMATION);
			popUpPasDeQuestion.setTitle("Aucune question disponible");
			popUpPasDeQuestion.setHeaderText("Il n'y a pas de question correspondante aux paramètres que vous avez entré.");
			popUpPasDeQuestion.setContentText(null);
			popUpPasDeQuestion.showAndWait();
		}
    }
    
    public void setListeCategorie(HashMap<String, Categorie> listeCategorie) {
    	comboBoxCategorie.getItems().add("Toutes les catégories");
    	// La valeur par défaut est "Indifférent"
    	comboBoxCategorie.getSelectionModel().selectFirst();
    	// Ajout des catégories existantes.
    	for (Map.Entry entry : listeCategorie.entrySet()) {
    		comboBoxCategorie.getItems().add(((Categorie)entry.getValue()).getIntituleCategorie());
    	}
    }
    
    @FXML
    void RetourMenu(ActionEvent event) {
        Main.retourMenuPrincipal();
    }

}

