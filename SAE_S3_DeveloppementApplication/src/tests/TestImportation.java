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

        // Chemin du fichier CSV fictif pour les tests.
        String testFilePath = "D:/BUT2/SAE-S3-DeveloppementApplication/questionsbasiques.xls";

        // Appeler la méthode que vous souhaitez tester.
        boolean importSuccess = stockage.importCSV(testFilePath);

        // Vérifier les résultats.
        assertTrue(importSuccess);
        
        // Obtenir la liste des questions depuis la ListeQuestion.
        Map<String, Question> questionsMap = stockage.getListeQuestion();

        // Vérifier que la map des questions n'est pas vide.
        assertFalse(questionsMap.isEmpty());

        // Afficher les valeurs stockées.
        for (Map.Entry<String, Question> entry : questionsMap.entrySet()) {
            System.out.println("Clé : " + entry.getKey() + ", Valeur : " + entry.getValue());
        }
    }
}

