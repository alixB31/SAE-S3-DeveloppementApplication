/**
 * Categorie.java									08 novembre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */


package modele;

/**
 * Classe regroupant l'ensemble des methodes servant a la gestion des joueurs 
 * @author A.Brugier
 */
public class Joueur {
	
	/* intitule de la categorie */
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
	
	public void setPseudoJoueur(String nouveauPseudo) {
		pseudoJoueur = nouveauPseudo;
	}
}
