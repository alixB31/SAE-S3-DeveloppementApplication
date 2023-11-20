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
    
    private Quiz quiz;
    
    public void setQuizScore(Quiz quiz) {
    	this.quiz = quiz;
    }
    
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
    
    public void setListeQuestion() {
    	vBoxFeedback.getChildren().clear();
    	ArrayList<Question> listeQuestions = quiz.getCinqQuestions(numeroDePage);
    	System.out.println("Num page = " + numeroDePage);
    	for (int i = 0; i <listeQuestions.size(); i++) {
    		System.out.println("Initialisation question n°" + ((numeroDePage*5)+1+i));
    		HBox hbox = new HBox(30);
    		Text textQuestion = new Text("Question n°" + ((numeroDePage*5)+1+i));

    		// Mise en place du bouton vert ou rouge
    		String cheminImageVerteOuRouge = "./images/";
    		if (quiz.getResultatDeQuestion(i+(numeroDePage*5))) {
    			cheminImageVerteOuRouge += "valider.png";
    		} else {
    			cheminImageVerteOuRouge += "croix.png";
    		}
    		
            Image justeOuNon = new Image(cheminImageVerteOuRouge);
            ImageView affichageJusteOuNon = new ImageView(justeOuNon);
            affichageJusteOuNon.setFitWidth(35);
            affichageJusteOuNon.setFitHeight(35);
            
            // Mise en place du bouton vers le feedback
            Button feedBackButton = new Button();
            Image versFeedBack = new Image("./images/ampoule.png");
            ImageView affichageVersFeedBack = new ImageView(versFeedBack);
            feedBackButton.setGraphic(affichageVersFeedBack);
            
            // Mise en place visuelle.
            affichageVersFeedBack.setFitWidth(35);
            affichageVersFeedBack.setFitHeight(35);
            feedBackButton.setStyle("-fx-border-color: transparent; -fx-background-color: transparent;");
            
            // Action lors du click sur le bouton
            int numeroQuestion = i;
            feedBackButton.setOnAction(event -> feedBackQuestionAction(numeroQuestion, quiz.getListeQuestion().get(numeroQuestion).getFeedBackQuestion()));
            
    		hbox.getChildren().add(textQuestion);
    		hbox.getChildren().add(affichageJusteOuNon);
    		hbox.getChildren().add(feedBackButton);
    		vBoxFeedback.getChildren().add(hbox);
    	}
    }
    
    @FXML
    void feedBackQuestionAction(int numeroQuestion, String feedback) {
    	
    	if (feedback == null || feedback.isBlank() || feedback == "") {
    		feedback = "Il n'y a pas d'explication pour cette question.";
    	}
    	
    	Alert boitefeedBack = new Alert(Alert.AlertType.INFORMATION,
    			feedback, ButtonType.OK);
    	boitefeedBack.setTitle("Explication de la Question n°" + (numeroDePage*5+numeroQuestion+1)); 
    	boitefeedBack.setHeaderText(quiz.getListeQuestion().get(numeroDePage*5+numeroQuestion).getIntituleQuestion());
    	boitefeedBack.showAndWait();
    }
    
    @FXML
    void swipeGaucheQuestionAction(){
    	System.out.println("Gauche" + numeroDePage);
    	if (numeroDePage > 0) {
    		System.out.println("Gauche execute");
    		setNumeroDePage(numeroDePage-1);
        	setListeQuestion();
    	}
    }
    
    @FXML
    void swipeDroitQuestionAction() {
    	System.out.println("Droit" + numeroDePage);
    	if (numeroDePage*5 + 5 < quiz.getNombreQuestions()) {
    		System.out.println("Droit execute");
    		setNumeroDePage(numeroDePage+1);
        	setListeQuestion();
    	}
    }
}
