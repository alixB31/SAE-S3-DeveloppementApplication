/*
 * Main.java                                      25 octobre 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package vue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import controleur.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;
import modele.Stockage.EchecSerialisationRestauration;



/**
 * Cette classe est la classe principale d'une application JavaFX.
 * Cette classe contient les chargeurs des vues.
 * @author rayan.ibrahime
 */
public class Main extends Application {
	private static Stage fenetrePrincipale;
	private static Scene sceneNotice;
	private static Scene sceneMenu;
	private static Scene scenePartie;
	private static Scene sceneParametre;
	private static Scene sceneParametreCategorie;
	private static Scene sceneRepondreQuestion;
	private static Scene sceneScoreQuiz;
	private static Scene sceneImportation;
	private static Scene sceneExportation;
	private static Scene sceneEnvoie;
	private static Scene sceneChoix;
	private static Scene sceneRecevoir;
	private static FXMLLoader chargeurFXMLParametre= new FXMLLoader();
	private static FXMLLoader chargeurFXMLCategorie = new FXMLLoader();
	private static FXMLLoader chargeurFXMLPartie = new FXMLLoader();
	private static FXMLLoader chargeurFXMLReponseQuestion = new FXMLLoader();
	private static FXMLLoader chargeurFXMLScoreQuiz = new FXMLLoader();
	private static FXMLLoader chargeurFXMLImportation = new FXMLLoader();
	private static FXMLLoader chargeurFXMLExportation = new FXMLLoader();
	private static FXMLLoader chargeurFXMLEnvoie = new FXMLLoader();
	public static boolean onViensArriver = false;
	public static Stockage stockage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		/* on crée la vue du menu */
		FXMLLoader chargeurFXML = new FXMLLoader();
		chargeurFXML.setLocation(getClass().getResource("ihmMenu.fxml"));
		Parent menu = chargeurFXML.load();
		sceneMenu = new Scene(menu);

		/* on crée la vue de la notice */
		FXMLLoader chargeurFXMLNotice = new FXMLLoader();
		chargeurFXMLNotice.setLocation(getClass().getResource("ihmNotice.fxml"));
		Parent notice = chargeurFXMLNotice.load();
		sceneNotice = new Scene(notice); 

		/* on crée la vue de la partie */
		chargeurFXMLPartie.setLocation(getClass().getResource("ihmPartie.fxml"));
		Parent partie = chargeurFXMLPartie.load();
		scenePartie = new Scene(partie); 

		/* on crée la vue des paramètres */
		chargeurFXMLParametre.setLocation(getClass().getResource("ihmParametreQuiz.fxml"));
		Parent parametre = chargeurFXMLParametre.load();
		sceneParametre = new Scene(parametre); 

		/* on crée la vue des paramètres des catégories*/
		chargeurFXMLCategorie.setLocation(getClass().getResource("ihmParametreCategorie.fxml"));
		Parent parametreCategorie = chargeurFXMLCategorie.load();
		sceneParametreCategorie = new Scene(parametreCategorie);

		/* on crée la vue du choix d'export */
		FXMLLoader chargeurFXMLChoix = new FXMLLoader();
		chargeurFXMLChoix.setLocation(getClass().getResource("ihmChoixClientServeur.fxml"));
		Parent choix = chargeurFXMLChoix.load();
		sceneChoix = new Scene(choix); 

		/* on crée la vue du receveur*/
		FXMLLoader chargeurFXMLRecevoir = new FXMLLoader();
		chargeurFXMLRecevoir.setLocation(getClass().getResource("ihmRecevoir.fxml"));
		Parent recevoir = chargeurFXMLRecevoir.load();
		sceneRecevoir = new Scene(recevoir); 


		chargeurFXMLReponseQuestion.setLocation(getClass().getResource("ihmRepondreQuestion.fxml"));
		Parent repondreQuestion = chargeurFXMLReponseQuestion.load();
		sceneRepondreQuestion = new Scene(repondreQuestion);


		chargeurFXMLScoreQuiz.setLocation(getClass().getResource("ihmScoreQuiz.fxml"));
		Parent ScoreQuiz = chargeurFXMLScoreQuiz.load();
		sceneScoreQuiz = new Scene(ScoreQuiz);


		chargeurFXMLImportation.setLocation(getClass().getResource("ihmImportation.fxml"));
		Parent Importation = chargeurFXMLImportation.load();
		sceneImportation = new Scene(Importation);

		chargeurFXMLExportation.setLocation(getClass().getResource("ihmExportation.fxml"));
		Parent Exportation = chargeurFXMLExportation.load();
		sceneExportation = new Scene(Exportation);

		/* on crée la vue de l'envoie */

		chargeurFXMLEnvoie.setLocation(getClass().getResource("ihmEnvoie.fxml"));
		Parent envoie = chargeurFXMLEnvoie.load();
		sceneEnvoie = new Scene(envoie); 

		/* Création de la fenêtre principale*/
		primaryStage.setTitle("Quiz");
		primaryStage.setHeight(740);
		primaryStage.setWidth(1080);
		primaryStage.setScene(sceneMenu);
		fenetrePrincipale = primaryStage;
		primaryStage.show();
	}

	/**
	 * Ferme l'application
	 */
	public static void quitterJeu() {
		Platform.exit();
	}

	/**
	 * Afficher les paramètres
	 */
	public static void lancerParametre() {
		// Crée un objet contrôleur du nouveau contrôleur.
		ParametreController controller = chargeurFXMLParametre.getController();

		// TODO Initialiser la liste de questions de la catégorie sélectionnée.
		HashMap<String, Categorie> listeCategorie = stockage.getListeCategorie();

		controller.comboBox.getItems().clear();
		for (Categorie categorie : listeCategorie.values()) {
			controller.comboBox.getItems().add(categorie.getIntituleCategorie());
		}
		fenetrePrincipale.setScene(sceneParametre);
	}

	/**
	 * Lancer une partie        
	 */
	public static void lancerPartie() {
		// Création du contrôleur
		PartieController controller = chargeurFXMLPartie.getController();
		// On vide la liste déroulante des catégories
		controller.getComboBoxCategorie().getItems().clear();
		// On ajoute la liste des catégories existantes dans la liste déroulante.
		controller.setListeCategorie(stockage.getListeCategorie());
		// Lancement de la fenètre.
		fenetrePrincipale.setScene(scenePartie);
	}

	/** 
	 * Renvoie à la page de la vue ihmMenu.fxml
	 */
	public static void retourMenuPrincipal() {
		fenetrePrincipale.setScene(sceneMenu);
	}

	/** 
	 * Affichage de la notice avec la vue ihmNotice.fxml
	 */
	public static void voirNotice() {
		fenetrePrincipale.setScene(sceneNotice);
	}

	/**
	 * Afficher les paramètres d'une catégorie
	 * @throws IOException 
	 *
	 */
	public static void voirParamCategorie(String categorie) throws IOException {

		// Crée un objet contrôleur du nouveau contrôleur.
		ParametreCategorieController controller = chargeurFXMLCategorie.getController();

		// Ajoute le nom de la catégorie dans la variable.
		controller.setNomCategorie(categorie);

		// Initialise le nom de la catégorie dans le label correspondant dans
		// la nouvelle ihm.
		controller.nomCategorie.setText(categorie);
		controller.comboBoxQuestion.getItems().clear();
		// Initialiser la liste de questions de la catégorie sélectionnée.
		controller.setComboBoxQuestion();
		// Ouvre la nouvelle ihm des paramètres de la catégorie sélectionnée.
		fenetrePrincipale.setScene(sceneParametreCategorie);
	}


	/**
	 * @param args the command line arguments
	 * @throws EchecSerialisationRestauration 
	 */
	public static void main(String[] args) throws EchecSerialisationRestauration {
		stockage = new Stockage();
		stockage.restaurer();
		stockage.ajouterCategorie(new Categorie("Général"));
		System.out.println(stockage.getListeCategorie());
		launch(args);
	}

	/**
	 * Lancer une partie        
	 */
	public static void repondreQuestion(Quiz quiz , int indice) {
		RepondreQuestionControleur controller = chargeurFXMLReponseQuestion.getController();
		// Initialisation du quiz et la position de la question à présenter
		controller.setQuiz(quiz);
		controller.setIndiceQuestion(indice);

		// Initialisation des données à écrire sur la page

		// Niveau de difficulté
		String niveauDifficulte = "indifferent";
		if(quiz.getDifficulte() != 0) {
			niveauDifficulte = quiz.getDifficulte() + "";
		}
		controller.setDifficulte(niveauDifficulte);

		// Categorie choisie
		if (quiz.getCategorie() == null) {
			controller.setCategorie("Toutes les catégories");
		} else {
			controller.setCategorie(quiz.getCategorie().getIntituleCategorie());
		}

		// numero question en cours
		controller.setNumeroQuestion(indice+1+"");

		// nombre de question total avec vérif
		controller.setNombreQuestionTotal(quiz.getNombreQuestions()+"");

		// La questioj à afficher
		controller.setQuestion(quiz.getListeQuestion().get(indice).getIntituleQuestion());

		// Liste des réponses
		controller.setListeReponse(quiz.getListeQuestion().get(indice));

		fenetrePrincipale.setScene(sceneRepondreQuestion);
	}

	public static void ihmScoreQuiz(Quiz quiz, int indicePageResultat) {
		ScoreQuizController controller = chargeurFXMLScoreQuiz.getController();
		String nomCategorie;
		if (quiz.getCategorie() == null) {
			nomCategorie = "Toutes les catégories";
		} else {
			nomCategorie = quiz.getCategorie().getIntituleCategorie();
		}
		controller.setQuizScore(quiz);
		controller.setCategorie(nomCategorie);
		controller.setNote(quiz.getScoreFinal()+"", quiz.getNombreQuestions()+"");
		controller.setNumeroDePage(indicePageResultat);
		controller.setListeQuestion();
		fenetrePrincipale.setScene(sceneScoreQuiz);
	}

	public static void ihmImportation() {
		fenetrePrincipale.setScene(sceneImportation);		
	}

	public static void ihmExportation() {
		onViensArriver = true;
		ExporterController controller = chargeurFXMLExportation.getController();
		controller.getComboBoxCategorie().getItems().clear();
		controller.questionCheck.clear();
		controller.getCheckListeQuestion().getChildren().clear();
		controller.setComboBoxCategorie(stockage.getListeCategorie());
		controller.setCheckBoxQuestion(stockage.getListeQuestion());
		fenetrePrincipale.setScene(sceneExportation);		
	}

	public static void ihmEnvoie() {
		fenetrePrincipale.setScene(sceneEnvoie);
	}

	public static void ihmChoix() {
		fenetrePrincipale.setScene(sceneChoix);
	}

	public static void ihmRecevoir() {
		fenetrePrincipale.setScene(sceneRecevoir);
	}
}
