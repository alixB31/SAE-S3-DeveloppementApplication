package controleur;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vue.Main;
import modele.Question;
import modele.Quiz;

public class ScoreQuizController {
	
    @FXML
    private Button btnTerminer;

    @FXML
    private Text categorieQuestion;

    @FXML
    private Button feedBackQuestionCinq;

    @FXML
    private Button feedBackQuestionDeux;

    @FXML
    private Button feedBackQuestionQuatre;

    @FXML
    private Button feedBackQuestionTrois;

    @FXML
    private Button feedBackQuestionUn;

    @FXML
    private Text nbQuestion;

    @FXML
    private Text numeroQuestionCinq;

    @FXML
    private Text numeroQuestionDeux;

    @FXML
    private Text numeroQuestionQuatre;

    @FXML
    private Text numeroQuestionTrois;

    @FXML
    private Text numeroQuestionUn;

    @FXML
    private ImageView reponseVisuelleQuestionCinq;

    @FXML
    private ImageView reponseVisuelleQuestionDeux;

    @FXML
    private ImageView reponseVisuelleQuestionQuatre;

    @FXML
    private ImageView reponseVisuelleQuestionTrois;

    @FXML
    private ImageView reponseVisuelleQuestionUn;

    @FXML
    private Text score;

    @FXML
    private Text texteEncouragement;
    
    @FXML
    private VBox vBoxFeedback;

    
    public void setCategorie(String nomCategorie) {
    	categorieQuestion.setText(nomCategorie);
    }
    
    public void setNote(String noteObtenue, String nombreTotalDeQuestion) {
    	score.setText(noteObtenue);
    	nbQuestion.setText(nombreTotalDeQuestion);
    }
    
    //TODO le nom du joueur s'il existe
    
    @FXML
    void btnTerminerAction(ActionEvent event) {
    	// TODO repeter la page celon le nombres de questions (5 questions = 0 repetitions, 20 questions = 3 répétitions)
    	Main.ihmScoreQuiz();
    }

    @FXML
    void feedBackQuestionCinqAction(ActionEvent event) {
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
    			 "Voici le feedBack",
    			 ButtonType.OK);
    	boitefeedBack.setTitle("FeedBack Question n°"); // TODO ajouter le numero de la question
    	boitefeedBack.setHeaderText("FeedBack"); 
    	boitefeedBack.showAndWait(); 
    }

    @FXML
    void feedBackQuestionDeuxAction(ActionEvent event) {
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
   			 "Voici le feedBack",
   			 ButtonType.OK);
	   	boitefeedBack.setTitle("FeedBack Question n°"); // TODO ajouter le numero de la question
	   	boitefeedBack.setHeaderText("FeedBack"); 
	   	boitefeedBack.showAndWait(); 
    }

    @FXML
    void feedBackQuestionQuatreAction(ActionEvent event) {
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
      			 "Voici le feedBack",
      			 ButtonType.OK);
   	   	boitefeedBack.setTitle("FeedBack Question n°"); // TODO ajouter le numero de la question
   	   	boitefeedBack.setHeaderText("FeedBack"); 
   	   	boitefeedBack.showAndWait(); 
    }

    @FXML
    void feedBackQuestionTroisAction(ActionEvent event) {
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
      			 "Voici le feedBack",
      			 ButtonType.OK);
   	   	boitefeedBack.setTitle("FeedBack Question n°"); // TODO ajouter le numero de la question
   	   	boitefeedBack.setHeaderText("FeedBack"); 
   	   	boitefeedBack.showAndWait(); 
    }

    @FXML
    void feedBackQuestionUnAction(ActionEvent event) {
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
      			 "Voici le feedBack",
      			 ButtonType.OK);
   	   	boitefeedBack.setTitle("FeedBack Question n°"); // TODO ajouter le numero de la question
   	   	boitefeedBack.setHeaderText("FeedBack"); 
   	   	boitefeedBack.showAndWait(); 
    }
    
    public void setListeQuestion(Quiz quiz, int numeroDePage) {
    	ArrayList<Question> listeQuestions = quiz.getCinqQuestions(numeroDePage);
    	for (int i = 0; i <listeQuestions.size(); i++) {
    		HBox hbox = new HBox(10);
    		Text textQuestion = new Text("Question n°" + ((numeroDePage*5)+i));
    		//TODO l'image
    		//TODO le feedback
    		hbox.getChildren().add(textQuestion);
    		vBoxFeedback.getChildren().add(hbox);
    	}
    }

}
