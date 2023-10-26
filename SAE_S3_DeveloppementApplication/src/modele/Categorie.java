/*
 * 
 */

package modele;

/**
 * 
 */
public class Categorie {

	public String intituleCategorie;
	
	/**
	 * @param intitule
	 */
	public Categorie(String intitule) {
		this.intituleCategorie = intitule;
	}
	
	/**
	 * @return
	 */
	public boolean supprimerCategorie() {
		return false;
	}
	
	/**
	 * @param intitule
	 * @return
	 */
	public boolean modifierCategorie(String intitule) {
		return false;
	}
	
	/**
	 * @param intitule
	 * @return
	 */
	public boolean ajouterCategorie(String intitule) {
		return false;
	}
}
