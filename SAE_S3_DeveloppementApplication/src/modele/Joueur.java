/*
 * Joueur.java									08 novembre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */

package modele;

import java.io.Serializable;

/**
 * Classe regroupant l'ensemble des methodes servant a la gestion des joueurs 
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class Joueur implements Serializable {
	

	private String pseudoJoueur;
	
	/**
	 * Constructeur d'un joueur
	 * @param intitule
	 */
	public Joueur(String pseudo) {
		this.pseudoJoueur = pseudo;
	}
	
	/**
	 * @return pseudoJoueur, le pseudo du joueur courant.
	 */
	public String getPseudoJoueur() {
	    return pseudoJoueur;
	}
	
	/**
	 * Setter du pseudo du joueur
	 * @return pseudoJoueur, le pseudo du joueur courant.
	 */
	public void setPseudoJoueur(String nouveauPseudo) {
		pseudoJoueur = nouveauPseudo;
	}
}
