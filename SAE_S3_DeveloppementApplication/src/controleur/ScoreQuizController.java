package controleur;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
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

    private int numeroDePage;
    
    private int numeroQuestion = 0;
    
    public void setNumeroDePage(int numeroDePage) {
    	this.numeroDePage = numeroDePage;
    }
    
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
    	Main.ihmScoreQuiz(null, 0);
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
    
    public void setListeQuestion(Quiz quiz) {
    	ArrayList<Question> listeQuestions = quiz.getCinqQuestions(numeroDePage);
    	for (int i = 0; i <listeQuestions.size(); i++) {
    		HBox hbox = new HBox(30);
    		Text textQuestion = new Text("Question n°" + ((numeroDePage*5)+1+i));
    		
    		// Mise en place du bouton vert ou rouge
    		String cheminImageVerteOuRouge = "./images/";
    		if (quiz.getResultatDeQuestion(i)) {
    			cheminImageVerteOuRouge += "valider.png";
    		} else {
    			cheminImageVerteOuRouge += "croix.png";
    		}
            Image justeOuNon = new Image(cheminImageVerteOuRouge);
            ImageView affichageJusteOuNon = new ImageView(justeOuNon);
            affichageJusteOuNon.setFitWidth(35);
            affichageJusteOuNon.setFitHeight(35);
//            imageView.setOnMouseClicked(event ->{feedBackQuestionAction();});
            
            // Mise en place du bouton vers le feedback
            Image versFeedBack = new Image("./images/ampoule.png");
            ImageView affichageVersFeedBack = new ImageView(versFeedBack);
            
            affichageVersFeedBack.setFitWidth(35);
            affichageVersFeedBack.setFitHeight(35);
    		hbox.getChildren().add(textQuestion);
    		hbox.getChildren().add(affichageJusteOuNon);
    		hbox.getChildren().add(affichageVersFeedBack);
    		vBoxFeedback.setSpacing(50);
    		vBoxFeedback.getChildren().add(hbox);
    	}
    }
    
    @FXML
    void feedBackQuestionAction() {
    	
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
    			 "Voici le feedBack",
    			 ButtonType.OK);
    	boitefeedBack.setTitle("FeedBack Question n°" + numeroDePage*5+numeroQuestion); // TODO ajouter le numero de la question
    	numeroQuestion++;
    	boitefeedBack.setHeaderText("FeedBack");
    	boitefeedBack.showAndWait(); 
    }

}
