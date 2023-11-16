package controleur;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import modele.Quiz;
import modele.Question;
import vue.Main;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 * @author nathangirardin
 */
public class RepondreQuestionControleur {

    @FXML
    private Text question;

    @FXML
    private Button btnValider;

    @FXML
    private Text categorieQuestion;

    @FXML
    private Text difficultéQuestion;

    @FXML
    private Text nbPageMax;

    @FXML
    private Text pageCourante;

    @FXML
    private RadioButton reponse1;

    @FXML
    private RadioButton reponse2;

    @FXML
    private RadioButton reponse3;

    @FXML
    private RadioButton reponse4;

    @FXML
    private RadioButton reponse5;

    @FXML
    private ToggleGroup reponses;
    
    private Quiz quiz;
    
    private int indiceQuestionCourrante;
    
    @FXML
    void btnValiderAction(ActionEvent event) {
    	
    	// Renvoie sur une question ou s'il en reste, sinon envoie
    	// sur la page des résultat.
    	if (indiceQuestionCourrante <= quiz.getNombreQuestions()) {
    		Main.repondreQuestion(quiz, indiceQuestionCourrante);
    	} else {
    		Main.ihmScoreQuiz();
    	}
    	
    }

    public void setQuiz(Quiz quiz) {
    	this.quiz = quiz;
    }
    
    public void setIndiceQuestion(int indice) {
    	this.indiceQuestionCourrante = indice;
    }
    
    public void setDifficulte(String niveau) {
    	this.difficultéQuestion.setText(niveau);
    }
    
    public void setCategorie(String categorie) {
    	this.categorieQuestion.setText(categorie);
    }
    
    public void setNumeroQuestion(String numeroQuestionEnCour) {
    	this.pageCourante.setText(numeroQuestionEnCour);
    }
    
    public void setNombreQuestionTotal(String nombreQuestionTotal) {
    	this.nbPageMax.setText(nombreQuestionTotal);
    }
    
    public void setQuestion(String question) {
    	this.question.setText(question);
    }
    
    public void setListeReponse(Question question) {
    	ArrayList<String> listeReponses = Main.stockage.getListeReponsesOrdreAleatoire(question.getIntituleQuestion());
    	for (int i = 0; i < listeReponses.size(); i++) {
    		new RadioButton(listeReponses.get(i)).setToggleGroup(reponses);
    	}
    }
}
