/**
 * TestCategorie.java									26 octobre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Categorie;
import modele.Stockage;
import modele.Question;

/**
 * Tests unitaires des méthodes relatives à la classe Categorie
 * @author A.Brugier
 */
class TestCategorie {
	
	/**
	 * Objet Stockage servant pour les tests.
	 */
	private Stockage stockageTest;
	
	/*Liste contenant des categories de tests*/
    private ArrayList<Categorie> jeuxDeTest;  
    
    /**
     * Liste contenant les questions des catégories à tester.
     */
    private ArrayList<Question> questions = new ArrayList<>();
    
    /**
     * Liste par défaut des réponses fausses des questions.
     */
    private ArrayList<String> reponsesFausses = new ArrayList<>();
    
    /**
     * S'effectue avant toutes les méthodes de test. Initialise les jeux de
     * test.
     * @throws Exception
     */
	@BeforeEach
	void setUp() throws Exception {
		jeuxDeTest = new ArrayList<>(5);
		jeuxDeTest.add(new Categorie("Java"));
		jeuxDeTest.add(new Categorie("Math"));
		jeuxDeTest.add(new Categorie("JavaFX"));
		jeuxDeTest.add(new Categorie("Général"));
		stockageTest = new Stockage(); 
		
		reponsesFausses.add("faux");
		questions.add(new Question("Q1", jeuxDeTest.get(0),1,reponsesFausses,"vrai",""));
	}
	
	/**
	 * Test la méthode modifierCategorie() de Stockage.
	 */
	@Test
	void testModifierCategorie() {
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		stockageTest.ajouterCategorie(jeuxDeTest.get(0));
		stockageTest.ajouterQuestion(questions.get(0));
		stockageTest.modifierElementListeCategorie(jeuxDeTest.get(0),"Python");
		assertFalse(stockageTest.getListeCategorie().containsKey("Java"));
		assertTrue(stockageTest.getListeCategorie().containsKey("Python"));
		mapVoulu.put("Math",jeuxDeTest.get(1));
		assertFalse(stockageTest.modifierElementListeCategorie(jeuxDeTest.get(1),"Python"));
		assertEquals(questions.get(0).getCategorieDeQuestion(), stockageTest.getListeCategorie().get("Python"));
	}
	
	/**
	 * Test la méthode AJouterCategorie() de Stockage.
	 */
	@Test
	void testAjouterCategorie() {
		Map<String, Categorie> mapVoulu = new HashMap<>();
		
		//ajoute une categorie au stockage et compare le resultat avec une hashmap crée a la main
		assertTrue(stockageTest.ajouterCategorie(jeuxDeTest.get(0)));
		mapVoulu.put("Java",jeuxDeTest.get(0));
		assertTrue(stockageTest.ajouterCategorie(jeuxDeTest.get(1)));
		mapVoulu.put("Math",jeuxDeTest.get(1));
		
		assertEquals(stockageTest.getListeCategorie(),mapVoulu);
		assertFalse(stockageTest.ajouterCategorie(jeuxDeTest.get(1)));
		
		assertFalse(stockageTest.ajouterCategorie(new Categorie("Toutes les catéories")));
		assertFalse(stockageTest.ajouterCategorie(new Categorie("Général")));
	}
	
	/**
	 * Test la méthode supprimerCategiorie de stockage();
	 */
	@Test
	void testSuprimmerCategorie() {
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		stockageTest.ajouterCategorie(jeuxDeTest.get(0));
		stockageTest.ajouterCategorie(jeuxDeTest.get(1));
		stockageTest.supprimerElementListeCategorie(jeuxDeTest.get(1));
		assertEquals(stockageTest.getListeCategorie(),mapVoulu);
		
		//supression de la categorie general
		stockageTest.ajouterCategorie(jeuxDeTest.get(3));
		assertFalse(stockageTest.supprimerElementListeCategorie(jeuxDeTest.get(3)));
		
		//supression d'une categorie inexistante
		stockageTest.ajouterCategorie(jeuxDeTest.get(3));
		assertFalse(stockageTest.supprimerElementListeCategorie(jeuxDeTest.get(1)));
	}
	
}
