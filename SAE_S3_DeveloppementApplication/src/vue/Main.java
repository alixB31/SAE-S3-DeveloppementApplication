/*
 * Main.java                                      25 octobre 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package vue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



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
    private static Scene sceneParametreQuestion;
    
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
        FXMLLoader chargeurFXMLPartie = new FXMLLoader();
        chargeurFXMLPartie.setLocation(getClass().getResource("ihmPartie.fxml"));
        Parent partie = chargeurFXMLPartie.load();
        scenePartie = new Scene(partie); 
        
        /* on crée la vue des paramètres */
        FXMLLoader chargeurFXMLParametre = new FXMLLoader();
        chargeurFXMLParametre.setLocation(getClass().getResource("ihmParametreQuiz.fxml"));
        Parent parametre = chargeurFXMLParametre.load();
        sceneParametre = new Scene(parametre); 
        
        /* on crée la vue des paramètres des catégories*/
        FXMLLoader chargeurFXMLCategorie = new FXMLLoader();
        chargeurFXMLCategorie.setLocation(getClass().getResource("ihmParametreCategorie.fxml"));
        Parent parametreCategorie = chargeurFXMLCategorie.load();
        sceneParametreCategorie = new Scene(parametreCategorie); 
        
        /* on crée la vue des paramètres des questions*/
        FXMLLoader chargeurFXMLQuestion = new FXMLLoader();
        chargeurFXMLQuestion.setLocation(getClass().getResource("ihmParametreQuestion.fxml"));
        Parent parametreQuestion = chargeurFXMLQuestion.load();
        sceneParametreQuestion = new Scene(parametreQuestion); 
       

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
     *
     */
    public static void voirParamCategorie() {
        fenetrePrincipale.setScene(sceneParametreCategorie);
    }
    
    /**
     * Affiche les paramètres d'une question
     *
     */
    public static void voirParamQuestion() {
        fenetrePrincipale.setScene(sceneParametreQuestion);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
