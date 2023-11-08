/*
 * ListeQuestion.java
 * année 2023-2024, BUT2 Informatique, no copyright
 */
package modele;

import java.util.HashMap;
import java.util.Map;

/**
 * Liste des questions de type HashMap.
 * @author Nathan Girardin, Mateo Faussurier, Rayan Ibrahim, Alix Brugier
 */
public class ListeQuestion {

    /*
     * Liste contenant les questions du quiz, cle correspont à la cle 
     * dans la liste pour acceder à une question.
     * l'intitule d'une question est unique.
     */
    HashMap<String, Question> listeQuestion;

    /**
     * Construit un objet ListeQuestion qui contiendra des objets Question.
     */
    public ListeQuestion() {
        listeQuestion = new HashMap<>();
    }

    /**
     * getter de la ListeQuestion
     * @return la liste des questions
     */
    public HashMap getListeQuestion() {
        return listeQuestion;
    }

    /**
     * getter d'une question dans la liste
     * @param cle designant la question que l'on veut getter
     * @return la question / l'element
     */
    public Question getElementListeQuestion(String cle) {
        return listeQuestion.get(cle);
    }

    /**
     * delete d'une question dans la liste 
     * @param cle designant la question qui va être supprime
     * @return true si la question à bien été supprimé, false sinon
     */
    public boolean supprimerElementListeQuestion(String cle) {
        boolean estSupprime = false;
        if (elementEstDansListeQuestion(cle) && !cle.equals("Général")) {
            listeQuestion.remove(cle);
            estSupprime = true;
        }
        return estSupprime;
    }

    /**
     * verifie si une question est deja dans la liste
     * @param cle 
     * @return true si la question est dans la liste, false sinon
     */
    public boolean elementEstDansListeQuestion(String cle) {
        return listeQuestion.containsKey(cle);
    }

    /** TODO comment method role
     * @param question 
     * @return estAjoutee, true si la question est ajoutée, false sinon.
     */
    public boolean ajouterElementListeQuestion(Question question) {
        boolean estAjoutee = false;
        String intitule = question.getIntituleQuestion();
        if (!elementEstDansListeQuestion(intitule) && !intitule.isEmpty() && !intitule.isBlank()) {
            listeQuestion.put(intitule, question);
            estAjoutee = true;
        }

        return estAjoutee;
    }

    /**
     * Modifie une question, on ne peut modifier que l'intitulé d'une question.
     * Les questions liées à une catégorie sont modifiées au sein de la classe
     * ListeQuestion.java.
     * @param ancienneQuestion l'ancienne question 
     * @param nouvelIntitule le nouvel intitule de la question
     * @return estModifiee, true si la catégorie est modifiée, false sinon.
     */
    public boolean modifierIntituleQuestion(Question ancienneQuestion, String nouvelIntitule) {
        boolean estModifiee = false;
        if (elementEstDansListeQuestion(ancienneQuestion.getIntituleQuestion())) {
            ancienneQuestion.setIntituleQuestion(nouvelIntitule);
            estModifiee = true;
        }
        return estModifiee;
    }

    public boolean modifierCategorieDeQuestion(Question question, Categorie categorie) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())) {
            question.setCategorieQuestion(categorie);
            estModifiee = true;
        }
        return estModifiee;
    }

    public boolean modifierDifficulteQuestion(Question question, int difficulte) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())) {
            question.setDifficulteQuestion(difficulte);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean modifierListeReponsesFaussesQuestion(Question question, String ancienneReponseFausse,
    		String nouvelleReponseFausse) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())) {
            question.setReponsesFaussesQuestion(ancienneReponseFausse, nouvelleReponseFausse);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean ajouterReponseFausse(Question question,String reponseFausse) {
    	boolean estModifiee = false;
    	boolean estDansListeReponseFausse = false;
    	for (int i = 0; i < question.listeReponsesFausses.size(); i++) {
    		if (question.listeReponsesFausses.get(i).equals(reponseFausse)) {
    			estDansListeReponseFausse = true;
    		}
    	}
		if (!reponseFausse.isBlank() && !reponseFausse.isBlank() && !estDansListeReponseFausse) {
			question.ajouterReponseFausse(reponseFausse);
			estModifiee = true;
		}
		return estModifiee;
    }
    
    public boolean modifierFeedBackQuestion(Question question, String feedBack) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())) {
            question.setFeedBackQuestion(feedBack);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean modifierReponseJusteQuestion(Question question, String reponseJuste) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())) {
            question.setReponseJusteQuestion(reponseJuste);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    
    /**
     * Renvoie la liste des questions correpondant à la catégorie en paramètre.
     * @param categorie la catégorie dont on veut la liste des questions.
     * @return listeQuestionCategorie, la liste des questions correspondantes.
     */
    public ListeQuestion listeQuestionParCategorie(Categorie categorie) {
        ListeQuestion listeQuestionParCategorie = new ListeQuestion();
        for (Map.Entry mapEntry: listeQuestion.entrySet()) {
            Question question = (Question) mapEntry.getValue();
            if(question.getCategorieDeQuestion().equals(categorie)) {
                listeQuestionParCategorie.listeQuestion.put(question.getIntituleQuestion(), question);
            }
        }
        return listeQuestionParCategorie;
    }

}
