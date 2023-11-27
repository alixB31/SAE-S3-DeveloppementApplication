/*
 * ListeQuestion.java
 * année 2023-2024, BUT2 Informatique, no copyright
 */
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Liste des questions de type HashMap.
 * @author Nathan Girardin, Mateo Faussurier, Rayan Ibrahim, Alix Brugier
 */
public class ListeQuestion implements Serializable{

    /**
     * Liste contenant les questions du quiz, cle correspont à la cle 
     * dans la liste pour acceder à une question.
     * l'intitule d'une question est unique.
     */
    private HashMap<String, Question> listeQuestion;

    private ArrayList<Question> listeASupprimer = new ArrayList<>();
    
    private ArrayList<Question> listeAAjouter = new ArrayList<>();
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

        if (elementEstDansListeQuestion(cle)) {
            listeQuestion.remove(cle);
            estSupprime = true;
        }
        return estSupprime;
    }

    /**
     * Verifie si une question est deja dans la liste
     * @param cle 
     * @return true si la question est dans la liste, false sinon
     */
    public boolean elementEstDansListeQuestion(String cle) {
        return listeQuestion.containsKey(cle);
    }

    /**
     * Ajoute une question dans la liste des questions du Stockage courrant.
     * @param question  la question à ajouter à la liste des questions.
     * @return estAjoutee, true si la question est ajoutée, false sinon.
     */
    public boolean ajouterElementListeQuestion(Question question) {
        boolean estAjoutee = false;
        String intitule = question.getIntituleQuestion();

    	String categorie = question.getCategorieDeQuestion().getIntituleCategorie();
        String concatenation = intitule+categorie;
        if (!elementEstDansListeQuestion(concatenation) && intitule!= null && !intitule.isEmpty() && !intitule.isBlank()
        		&& (question.getDifficulteQuestion() == 1 || question.getDifficulteQuestion() == 2 || question.getDifficulteQuestion() == 3)) {
            listeQuestion.put(concatenation, question);
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
    public boolean modifierIntituleQuestion(Question question, String nouvelIntitule, String concatenation) {
        boolean estModifiee = false;
        if (nouvelIntitule != null) {
        	if (!nouvelIntitule.equals(question.getIntituleQuestion())
            		&& elementEstDansListeQuestion(concatenation/*question.getIntituleQuestion()+question.getCategorieDeQuestion().getIntituleCategorie()*/)
            		&& !nouvelIntitule.isBlank()
            		&& !nouvelIntitule.isEmpty()) {
               if (this.supprimerElementListeQuestion(concatenation)) {
            	   question.setIntituleQuestion(nouvelIntitule);
            	   if (this.ajouterElementListeQuestion(question)) {
            		   estModifiee = true;
            	   }  
               }
            } else if (nouvelIntitule.equals(question.getIntituleQuestion())) {
            	estModifiee = true;
            }
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
    	if (question.getCategorieDeQuestion().equals(categorie)) {
    		estModifiee = true;
    		System.out.println("c les meme" + categorie.getIntituleCategorie());
    	} else if (categorie != null
    			&& elementEstDansListeQuestion(question.getIntituleQuestion()+question.getCategorieDeQuestion().getIntituleCategorie())
        		&& !elementEstDansListeQuestion(question.getIntituleQuestion()+categorie.getIntituleCategorie())) {
//    		supprimerElementListeQuestion(question.getIntituleQuestion()+question.getCategorieDeQuestion().getIntituleCategorie());
    		listeASupprimer.add(question);
    		question.setCategorieQuestion(categorie);
    		listeAAjouter.add(question);
//    		ajouterElementListeQuestion(question);
//            question.setCategorieQuestion(categorie);
//            ajouterElementListeQuestion(question);
            
            estModifiee = true;
        }
        return estModifiee;
    }

    /**
     * Mofifie la difficulté d'une question. Une difficulté ne peut être égale
     * qu'à 1, 2 ou 3.
     * @param question la question dont on modifie la difficulté.
     * @param difficulte la nouvelle difficulté.
     * @return estModifiee est true si la difficulte est modifiée, false sinon.
     */
    public boolean modifierDifficulteQuestion(Question question, int difficulte, String concatenation) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(concatenation)
        		&& (difficulte == 1 || difficulte == 2 || difficulte == 3)) {
            question.setDifficulteQuestion(difficulte);
           
            estModifiee = true;
        }
        return estModifiee;
    }
    
    /**
     * Modifie un élément de la liste des réponses fausses.
     * @param question la question dont on modifie la réponse fausse
     * @param ancienneReponseFausse l'ancienne réponse fausse.
     * @param nouvelleReponseFausse la nouvelle réponse fausse.
     * @return estModifiee si la réponse fausse de la question est modifée, false sinon.
     */
    public boolean modifierListeReponsesFaussesQuestion(Question question,
    		ArrayList<String> liste, String concatenation) {
    	boolean estModifiee = false;
        if (elementEstDansListeQuestion(concatenation)
        		&& question.listeReponsesFaussesValide(liste)){
    		question.setReponsesFaussesQuestion(liste);      
            estModifiee = true;

      	}
        return estModifiee;
    }
    
    /**
     * Modifie le feedback de la question. Une question n'a pas forcément
     * de feedBack mais un feedBack ne peut être enregistré vide ou
     * rempli uniquement d'espace.
     * @param question la question dont on veut modifier le feedback.
     * @param feedBack la nouvelle version du feedBack
     * @return estModifiee true si le feedBack est modifié, false sinon;
     */
    public boolean modifierFeedBackQuestion(Question question, String feedBack, String concatenation) {
    	boolean estModifie = false;
        if (feedBack!= null&& elementEstDansListeQuestion(concatenation)) {
            question.setFeedBackQuestion(feedBack);
            estModifie = true;
        }
        return estModifie;
    }
    
    /**
     * Modifie la réponse juste d'une question. La réponse juste est
     * obligatoire. Elle doit être obligatoirement remplie d'au moins
     * un caractère. Le text de la réponse juste ne peut pas être blanc.
     * @param question la question dont on modifie la réponse juste.
     * @param reponseJuste la nouvelle réponse juste.
     * @return modifiee est true si la réponse juste est modifiée, false sinon.
     */
    public boolean modifierReponseJusteQuestion(Question question, String reponseJuste, String concatenation) {
    	boolean estModifiee = false;
        if (reponseJuste != null && elementEstDansListeQuestion(concatenation)
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
    public ArrayList<Question> listeQuestionParCategorie(Categorie categorie) {
        ArrayList<Question> listeQuestionParCategorie = new ArrayList<>();
        for (Map.Entry mapEntry: listeQuestion.entrySet()) {
            Question question = (Question) mapEntry.getValue();
            if(question.getCategorieDeQuestion().equals(categorie)) {
                listeQuestionParCategorie.add(question);
            }
        }
        return listeQuestionParCategorie;
    }
    
    /**
     * Supprime les questions en fonction de leur catégorie.
     * @param categorie la catégorie des questions à supprimer.
     * @return sontSupprimees est true si les questions sont supprimées,
     * false sinon.
     */
    public boolean supprimerQuestionParCategorie(Categorie categorie) {
    	boolean sontSupprimees = false;
    	ArrayList<Question> listeQuestionParCategorie = listeQuestionParCategorie(categorie);
    	if (listeQuestionParCategorie.size() == 0) {
    		sontSupprimees = true;
    	} else {
    		for (int i = 0; i < listeQuestionParCategorie.size(); i++) {
                if (listeQuestionParCategorie.get(i).getCategorieDeQuestion().equals(categorie)) {
                	supprimerElementListeQuestion(listeQuestionParCategorie.get(i).getIntituleQuestion());
                }
                sontSupprimees = true;
            }
    	}
    	return sontSupprimees;
    }
    
    /**
     * Supprime une réponse fausse.
     * @param question la question dont on modifie la réponse fausse.
     * @param ancienneReponseFausse L'ancieen réponse fausse à supprimer.
     * @return
     */
    public boolean supprimerReponseFausse(Question question, String ancienneReponseFausse) {
    	boolean estSupprimeReponseFausse = false;
    	if (question.reponseFausseExiste(ancienneReponseFausse)
    			&& question.listeReponsesFausses.size() > 1) {
    		for (int i = 0; i< question.getReponsesFaussesQuestion().size(); i++) {
    			if (ancienneReponseFausse.equals(question.getReponsesFaussesQuestion().get(i))) {
    				estSupprimeReponseFausse = true;
    				question.supprimerReponseFausseQuestion(i);
    			}
    		}
    	}
    	return estSupprimeReponseFausse;
    }
    
    public boolean modifierQuestionParCategorie(Categorie categorie, Categorie nouvelleCategorie) {
    	boolean sontModifiees = false;
    	if (this.listeQuestionParCategorie(categorie).size() == 0) {
    		sontModifiees = true;
    	} else {
    		for (Map.Entry question: listeQuestion.entrySet()) {
    			Question questionObject = (Question)question.getValue();
        		if (questionObject.getCategorieDeQuestion().equals(categorie)) {
        			modifierCategorieDeQuestion(questionObject, nouvelleCategorie);
        			sontModifiees = true;
        		}
        	}
    		for (int i = 0; i < listeAAjouter.size(); i++) {
    			this.ajouterElementListeQuestion(listeAAjouter.get(i));
    		}
    		for (int i = 0; i< listeASupprimer.size(); i++) {
    			this.supprimerElementListeQuestion(listeASupprimer.get(i).getIntituleQuestion());
    		}
    		listeASupprimer.clear();
    		listeAAjouter.clear();
    	}
    	return sontModifiees;
    }
    
    /**
     * 
     * @param quiz
     * @return
     */
    public ArrayList<Question> listeQuestionFiltreDifficulteCategorieTaille(Quiz quiz) {
    	ArrayList<Question> listeFiltree = new ArrayList<>();
    	if (quiz.getCategorie() != null) {
    		listeFiltree = listeQuestionParCategorie(quiz.getCategorie());
    	} else {
    		
    		for (Map.Entry entry : listeQuestion.entrySet()) {
    			listeFiltree.add((Question)entry.getValue());
    		}
    	}
    	
    	Collections.shuffle(listeFiltree);
    	
    	// Filtrage de la difficulté
    	if (quiz.getDifficulte()!= 0) {
    		for (int i = 0; i< listeFiltree.size(); i++) {
        		if (listeFiltree.get(i).getDifficulteQuestion()!= quiz.getDifficulte()) {
        			listeFiltree.remove(i);
        		}
        	}
    	}
    	if (quiz.getNombreQuestions() > listeFiltree.size()) {
    		quiz.setNombreQuestion(listeFiltree.size());
    	}
    	// On retire les question en trop

    	System.out.println("Nombre question dans liste avant : " + listeFiltree.size());
    	while (quiz.getNombreQuestions() < listeFiltree.size()) {
   		listeFiltree.remove(0);
    	}
    	System.out.println("Nombre question dans liste apres : " + listeFiltree.size());
    	System.out.println("Nombre question apres : "+ quiz.getNombreQuestions());
    	return listeFiltree;
    }
    
    public ArrayList<String> getReponsesOrdreAleatoire(Question question) {
    	return question.getListeReponsesOrdreAleatoire();
    }
}
