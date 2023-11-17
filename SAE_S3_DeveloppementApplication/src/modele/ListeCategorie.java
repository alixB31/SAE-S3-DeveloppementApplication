/*
 * ListeCategorie.java					             26/10/2023
 * Un objet permettant de stoquer les catégories existantes.
 * C'est un objet HashMap.
 */
package modele;

import java.io.Serializable;
import java.util.HashMap;

/** 
 * Liste des catégories de type HashMap. Il ne peut pas y avoir plusieurs fois
 * des catégories de même intitulé.
 * @author mateo.faussurier
 *
 */
public class ListeCategorie implements Serializable{

    /**
     * Liste contenant les catégories, la clé est l'équivalante à l'intitulé
     * d'une catégorie. Cette intitulé est unique.
     */
    HashMap<String, Categorie> listeCategorie;

    /**
     * Construit une HashMap ListeCategorie vide.
     */
    public ListeCategorie() {
        listeCategorie = new HashMap<>();
    }

    /** 
     * @return listeCategorie la liste contenant les catégories.
     */
    public HashMap getListeCategorie() {
        return listeCategorie;
    }

    /**
     * Retourne la catégorie de la liste correspondant à la clé.
     * @param cle , l'intitulé de la catégorie représentant la clé de l'ensemble
     * @return l'objet catégorie correspondant, ou null s'il n'y a aucune
     * correspondance.
     */
    public Categorie getElementListeCategorie(String cle) {
    	
        return listeCategorie.get(cle);
    }

    /** 
     * Supprime l'éméent de la liste correspondant à la cle en paramètre.
     * @param cle , l'intitulé de la catégorie représentant la clé de l'ensemble
     * @return estSupprime true si l'élément c'est supprimé, false sinon
     */
    public boolean supprimerElementListeCategorie(String cle) {
        boolean estSupprime = false;
        if (elementEstDansListeCategorie(cle) && !cle.equals("Général")) {
            listeCategorie.remove(cle);
            estSupprime = true;
        }
        return estSupprime;
    }

    /**
     * Vérifie si l'intitulé d'une catégorie est déjà présent dans la liste
     * des catégories.
     * @param intitule
     * @return l'élément de la catégorie correspondant à son intitulé
     */
    public boolean elementEstDansListeCategorie(String intitule) {
        return listeCategorie.containsKey(intitule);
    }

    /** 
     * Ajoute une categorie a listeCategorie en récuperant son intitule.
     * @param categorie
     * @return estAjoutee, true si la catégorie a était ajoutée, false sinon.
     */
    public boolean ajouterElementListeCategorie(Categorie categorie) {
        boolean estAjoutee = false;
        String intitule = categorie.getIntituleCategorie();
        if (!elementEstDansListeCategorie(intitule) && !intitule.isEmpty() 
                && !intitule.isBlank() && !intitule.equals("Général")) {
            listeCategorie.put(intitule, categorie);
            estAjoutee = true;
        }
        return estAjoutee;
    }



}
