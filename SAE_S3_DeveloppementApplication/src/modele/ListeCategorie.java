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
     * Construit un objet ListeCategorie vide.
     */
    public ListeCategorie() {
        listeCategorie = new HashMap<>();
    }

    /** 
     * 
     * @return
     */
    public HashMap getListeCategorie() {
        return listeCategorie;
    }

    /** TODO comment method role
     * @param cle
     * @return
     */
    public Categorie getElementListeCategorie(String cle) {
        return listeCategorie.get(cle);
    }

    /** TODO comment method role
     * @param cle
     * @return
     */
    public boolean supprimerElementListeCategorie(String cle) {
        boolean estSupprime = false;
        if (elementEstDansListeCategorie(cle) && !cle.equals("Général")) {
            listeCategorie.remove(cle);
            estSupprime = true;
        }
        return estSupprime;
    }

    /** TODO comment method role
     * @param intitule
     * @return l'élément de la catégorie correspondant à son intitulé
     */
    public boolean elementEstDansListeCategorie(String intitule) {
        return listeCategorie.containsKey(intitule);
    }

    /** TODO comment method role
     * @param categorie
     * @return estAjoutee, true si la catégorie est ajoutée, false sinon.
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
     * @param nouvelleCategorie
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
