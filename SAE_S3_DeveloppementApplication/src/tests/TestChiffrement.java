package tests;

import static org.junit.jupiter.api.Assertions.*;

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


}
