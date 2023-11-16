/*
 * PartieController.java                                      31 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package controleur;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import vue.Main;
import modele.Quiz;
import modele.Categorie;
import modele.Question;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
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
    private ComboBox comboBoxCategorie;
    
    public ComboBox getComboBoxCategorie() {
    	return comboBoxCategorie;
    }
    
    @FXML
    void LancerQuiz(ActionEvent event) {
    	if (comboBoxCategorie.getValue() == null) {
    		//TODO pop-up Aucune catégorie sélectionnée (la partie ne se lance pas)
    	} else {
    		RadioButton difficulte = (RadioButton)choixDifficultes.getSelectedToggle();
    		int difficulteReelle;
    		if (difficulte.getText().equals("Indifférent")) {
    			difficulteReelle = 0;
    		} else {
    			difficulteReelle = Integer.parseInt(difficulte.getText());
    		}
    		RadioButton nbQuestion = (RadioButton)nombreQuestion.getSelectedToggle();
    		Quiz quiz = new Quiz(difficulteReelle,(Categorie)Main.stockage.
    				getListeCategorie().get(comboBoxCategorie.getValue()),
    				Main.stockage);
    		if (quiz.quantiteQuestionOk(Integer.parseInt(nbQuestion.getText()))) {
    			Main.repondreQuestion(quiz , 0);
    		} else if (quiz.getNombreQuestions()!=0){
    			//TODO Pop-up pas assez de question
    			// Le nombre de question trouvées est : nbQuestion.getText() Attention c'est déjà une String.
    			// Si le joueur veux continuer alors il continu.
    			Main.repondreQuestion(quiz, 0);
    		} else {
    			// Il n'y a aucune questions correspondantes
    			//Pop-up mais pas de possibilité de continuer
    			System.out.println("Pas de question disponible.");
    		}
    	}
    }
    
    public void setListeCategorie(HashMap<String, Categorie> listeCategorie) {
    	comboBoxCategorie.getItems().add("Indifférent");
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

