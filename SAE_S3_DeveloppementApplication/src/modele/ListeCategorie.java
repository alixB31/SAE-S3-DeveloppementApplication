/*
 * ListeCategorie.java					             26/10/2023
 * Un objet permettant de stoquer les catégories existantes.
 * C'est un objet HashMap.
 */
package modele;

import java.util.HashMap;

/** 
 * Liste des catégories de type HashMap. Il ne peut pas y avoir plusieurs fois
 * des catégories de même intitulé.
 * @author mateo.faussurier
 *
 */
public class ListeCategorie {

    // Liste contenant les catégories, la clé est l'équivalante à l'intitulé
    // d'une catégorie. Cette intitulé est unique.
    HashMap<String, Categorie> listeCategorie;

    /**
     * Construit une HashMap ListeCategorie vide.
     */
    public ListeCategorie() {
        listeCategorie = new HashMap<>();
    }

    /** 
     * @return
     */
    public HashMap getListeCategorie() {
        return listeCategorie;
    }

    /**
     * Retourne la catégorie de la liste correspondant à la clé.
     * @param cle, l'intitulé de la catégorie représentant la clé de l'ensemble
     * @return l'objet catégorie correspondant, ou null s'il n'y a aucune
     * correspondance.
     */
    public Categorie getElementListeCategorie(String cle) {
    	
        return listeCategorie.get(cle);
    }

    /** 
     * Supprime l'éméent de la liste correspondant à la cle en paramètre.
     * @param cle, l'intitulé de la catégorie représentant la clé de l'ensemble
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

    /**
     * Modifie une catégorie, on ne peut modifier que l'intitulé d'une catégorie.
     * Les questions liées à une catégorie sont modifiées au sein de la classe
     * ListeQuestion.java.
     * @param ancienneCategorie
     * @param nouvelleIntitule 
     * @return estModifiee, true si la catégorie est modifiée, false sinon.
     */
    public boolean modifierElementListeCategorie(Categorie ancienneCategorie, String nouvelleIntitule) {
        boolean estModifiee = false;
        if (elementEstDansListeCategorie(ancienneCategorie.getIntituleCategorie())) {
            ancienneCategorie.setIntituleCategorie(nouvelleIntitule);
            estModifiee = true;
        }
        return estModifiee;
    }

}
