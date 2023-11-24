package controleur;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
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
    private VBox reponsesBox;
    
    private Quiz quiz;
    
    private int indiceQuestionCourrante;
    
    @FXML
    private ToggleGroup reponsesToggle = new ToggleGroup();
    
    @FXML
    void btnValiderAction(ActionEvent event) {
    	// Renvoie sur une question s'il en reste, sinon envoie
    	// sur la page des résultats.
    	if (reponsesToggle.getSelectedToggle()!=null) {
    		RadioButton reponse = (RadioButton)reponsesToggle.getSelectedToggle();
    		System.out.println(reponse.getText());
    		if(quiz.estJuste(reponse.getText(), indiceQuestionCourrante)) {
				quiz.ajouterResultat(indiceQuestionCourrante, true);
				quiz.incrementerScore();
			} else {
				quiz.ajouterResultat(indiceQuestionCourrante, false);
			}
    		if (indiceQuestionCourrante+1 < quiz.getNombreQuestions()) {
    			indiceQuestionCourrante++;
        		Main.repondreQuestion(quiz, indiceQuestionCourrante);
        	} else {
        		Main.ihmScoreQuiz(quiz, 0);
        	}
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
        reponsesBox.getChildren().clear();
        ArrayList<String> listeReponses = Main.stockage.getListeReponsesOrdreAleatoire(question.getIntituleQuestion() + question.getCategorieDeQuestion().getIntituleCategorie());
        boolean premierElement = true;

        for (int i = 0; i < listeReponses.size(); i++) {
            RadioButton button = new RadioButton(listeReponses.get(i));
            button.setToggleGroup(reponsesToggle);

            if (premierElement) {
                button.setSelected(true);
            }

            // Appliquer la police d'écriture
            button.setStyle("-fx-font-family: 'Segoe UI'; -fx-font-size: 18;");

            // Appliquer la couleur du texte
            button.setTextFill(javafx.scene.paint.Color.valueOf("#D8E3E7"));

            reponsesBox.getChildren().add(button);
            premierElement = false;
        }

        reponsesBox.setSpacing(20);
    }

}
