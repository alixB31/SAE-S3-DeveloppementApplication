package controleur;

import java.util.ArrayList;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import vue.Main;
import modele.Question;
import modele.Quiz;
import modele.Stockage;

public class ScoreQuizController {
	
    @FXML
    private Button btnTerminer;

    @FXML
    private Text categorieQuestion;

    @FXML
    private Text nbQuestion;

    @FXML
    private Text score;

    @FXML
    private Text texteEncouragement;
    
    @FXML
    private VBox vBoxFeedback;

    private int numeroDePage;
    
    private Quiz quiz;
    
    private String pseudoJoueur;
    
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
    	Alert popUpTerminerQuiz = new Alert(AlertType.CONFIRMATION);
    	popUpTerminerQuiz.setTitle("Quitter le quiz");
    	popUpTerminerQuiz.setHeaderText("Attention: Quitter le quiz est irreversible!");
    	popUpTerminerQuiz.setContentText("Voulez vous quitter le quiz?");
		
		Optional<ButtonType> reponseContinuer = popUpTerminerQuiz.showAndWait();
		if (reponseContinuer.get() == ButtonType.OK) {
			Main.retourMenuPrincipal();
		}
    }
    
    public void setPseudo(String pseudo) {
    	pseudoJoueur= pseudo;
    }
    
    public void setPhrase() {
        double scoreRatio = (double) Integer.parseInt(score.getText()) / Integer.parseInt(nbQuestion.getText());

        if (pseudoJoueur != null || !pseudoJoueur.isBlank() || !pseudoJoueur.isEmpty()){
	        if (scoreRatio <= 0.5) {
	            texteEncouragement.setText("Bravo, " + pseudoJoueur + " tu peux faire mieux, ne lâche pas !");
	        } else if (scoreRatio > 0.5 && scoreRatio <= 0.75) {
	            texteEncouragement.setText("Bravo, " + pseudoJoueur + " pas mal, mais il y a toujours place à l'amélioration !");
	        } else {
	            texteEncouragement.setText("Bravo, " + pseudoJoueur + " excellent travail ! Continue comme ça !");
	        }
        } else {
        	if (scoreRatio <= 0.5) {
	            texteEncouragement.setText("Bravo, tu peux faire mieux, ne lâche pas !");
	        } else if (scoreRatio > 0.5 && scoreRatio <= 0.75) {
	            texteEncouragement.setText("Bravo, pas mal, mais il y a toujours place à l'amélioration !");
	        } else {
	            texteEncouragement.setText("Bravo, excellent travail ! Continue comme ça !");
	        }
        }
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
    		hbox.setAlignment(Pos.CENTER_LEFT);
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
