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
		assertEquals(modele.Chiffrement.calculCleGlobale(19,4,5),1);
		assertEquals(modele.Chiffrement.calculCleGlobale(125,47,35),20);

	}
	
	@Test
	void testCalculCleGlobale() {
		assertEquals(modele.Chiffrement.calculCleGlobale(19,4,5),1);
		assertNotEquals(modele.Chiffrement.calculCleGlobale(89,91,33),83);

	}
	
	@Test
	void testCalculClePrive() {
	}
	
	@Test
	void testCreationCleVigenere() {
		modele.Chiffrement.CreationCleVigenere(871511);
	}
	
}
