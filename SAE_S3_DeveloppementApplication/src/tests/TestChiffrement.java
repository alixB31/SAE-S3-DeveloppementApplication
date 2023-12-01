package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import modele.Chiffrement.*;
class TestChiffrement {
		
	@Test
	void testCalculClePublique() {
		assertEquals(modele.Chiffrement.calculClePartage(19,4,5),1);
		assertEquals(modele.Chiffrement.calculClePartage(125,47,35),20);

	}
	
	@Test
	void testCalculClePartage() {
		assertEquals(modele.Chiffrement.calculClePartage(19,4,5),1);
		assertNotEquals(modele.Chiffrement.calculClePartage(89,91,33),83);

	}
	
	@Test
	void testCalculClePrive() {
	}
	
	@Test
	void testCreationCleVigenere() {
		modele.Chiffrement.CreationCleVigenere(871511);
	}
	
}
