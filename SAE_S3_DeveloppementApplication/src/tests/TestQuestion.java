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
		jeuxDeTest.add(new Categorie("Général"));
		jeuxDeTest.add(new Categorie("categoriePasDansListe"));
		
		listeReponsesFausses.add("2");
		listeReponsesFausses.add("7");
		listeReponsesFausses.add("10");
		listeReponsesFaussesTropLongue.add("1");
		listeReponsesFaussesTropLongue.add("3");
		listeReponsesFaussesTropLongue.add("2");
		listeReponsesFaussesTropLongue.add("4");
		listeReponsesFaussesTropLongue.add("5");
		
		jeuxDeTestQuestion.add(new Question("Combien y a t'il de nombre boucle en java ?",jeuxDeTest.get(0),1,listeReponsesFausses,"3","il y a 3 boucles"));
		jeuxDeTestQuestion.add(new Question("3+2",jeuxDeTest.get(1),1,listeReponsesFausses,"5","3 + 2 = 5"));
		jeuxDeTestQuestion.add(new Question("3+1",jeuxDeTest.get(1),1,listeReponsesFaussesTropLongue,"4","3 + 1 = 4"));
		jeuxDeTestQuestion.add(new Question("3+3",jeuxDeTest.get(2),1,listeReponsesFausses,"6","3 + 3 = 6"));
		stockageTest = new Stockage();
		stockageTest.ajouterCategorie(jeuxDeTest.get(0));
		stockageTest.ajouterCategorie(jeuxDeTest.get(1));
		stockageTest.ajouterCategorie(jeuxDeTest.get(2));
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
		
		//cas ou trop de reponse fausse dans la question
		//assertFalse(stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(2)));
		
		
	}
	
	@Test
	void testSupprimerQuestion() {
		Map<String, Question> mapVoulu = new HashMap<>();
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		stockageTest.supprimerElementListeQuestion("Combien y a t'il de nombre boucle en java ?");
		assertEquals(stockageTest.getListeQuestion(),mapVoulu);
		
		mapVoulu.put("3+2",jeuxDeTestQuestion.get(1));
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		stockageTest.supprimerElementListeQuestion("Combien y a t'il de nombre boucle en java ?");
		assertEquals(stockageTest.getListeQuestion(),mapVoulu);
		
		//cas ou la question n'existe pas 
		assertFalse(stockageTest.supprimerElementListeQuestion("Question inexistante"));
	}
	
	@Test
	void testModifierIntituleQuestion() {
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.modifierIntituleQuestion(jeuxDeTestQuestion.get(1),"Que fais 2 +3 ?");
		assertEquals(jeuxDeTestQuestion.get(1).getIntituleQuestion(),"Que fais 2 +3 ?");
		//cas intitule vide 
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		assertFalse(stockageTest.modifierIntituleQuestion(jeuxDeTestQuestion.get(0),""));
		
		//cas ou la question n'existe pas 
		assertFalse(stockageTest.modifierIntituleQuestion(jeuxDeTestQuestion.get(2),"AAAAA"));

	}
	
	@Test
	void testModifierCategorieQuestion() {
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		//cas ou la nouvelle categorie n'existe pas 
		assertFalse(stockageTest.modifierCategorieQuestion(jeuxDeTestQuestion.get(1),jeuxDeTest.get(3)));		
		//cas ou la nouvelle categorie existe
		stockageTest.ajouterCategorie(jeuxDeTest.get(3));
		stockageTest.modifierCategorieQuestion(jeuxDeTestQuestion.get(1),jeuxDeTest.get(3));
		assertEquals(jeuxDeTestQuestion.get(1).getCategorieDeQuestion(),jeuxDeTest.get(3));

	}

	@Test
	void testModifierDifficulteQuestion() {
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(1),3);
		assertEquals(jeuxDeTestQuestion.get(1).getDifficulteQuestion(),3);
		//cas ou difficultes pas valide
		assertFalse(stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(1),5));
		//cas ou la question n'existe pas 
		assertFalse(stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(0),2));

	}
	
	@Test
	void testModifierFeedBackQuestion() {
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(1),"le résultat est 5");
		assertEquals(jeuxDeTestQuestion.get(1).getFeedBackQuestion(),"le résultat est 5");
		//cas ou feedback vide
		assertFalse(stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(1),""));
		//cas ou la question n'existe pas 
		assertFalse(stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(3),"2"));

	}
	
	@Test
	void testModifierReponseJusteQuestion() {
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(1),"55");
		assertEquals(jeuxDeTestQuestion.get(1).getReponseJusteQuestion(),"55");
		//cas ou reponse vide 
		assertFalse(stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(1),""));
		//cas ou la question n'existe pas 
		assertFalse(stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(3),"55"));
		
	}
	
	@Test
	void testAjouterReponseFausseQuestion() {
		ArrayList arrayVoulu = new ArrayList<>();
		arrayVoulu.add("2");
		arrayVoulu.add("7");
		arrayVoulu.add("10");
		arrayVoulu.add("55");
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(1),"55");
		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
		//cas avec plus de 4 reponses fausses
		assertFalse(stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(1),"trop long"));
		//cas ou la nouvelle reponses fausses est fausses
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		assertFalse(stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(0),""));
	}
	
	@Test
	void testSupprimerReponseFausseQuestion() {
		ArrayList arrayVoulu = new ArrayList<>();
		arrayVoulu.add("7");
		arrayVoulu.add("10");
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(1),"2");
		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
		
		ArrayList arrayVoulu2 = new ArrayList<>();
		stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(0),"2");
		arrayVoulu2.add("10");
		arrayVoulu2.add("2");
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"7");
		assertEquals(jeuxDeTestQuestion.get(0).getReponsesFaussesQuestion(),arrayVoulu2);
		
		//suppresion jusqu'a qu'il n'y ai plus de reponses fausses
		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"2");
		assertFalse(stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"10"));
	}
	
	@Test
	void testModifierListeReponseFausseQuestion() {
		ArrayList arrayVoulu = new ArrayList<>();
		arrayVoulu.add("2000");
		arrayVoulu.add("7");
		arrayVoulu.add("10");
		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
		stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"2","2000");
		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
		// cas ou on modifie par la meme reponse
		assertTrue(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"7","7"));
		// cas ou on modifie par une reponse vide
		assertFalse(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"7",""));
		// cas ou on modifie une reponse inexistante
		assertFalse(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"50","25"));
		
		
	}
	
	
}
