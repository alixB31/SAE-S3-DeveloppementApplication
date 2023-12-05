/**
 * Categorie.java									26 octobre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */


package modele;

import java.io.Serializable;

/**
 * Classe regroupant l'ensemble des methodes servant a la gestion des categories 
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class Categorie implements Serializable{

	/** intitule de la categorie */
	private String intituleCategorie;
	
	/**
	 * Constructeur d'une categorie
	 * @param intitule
	 */
	public Categorie(String intitule) {
		this.intituleCategorie = intitule;
	}
	
	
	/**
	 * @return intituleCategorie, l'intitule de la catégorie courante.
	 */
	public String getIntituleCategorie() {
	    return intituleCategorie;
	}
	
	
}
