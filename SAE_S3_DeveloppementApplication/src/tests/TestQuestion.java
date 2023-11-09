package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Question;
import modele.Categorie;
import modele.Stockage;

class TestQuestion {
	/*Liste contenant des questions de tests*/
    private ArrayList<Question> jeuxDeTestQuestion;  
    
    /*Liste contenant des categories de tests*/
    private ArrayList<Categorie> jeuxDeTest;   
    
    private Stockage stockageTest = null;
    
    /* liste de reponse fausse */
    private ArrayList<String> listeReponsesFausses = new ArrayList();
    
    /* liste trop longue de reponse fausse */
    private ArrayList<String> listeReponsesFaussesTropLongue = new ArrayList();
    
	@BeforeEach
	void setUp() throws Exception {
		jeuxDeTest = new ArrayList<>(5);
		jeuxDeTestQuestion = new ArrayList<>(5);
		jeuxDeTest.add(new Categorie("Java"));
		jeuxDeTest.add(new Categorie("Math"));
		
		listeReponsesFausses.add("2");
		listeReponsesFausses.add("6");
		listeReponsesFausses.add("10");
		
		jeuxDeTestQuestion.add(new Question("Combien y a t'il de nombre boucle en java ?",jeuxDeTest.get(0),1,listeReponsesFausses,"3","il y a 3 boucles"));
		jeuxDeTestQuestion.add(new Question("3+2",jeuxDeTest.get(1),1,listeReponsesFausses,"5","3 + 2 = 5"));
		stockageTest = new Stockage();
	}
	
	@Test
	void testAjouterQuestion() {
		Map<String, Question> mapVoulu = new HashMap<>();
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		mapVoulu.put("Combien y a t'il de nombre boucle en java ?",jeuxDeTestQuestion.get(0));
		assertEquals(stockageTest.getListeQuestion(),mapVoulu);
		
		mapVoulu.put("3+2",jeuxDeTestQuestion.get(1));
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		assertEquals(stockageTest.getListeQuestion(),mapVoulu);
		
		
		//tester si trop de reponse Fausse
	}
	
	@Test
	void testSupprimerQuestion() {
		Map<String, Question> mapVoulu = new HashMap<>();
		stockageTest.supprimerElementListeQuestion("Combien y a t'il de nombre boucle en java ?");
		mapVoulu.remove("Combien y a t'il de nombre boucle en java ?",jeuxDeTestQuestion.get(0));
		int nbQuestion = stockageTest.getListeQuestion().size();
		assertEquals(mapVoulu.size(), nbQuestion + 1);
		
		// pas testé car JUnit ne fonctionne pas
	}

}
