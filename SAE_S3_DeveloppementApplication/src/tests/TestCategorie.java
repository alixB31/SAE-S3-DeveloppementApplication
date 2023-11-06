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
	
	/*Liste contenant des sommets de tests*/
    private List<Categorie> jeuxDeTest;  
    
	@BeforeEach
	void setUp() throws Exception {
		jeuxDeTest = new ArrayList<>(5);
		jeuxDeTest.add(new Categorie("Java"));
		jeuxDeTest.add(new Categorie("Math"));
		jeuxDeTest.add(new Categorie("JavaFX"));
		stockageTest = new Stockage();
	}
	
	@Test
	void testModifierCategorie() {
		jeuxDeTest.get(0).modifierCategorie("Python");
		assertEquals(jeuxDeTest.get(0).getIntituleCategorie(),"Python");
		jeuxDeTest.get(1).modifierCategorie("Histoire");
		assertEquals(jeuxDeTest.get(1).getIntituleCategorie(),"Histoire");
		jeuxDeTest.get(2).modifierCategorie("Java");
		assertNotEquals(jeuxDeTest.get(2).getIntituleCategorie(),"JavaFX");
	}
	@Test
	void testAjouterCategorie() {
		//ajoute une categorie au stockage et compare le resultat avec une hashmap crée a la main
		jeuxDeTest.get(0).ajouterCategorie(stockageTest);
		Map<String, Categorie> mapVoulu = new HashMap<>();
		mapVoulu.put("Java",jeuxDeTest.get(0));
		assertEquals(stockageTest.getListeCategorie(),mapVoulu);
		
		jeuxDeTest.get(1).ajouterCategorie(stockageTest);
		assertNotEquals(stockageTest.getListeCategorie(),mapVoulu);

	}
	
	
}
