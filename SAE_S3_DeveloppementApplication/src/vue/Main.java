/*
 * Main.java                                      25 octobre 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package vue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import controleur.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.*;



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
    private static Scene sceneImportationExportation;
    private static FXMLLoader chargeurFXMLCategorie = new FXMLLoader();
    private static FXMLLoader chargeurFXMLPartie = new FXMLLoader();
    private static FXMLLoader chargeurFXMLReponseQuestion = new FXMLLoader();
    
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
        FXMLLoader chargeurFXMLParametre = new FXMLLoader();
        chargeurFXMLParametre.setLocation(getClass().getResource("ihmParametreQuiz.fxml"));
        Parent parametre = chargeurFXMLParametre.load();
        sceneParametre = new Scene(parametre); 
        
        /* on crée la vue des paramètres des catégories*/
        chargeurFXMLCategorie.setLocation(getClass().getResource("ihmParametreCategorie.fxml"));
        Parent parametreCategorie = chargeurFXMLCategorie.load();
        sceneParametreCategorie = new Scene(parametreCategorie);
        
        
        
        chargeurFXMLReponseQuestion.setLocation(getClass().getResource("ihmRepondreQuestion.fxml"));
        Parent repondreQuestion = chargeurFXMLReponseQuestion.load();
        sceneRepondreQuestion = new Scene(repondreQuestion);
        
        FXMLLoader chargeurFXMLScoreQuiz = new FXMLLoader();
        chargeurFXMLScoreQuiz.setLocation(getClass().getResource("ihmScoreQuiz.fxml"));
        Parent ScoreQuiz = chargeurFXMLScoreQuiz.load();
        sceneScoreQuiz = new Scene(ScoreQuiz);
        
        FXMLLoader chargeurFXMLImportationExportation = new FXMLLoader();
        chargeurFXMLImportationExportation.setLocation(getClass().getResource("ihmImportationExportation.fxml"));
        Parent ImportationExportation = chargeurFXMLImportationExportation.load();
        sceneImportationExportation = new Scene(ImportationExportation);
       
        /* Création de la fenêtre principale*/
        primaryStage.setTitle("Quiz");
        primaryStage.setHeight(720);
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
    	controller.comboBoxCategorie.getItems().clear();
    	// TODO Initialiser la liste de questions de la catégorie sélectionnée.
    	ArrayList<Question> listeQuestionParCategorie = stockage.listeQuestionParCategorie((Categorie)stockage.getListeCategorie().get(categorie));
    	for (int i = 0; i<listeQuestionParCategorie.size(); i++) {
    		controller.comboBoxCategorie.getItems().add(listeQuestionParCategorie.get(i).getIntituleQuestion());
    	}
    	// Ouvre la nouvelle ihm des paramètres de la catégorie sélectionnée.
        fenetrePrincipale.setScene(sceneParametreCategorie);
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	stockage = new Stockage();
        stockage.ajouterCategorie(new Categorie("Général"));
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
        controller.setCategorie(quiz.getCategorie().getIntituleCategorie());
        
        // numero question en cours
        controller.setNumeroQuestion(indice+"");
        
        // nombre de question total avec vérif
        controller.setNombreQuestionTotal(quiz.getNombreQuestions()+"");
        
    	fenetrePrincipale.setScene(sceneRepondreQuestion);
    }

    public static void ihmScoreQuiz() {
    	fenetrePrincipale.setScene(sceneScoreQuiz);
		
    }
    
    public static void ihmImportationExportation() {
    	fenetrePrincipale.setScene(sceneImportationExportation);
    		
    }
    
}
