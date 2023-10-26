/**
 * Categorie.java									26 octobre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */


package modele;

/**
 * Classe regroupant l'ensemble des methodes servant a la gestion des categories 
 * @author F.Bernad, A.Brouzes, R.Britelle
 */
public class Categorie {

	/* intitule de la categorie */
	public String intituleCategorie;
	
	
	
	/**
	 * Constructeur d'une categorie
	 * @param intitule
	 */
	public Categorie(String intitule) {
		this.intituleCategorie = intitule;
	}
	
	/**
	 * Supprime la categorie appelante
	 * @return
	 */
	public boolean supprimerCategorie() {
		return false;
	}
	
	/**
	 * Modifie l'intitule de la categorie appelante
	 * @param intitule nouveau intitule de la categorie 
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
