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
	 * Modifie l'intitule de la categorie appelante
	 * @param intitule nouveau intitule de la categorie 
	 * @return true une fois l'operation faite
	 */
	public boolean modifierCategorie(String intitule) {
		//  TODO vérifier que la categorie existe dans le Stockage
		boolean estModifier = false;
		this.intituleCategorie = intitule;
		if (this.intituleCategorie == intitule) {
			estModifier = true;
		} 
		return estModifier;
	}	
	
	/**
	 * @return intituleCategorie, l'intitule de la catégorie courrante.
	 */
	public String getIntituleCategorie() {
	    return intituleCategorie;
	}
	
	/** TODO comment method role
	 * @param stockage
	 * @return
	 */
	public boolean ajouterCategorie(Stockage stockage) {
	    stockage.ajouterCategorie(this);
	    return true;
	}
}
