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

/**
 * Tests unitaires de la classe Categorie
 * @author A.Brugier
 */
class TestCategorie {
	
	
	private Stockage stockageTest = null;
	
	private Stockage stockageTeste = null;
	
	/*Liste contenant des sommets de tests*/
    private List<Categorie> jeuxDeTest;  
    
	@BeforeEach
	void setUp() throws Exception {
		jeuxDeTest = new ArrayList<>(5);
		jeuxDeTest.add(new Categorie("Java"));
		jeuxDeTest.add(new Categorie("Math"));
		jeuxDeTest.add(new Categorie("JavaFX"));
		jeuxDeTest.add(new Categorie("Général"));
		stockageTeste = new Stockage();
	}
	
	@Test
	void testModifierCategorie() {
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		stockageTeste.ajouterCategorie(jeuxDeTest.get(0));
		stockageTeste.modifierElementListeCategorie(jeuxDeTest.get(0),"Python");
		assertEquals(jeuxDeTest.get(0).getIntituleCategorie(),"Python");
		
		mapVoulu.put("Math",jeuxDeTest.get(1));
		assertFalse(stockageTeste.modifierElementListeCategorie(jeuxDeTest.get(1),"Python"));
	}
	@Test
	void testAjouterCategorie() {
		//ajoute une categorie au stockage et compare le resultat avec une hashmap crée a la main
		stockageTeste.ajouterCategorie(jeuxDeTest.get(0));
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		stockageTeste.ajouterCategorie(jeuxDeTest.get(1));
		mapVoulu.put("Math",jeuxDeTest.get(1));
		assertEquals(stockageTeste.getListeCategorie(),mapVoulu);
		assertFalse(stockageTeste.ajouterCategorie(jeuxDeTest.get(3)));
	}
	
	
	@Test
	void testSuprimmerCategorie() {
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		stockageTeste.ajouterCategorie(jeuxDeTest.get(0));
		stockageTeste.ajouterCategorie(jeuxDeTest.get(1));
		stockageTeste.supprimerElementListeCategorie(jeuxDeTest.get(1));
		assertEquals(stockageTeste.getListeCategorie(),mapVoulu);
		
		//supression de la categorie general
		stockageTeste.ajouterCategorie(jeuxDeTest.get(3));
		assertFalse(stockageTeste.supprimerElementListeCategorie(jeuxDeTest.get(3)));
		
		//supression d'une categorie inexistante
		stockageTeste.ajouterCategorie(jeuxDeTest.get(3));
		assertFalse(stockageTeste.supprimerElementListeCategorie(jeuxDeTest.get(1)));
	}
	
}
