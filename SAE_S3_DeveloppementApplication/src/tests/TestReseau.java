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

/**
 * Classe de tests pour les fonctionnalités d'importation, d'exportation et de communication.
 */
class TestReseau {

    /**
     * Teste l'importation réussie d'un fichier CSV.
     */
    @Test
    void testImportSucces() {
        Stockage stockage = new Stockage();
        String testFilePath = "D:\\BUT2\\SAE-S3-DeveloppementApplication\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\questionsbasiques.csv";

        assertTrue(stockage.importCSV(testFilePath));
    }

    /**
     * Teste si l'importation crée une liste de questions non vide.
     */
    @Test
    void testImportNonVide() {
        Stockage stockage = new Stockage();
        String testFilePath = "D:\\BUT2\\SAE-S3-DeveloppementApplication\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\questionsbasiques.csv";
        stockage.importCSV(testFilePath);

        Map<String, Question> questionsMap = stockage.getListeQuestion();

        assertTrue(!questionsMap.isEmpty());
    }

    /**
     * Teste l'exportation réussie de questions vers un fichier CSV.
     */
    @Test
    public void testExportCSV() {
        ArrayList<String> reponsesFausses = new ArrayList<>();
        reponsesFausses.add("Faux1");
        reponsesFausses.add("Faux2");

        Categorie categorie1 = new Categorie("Cat1");
        Categorie categorie2 = new Categorie("Cat2");

        Question question1 = new Question("Question 1", categorie1, 1, reponsesFausses, "ReponseJuste1", "");
        Question question2 = new Question("Question 2", categorie2, 1, reponsesFausses, "ReponseJuste1", "Feedback1");

        ArrayList<Question> listeQuestions = new ArrayList<>();
        listeQuestions.add(question1);
        listeQuestions.add(question2);

        Stockage stockage = new Stockage();

        boolean estExporte = stockage.exportCSV(listeQuestions);

        assertTrue(estExporte);
    }

    /**
     * Teste la communication réussie entre le serveur et le client.
     */
    @Test
    public void testCommunication() {
        // Démarrer un thread pour le serveur
        Thread serverThread = new Thread(() -> {
            assertTrue(Serveur.gererConnexion());
        });
        serverThread.start();

        // Envoyer un message au serveur
        boolean estEnvoye = Client.envoie("localhost");

        // Arrêter le serveur
        Serveur.arreterConnexion();

        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertTrue(estEnvoye);
    }
}
