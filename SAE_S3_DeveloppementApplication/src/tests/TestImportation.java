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
    void testImportCSV() {
        // Créer un objet de la classe Stockage.
        Stockage stockage = new Stockage();

        // Créer des objets réels pour les dépendances.
        ListeCategorie listeCategorie = new ListeCategorie();
        ListeQuestion listeQuestion = new ListeQuestion();

        // Chemin du fichier CSV fictif pour les tests.
        String testFilePath = "D:/BUT2/SAE-S3-DeveloppementApplication/questionsbasiques.csv";

        // Appeler la méthode que vous souhaitez tester.
        boolean importSuccess = stockage.importCSV(testFilePath);

        // Vérifier les résultats.
        assertTrue(importSuccess);
        
        for (Map.Entry mapEntry: listeQuestion.entrySet()) {
            Question question = (Question) mapEntry.getValue();
            if(question.getCategorieDeQuestion().equals(categorie)) {
                listeQuestion.add(question);
            }
        }
        return listeQuestionParCategorie;
    }
}

