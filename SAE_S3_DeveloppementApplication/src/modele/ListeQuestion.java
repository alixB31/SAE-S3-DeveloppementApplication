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

    /**
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
        // je crois qu'on s'en fous que la question sois dans general
        if (elementEstDansListeQuestion(cle)) {
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

    /**
     * @param question  la question à ajouter à la liste des questions.
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
     * @param question la question dont on modifie l'intitulé.
     * @param nouvelIntitule le nouvel intitule de la question.
     * @return estModifiee est true si la catégorie est modifiée, false sinon.
     */
    public boolean modifierIntituleQuestion(Question question, String nouvelIntitule) {
        boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& !nouvelIntitule.isBlank() && !nouvelIntitule.isEmpty()
        		&& nouvelIntitule != null) {
            question.setIntituleQuestion(nouvelIntitule);
            estModifiee = true;
        }
        return estModifiee;
    }

    /**
     * Modifie la catégorie d'une question existante dans la liste. Il faut que
     * la catégorie ne soit pas null et qu'elle existe dans la liste des catégories.
     * @param question la question dont on modifie la catégorie.
     * @param categorie la catégorie qui remplacera l'ancienne.
     * @return estModifiee est true si la catégorie est modifée, false sinon.
     */
    public boolean modifierCategorieDeQuestion(Question question, Categorie categorie) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& categorie != null) {
            question.setCategorieQuestion(categorie);
            estModifiee = true;
        }
        return estModifiee;
    }

    /**
     * Mofifie la difficulté d'une question. Une difficulté ne peut être égale
     * qu'à 
     * @param question
     * @param difficulte
     * @return
     */
    public boolean modifierDifficulteQuestion(Question question, int difficulte) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& (difficulte == 1 || difficulte == 2 || difficulte == 3)) {
            question.setDifficulteQuestion(difficulte);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean modifierListeReponsesFaussesQuestion(Question question, String ancienneReponseFausse,
    		String nouvelleReponseFausse) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& !nouvelleReponseFausse.isBlank() && !nouvelleReponseFausse.isEmpty()
        		&& !question.reponseFausseExiste(nouvelleReponseFausse)) {
        	for (int i = 0; i< question.getReponsesFaussesQuestion().size(); i++) {
    			if (ancienneReponseFausse.equals(question.getReponsesFaussesQuestion().get(i))) {
    				question.setReponsesFaussesQuestion(i, nouvelleReponseFausse);
    			}
    		}
            
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean ajouterReponseFausse(Question question,String reponseFausse) {
    	boolean estModifiee = false;
		if (!reponseFausse.isBlank() && !reponseFausse.isBlank() && !question.reponseFausseExiste(reponseFausse)
				&& question.getReponsesFaussesQuestion().size() < 4) {
			question.ajouterReponseFausse(reponseFausse);
			estModifiee = true;
		}
		return estModifiee;
    }
    
    public boolean modifierFeedBackQuestion(Question question, String feedBack) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& !feedBack.isBlank() && !feedBack.isEmpty()) {
            question.setFeedBackQuestion(feedBack);
            estModifiee = true;
        }
        return estModifiee;
    }
    
    public boolean modifierReponseJusteQuestion(Question question, String reponseJuste) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(question.getIntituleQuestion())
        		&& !reponseJuste.isBlank() && !reponseJuste.isEmpty()) {
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
    
    public boolean supprimerReponseFausse(Question question, String ancienneReponseFausse) {
    	boolean estSupprimeReponseFausse = false;
    	if (question.reponseFausseExiste(question.getIntituleQuestion())
    			&& question.listeReponsesFausses.size() > 1) {
    		for (int i = 0; i< question.getReponsesFaussesQuestion().size(); i++) {
    			if (ancienneReponseFausse.equals(question.getReponsesFaussesQuestion().get(i))) {
    				estSupprimeReponseFausse = true;
    				question.supprimerReponseFausseQuestion(i);
    			}
    		}
    		question.supprimerReponseFausseQuestion(0);
    	}
    	return estSupprimeReponseFausse;
    }
}
