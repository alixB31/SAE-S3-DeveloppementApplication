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
	
	/**
	 * Ajoute la catégorie en paramètre dans la liste des catégories.
	 * @param categorie, la catégorie à ajouter.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
	    return listeCategorie.ajouterElementListeCategorie(categorie);
	}
	/**
	 * Supprime une catégorie de la liste des catégories.
	 * @param categorie, la catégorie à supprimer.
	 * @return true si la catégorie est supprimée, false sinon.
	 */
	public boolean supprimerElementListeCategorie(Categorie categorie) {
		return listeCategorie.supprimerElementListeCategorie(categorie.getIntituleCategorie());
	}

	/**
	 * Vérifie si une catégorie est présente dans la liste.
	 * @param categorie, dont on vérifie l'existance dans la liste.
	 * @return true si la catégorie existe déjà dans la liste, false sinon.
	 */
	public boolean elementEstDansListeCategorie(Categorie categorie) {
		return listeCategorie.elementEstDansListeCategorie(categorie.getIntituleCategorie());
	}
	
	/**
	 * Modifie la catégorie en paramètre en remplacent l'ancien intitule par le nouveau
	 * @param ancienneCategorie
	 * @param nouveauIntitule
	 * @return true si la modification a réussi, false sinon.
	 */
	public boolean modifierElementListeCategorie(Categorie ancienneCategorie, String nouveauIntitule) {
		return listeCategorie.modifierElementListeCategorie(ancienneCategorie, nouveauIntitule);
	}
	
	/**
	 * Ajoute la question  en paramètre dans la liste des questions
	 * @param question, la question à modifier.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	public boolean ajouterQuestion(Question question) {
		return listeQuestion.ajouterElementListeQuestion(question);
	}
	
	/**
	 * Retourne la liste des questions ayant la même catégorie que celle en
	 * paramètre.
	 * @param categorie la catégorie qui filtre les questions.
	 * @return ListeQuestion, la liste des questions correspondantes.
	 */
	public ListeQuestion listeQuestionParCategorie(Categorie categorie) {
	    return listeQuestion.listeQuestionParCategorie(categorie);
	}
	
	// TODO modifier question
}
