package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import modele.Chiffrement.*;
class TestChiffrement {

	@Test
	void testPremierEntreEux() {
		assertFalse(modele.Chiffrement.sontPremiersEntreEux(20,5));
		assertFalse(modele.Chiffrement.sontPremiersEntreEux(35,14));
		assertTrue(modele.Chiffrement.sontPremiersEntreEux(701,25));
		assertTrue(modele.Chiffrement.sontPremiersEntreEux(33,7));
	}
	
	@Test
	void testPgcd() {
		assertEquals(modele.Chiffrement.pgcd(20,5),5);
		assertEquals(modele.Chiffrement.pgcd(35,14),7);
		assertEquals(modele.Chiffrement.pgcd(11,5),1);
		assertEquals(modele.Chiffrement.pgcd(39,5),1);
	}
		
	@Test
	void testCalculClePublique() {
		assertEquals(modele.Chiffrement.clePublique(89,9,33),2);
		assertNotEquals(modele.Chiffrement.clePublique(89,91,33),23);
		assertEquals(modele.Chiffrement.clePublique(3,58621,71),66);
		assertEquals(modele.Chiffrement.clePublique(981,4848,501),312);
	}
	
	@Test
	void testCalculClePartage() {
		assertEquals(modele.Chiffrement.calculClePartage(89,9,33),2);
		assertNotEquals(modele.Chiffrement.calculClePartage(89,91,33),83);
		assertEquals(modele.Chiffrement.calculClePartage(750,98,71),5);
		assertEquals(modele.Chiffrement.calculClePartage(4843,981,501),334);
	}
	
	@Test
	void testCalculClePrive() {
		// Créez une liste des nombres obtenables comme cle privé avec les parametre(79,3)
        List<Integer> listeDeNombres = Arrays.asList(5, 7, 11, 17, 19, 23, 25, 29, 31, 35, 37, 41, 43, 47, 49, 53, 55, 59, 61, 67, 71, 73, 77, 79, 83, 85, 89, 95, 97, 101, 103, 107, 109, 113, 115, 119, 121, 125, 127, 131, 133, 137, 139, 145, 149, 151, 155);

        // Verifie que la cle obtenu est dans la liste
        assertTrue(listeDeNombres.contains(modele.Chiffrement.clePrive(79, 3)));

	}
	
	@Test
	void testCreationCleVigenere() {
		modele.Chiffrement.CreationCleVigenere(871511);

	}
	
}
