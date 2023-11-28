package tests;

import org.junit.jupiter.api.Test;

import modele.Categorie;
import modele.Client;
import modele.ListeCategorie;
import modele.ListeQuestion;
import modele.Question;
import modele.Serveur;
import modele.Stockage;
import vue.Main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class TestImportation {

//    @Test
//    void testImportSucces() {
//        Stockage stockage = new Stockage();
//        String testFilePath = "D:\\programmationjava\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\\\questionsbasiques.csv";
//
//        assertTrue(stockage.importCSV(testFilePath));
//    }
//    
//    @Test
//    void testImportNonVide() {
//        Stockage stockage = new Stockage();
//        String testFilePath = "D:\\programmationjava\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\\\questionsbasiques.csv";
//        stockage.importCSV(testFilePath);
//        
//        Map<String, Question> questionsMap = stockage.getListeQuestion();
//
//        assertTrue(!questionsMap.isEmpty());
//
//    }
//    
//    @Test
//    public void testExportCSV() {
//    	ArrayList<String> reponsesFausses = new ArrayList<>();
//    	reponsesFausses.add("Faux1");
//    	reponsesFausses.add("Faux2");
//
//        
//        Categorie categorie1 = new Categorie("Cat1");
//        Categorie categorie2 = new Categorie("Cat2");
//    	
//        // Créer des objets Question pour tester
//        Question question1 = new Question("Question 1", categorie1, 1, reponsesFausses, "ReponseJuste1", "");
//        Question question2 = new Question("Question 2", categorie2, 1, reponsesFausses, "ReponseJuste1", "Feedback1");
//
//        // Ajouter les questions à une liste
//        ArrayList<Question> listeQuestions = new ArrayList<>();
//        listeQuestions.add(question1);
//        listeQuestions.add(question2);
//
//        // Créer une instance de votre classe
//        Stockage stockage = new Stockage();
//
//        // Appeler la méthode exportCSV avec la liste de questions
//        boolean estExporte = stockage.exportCSV(listeQuestions);
//
//        // Vérifier que l'exportation a réussi
//        assertTrue(estExporte);
//    }
    
	@Test
    public void testCommunication() {
        Thread serverThread = new Thread(() -> {
            assertTrue(Serveur.gererConnexion());
        });
        serverThread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean estEnvoye = Client.envoie("localhost");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        Serveur.arreterConnexion();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(estEnvoye);
    }
}

