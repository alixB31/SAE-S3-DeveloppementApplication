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
	 * Retourne la liste des questions.
	 * @return listeQuestion la liste des questions.
	 */
	public HashMap getListeQuestion() {
		return listeQuestion.getListeQuestion();
	}
	
	/**
	 * Ajoute la catégorie en paramètre dans la liste des catégories.
	 * @param categorie la catégorie à ajouter.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
	    return listeCategorie.ajouterElementListeCategorie(categorie);
	}
	/**
	 * Supprime une catégorie de la liste des catégories.
	 * @param categorie la catégorie à supprimer.
	 * @return true si la catégorie est supprimée, false sinon.
	 */
	public boolean supprimerElementListeCategorie(Categorie categorie) {
		return listeCategorie.supprimerElementListeCategorie(categorie.getIntituleCategorie());
	}

	
	/**
	 * Modifie la catégorie en paramètre en remplacent l'ancien intitule par le nouveau
	 * @param ancienneCategorie la catégorie à modifier
	 * @param nouveauIntitule le nouvel intitulé de la catégorie
	 * @return true si la modification a réussi, false sinon.
	 */
	public boolean modifierElementListeCategorie(Categorie ancienneCategorie, String nouveauIntitule) {
		return listeCategorie.modifierElementListeCategorie(ancienneCategorie, nouveauIntitule);
	}
	
	/**
	 * Ajoute la question  en paramètre dans la liste des questions
	 * @param question la question à modifier.
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
	
	public boolean modifierIntituleQuestion(Question question, String intitule) {
		return listeQuestion.modifierIntituleQuestion(question, intitule);
	}
	
	public boolean modifierCategorieQuestion(Question question, Categorie categorie) {
		return listeQuestion.modifierCategorieDeQuestion(question, categorie);
	}
	
	public boolean modifierDifficulteQuestion(Question question, int difficulte) {
		return listeQuestion.modifierDifficulteQuestion(question, difficulte);
	}
	
	
	public boolean modifierFeedBackQuestion(Question question, String feedBack) {
		return listeQuestion.modifierFeedBackQuestion(question, feedBack);
	}
	
	public boolean modifierReponseJusteQuestion(Question question, String reponseJuste) {
		return listeQuestion.modifierReponseJusteQuestion(question, reponseJuste);
	}
}
