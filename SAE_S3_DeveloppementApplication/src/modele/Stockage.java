package modele;

import java.util.HashMap;

/** TODO comment class responsibility (SRP)
 * @author mateo.faussurier
 *
 */
public class Stockage {
	
	// objet ListeCategorie contenant les catégories du serveur
	ListeCategorie listeCategorie;
	
	// objet ListeQuestion contenant les questions du serveur
	ListeQuestion listeQuestion;
	
	// objet ListeJouteur contenant les joueurs enregistrés sur le serveur
	ListeJoueur listeJoueur;
	
	/**
	 * Constructeur vide;
	 */
	public Stockage() {
		listeCategorie = new ListeCategorie();
		listeQuestion = new ListeQuestion();
		listeJoueur = new ListeJoueur();
	}
	
	/** 
	 * Retourne la liste des catégories contenant les catégories.
	 * @return listeCategorie la liste des catégories, c'est un objet de
	 * type HashMap
	 */
	public HashMap getListeCategorie() {
		return listeCategorie.getListeCategorie();
	}
	
	/** TODO comment method role
	 * @param categorie
	 * @return
	 */
	public boolean ajouterCategorie(Categorie categorie) {
	    return listeCategorie.ajouterCategorie(categorie);
	}

	public boolean ajouterQuestion(Question question) {
		return listeQuestion.ajouterQuestion(question);
		
	}
}
