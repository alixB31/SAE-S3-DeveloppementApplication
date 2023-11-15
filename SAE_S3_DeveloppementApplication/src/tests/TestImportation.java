package tests;

import org.junit.jupiter.api.Test;

import modele.Categorie;
import modele.ListeCategorie;
import modele.ListeQuestion;
import modele.Question;
import modele.Stockage;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class TestImportation {

    @Test
    void testImportSucces() {
        Stockage stockage = new Stockage();
        String testFilePath = "D:\\BUT2\\SAE-S3-DeveloppementApplication\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\received.csv";
        boolean importSuccess = stockage.importCSV(testFilePath);

        assertTrue(importSuccess);
    }
    
    @Test
    void testInportNonVide() {
        Stockage stockage = new Stockage();
        String testFilePath = "D:\\BUT2\\SAE-S3-DeveloppementApplication\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\received.csv";
        boolean importSuccess = stockage.importCSV(testFilePath);

        assertTrue(importSuccess);
    	
        Map<String, Question> questionsMap = stockage.getListeQuestion();

        assertTrue(!questionsMap.isEmpty());

        for (Map.Entry<String, Question> entry : questionsMap.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
}

