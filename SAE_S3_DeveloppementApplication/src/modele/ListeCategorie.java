/*
 * ListeCategorie.java					             26/10/2023
 * Un objet permettant de stoquer les catégories existantes.
 * C'est un objet HashMap.
 */
package modele;

import java.util.HashMap;

/** 
 * Liste des catégories de type HashMap. Il ne peut pas y avoir plusieurs fois
 * des catégories de même intitulé.
 * @author mateo.faussurier
 *
 */
public class ListeCategorie {
	
	// Liste contenant les catégories, la clé est l'équivalante à l'intitulé
        // d'une catégorie. Cette intitulé est unique.
	HashMap<String, Categorie> listeCategorie;
	
	/**
	 * Construit un objet ListeCategorie vide.
	 */
	public ListeCategorie() {
		listeCategorie = new HashMap<>();
	}
	
	public HashMap getListeCategorie() {
		return listeCategorie;
	}
	
	public Categorie getElementListeCategorie(String cle) {
		return listeCategorie.get(cle);
	}
	
	public boolean supprimerElementListeCategorie(String cle) {
		boolean estSupprime = false;
		if (elementEstDansListeCategorie(cle) && !cle.equals("Général")) {
			listeCategorie.remove(cle);
			estSupprime = true;
		}
		return estSupprime;
	}
	
	public boolean elementEstDansListeCategorie(String cle) {
		return listeCategorie.containsKey(cle);
	}
	
	
}
