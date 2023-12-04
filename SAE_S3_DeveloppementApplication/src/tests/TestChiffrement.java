package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import modele.Chiffrement;

class TestChiffrement {
		
	
	@Test
	void testCodeVigenere() {
		// Vérifie si le chiffrement Vigenère fonctionne correctement
		assertEquals(Chiffrement.codeVigenere("Voici un test", "A5"), "hxxmxn1vr%t#0");
		assertNotEquals(Chiffrement.codeVigenere("Voici un test", "A5"), "hxxmxn1vr%t#1");
	}

	@Test
	void testDecodeVigenere() {
		// Vérifie si le déchiffrement Vigenère fonctionne correctement
		assertEquals(Chiffrement.decodeVigenere("hxxmxn1vr%t#0", "A5"), "Voici un test");
		assertNotEquals(Chiffrement.decodeVigenere("Voici un test", "A5"), "hxxmxn1vr%t#1");
	}

	@Test
	void testClePublique() {
		// Vérifie si le calcul de la clé publique fonctionne correctement
		assertEquals(Chiffrement.clePublique(19, 4, 5), 1);
		assertEquals(Chiffrement.clePublique(125, 47, 35), 20);
		assertNotEquals(Chiffrement.clePublique(47, 125, 35), 20);
	}

	@Test
	void testCleGlobale() {
		// Vérifie si le calcul de la clé globale fonctionne correctement
		assertEquals(Chiffrement.calculCleGlobale(19, 4, 5), 1);
		assertEquals(Chiffrement.clePublique(125, 47, 35), 20);
		assertEquals(Chiffrement.clePublique(1248, 3, 270), 162);
		assertNotEquals(Chiffrement.calculCleGlobale(89, 91, 33), 83);
	}

	@Test
	void testCalculClePrive() {
		// Crée une liste de nombres pour la vérification du nombre généré aléatoirement
		List<Integer> listeDeNombres = new ArrayList<>();
		// Ajoute des nombres à la liste
		listeDeNombres.add(1);
		listeDeNombres.add(2);
		listeDeNombres.add(3);
		listeDeNombres.add(4);
		listeDeNombres.add(5);
		// Vérifie que le nombre généré aléatoirement est dans l'intervalle choisi
		assertTrue(listeDeNombres.contains(Chiffrement.clePrive(6)));
		listeDeNombres.clear();
		listeDeNombres.add(6);
		// Vérifie que le nombre généré aléatoirement n'est pas dans la liste
		assertFalse(listeDeNombres.contains(Chiffrement.clePrive(6)));
	}

	@Test
	void testCreationCleVigenere() {
		// Vérifie si la création de la clé Vigenère fonctionne correctement
		assertEquals(Chiffrement.CreationCleVigenere(41), ")1");
		assertNotEquals(Chiffrement.CreationCleVigenere(92), "))5");
	}
}
	

