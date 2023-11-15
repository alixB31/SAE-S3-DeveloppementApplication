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
        String testFilePath = "D:\\programmationjava\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\\\questionsbasiques.csv";

        assertTrue(stockage.importCSV(testFilePath));
    }
    
    @Test
    void testImportNonVide() {
        Stockage stockage = new Stockage();
        String testFilePath = "D:\\programmationjava\\SAE-S3-DeveloppementApplication\\SAE_S3_DeveloppementApplication\\\\questionsbasiques.csv";
        stockage.importCSV(testFilePath);
        
        Map<String, Question> questionsMap = stockage.getListeQuestion();

        assertTrue(!questionsMap.isEmpty());

    }
}

