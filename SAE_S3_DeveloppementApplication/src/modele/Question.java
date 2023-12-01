/**
 * Question.java									08 novembre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */

package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Les objets question contiennent les paramètres d'une question. Les questions
 * sont données à l'utilisateur durant un quizz. Un utilisateur peu modifier,
 * créer et supprimer des questions.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class Question implements Serializable{

	/** une question est toujours liée à une catégorie */
	public Categorie categorieQuestion;

	/** L'intitulé de la question */
	public String intituleQuestion;

	/** Les différentes difficultés d'une question sont du niveau 1(facile)
	 *  à 3(difficile) */
	public int difficulteQuestion;

	/** Liste des réponses fausses d'une question, il y en a de 1 à 4*/
	public ArrayList<String> listeReponsesFausses;

	/** L'unique réponse juste d'une question */
	public String reponseJuste;

	/** Un text explicatif sur la questions, sera retourné à l'utilisateur
	 * à la fin du quiz */
	public String feedBackQuestion;

	/**
	 * Constructeru d'une question
	 * @param intitule
	 * @param categorie
	 * @param difficulte
	 * @param liste
	 * @param reponse
	 * @param feedBack
	 */
	public Question(String intitule, Categorie categorie, int difficulte, 
			ArrayList<String> liste, String reponse, String feedBack) {
		this.intituleQuestion = intitule;
		this.categorieQuestion = categorie;
		this.difficulteQuestion = difficulte;
		this.listeReponsesFausses = liste;
		this.reponseJuste = reponse;
		this.feedBackQuestion = feedBack;
	}
	/**
	 * @return intituleCategorie, l'intitule de la catégorie courrante.
	 */
	public String getIntituleQuestion() {
		return intituleQuestion;
	}

	/**
	 * @return categorieQuestion la catégorie de la question courrante.
	 */
	public Categorie getCategorieDeQuestion() {
		return this.categorieQuestion;
	}

	/**
	 * @return difficulteQuestion la difficulte de la question courrante.
	 */
	public int getDifficulteQuestion() {
		return this.difficulteQuestion;
	}

	/**
	 * @return listeReponseFausse la liste des réponses fausses de la
	 * question courrante.
	 */
	public ArrayList<String> getReponsesFaussesQuestion() {
		return this.listeReponsesFausses;
	}

	/**
	 * @return reponseJuste la réponse juste de la question courrante.
	 */
	public String getReponseJusteQuestion() {
		return this.reponseJuste;
	}
	/**
	 * @return feedBackQuestion le retour sur la question pour l'utilisateur
	 * à la fin du quiz.
	 */
	public String getFeedBackQuestion() {
		return this.feedBackQuestion;
	}

	/**
	 * @param nouvelIntitule le nouvel intitulé de la question
	 */
	public void setIntituleQuestion(String nouvelIntitule) {
		this.intituleQuestion = nouvelIntitule;
	}

	/**
	 * @param categorie la nouvelle catégorie de la question.
	 */
	public void setCategorieQuestion(Categorie categorie) {
		this.categorieQuestion = categorie;
	}

	/**
	 * @param difficulte la nouvelle difficulte de la question.
	 */
	public void setDifficulteQuestion(int difficulte) {
		this.difficulteQuestion = difficulte;
	}

	/**
	 * @param reponseJuste , la nouvelle réponse juste de la question
	 * courrante.
	 */
	public void setReponseJusteQuestion(String reponseJuste) {
		if (!reponseFausseExiste(reponseJuste)) {
			this.reponseJuste = reponseJuste;
		}
	}

	/**
	 * @param feedBack le nouveau feedback de la questoion.
	 */
	public void setFeedBackQuestion(String feedBack) {
		this.feedBackQuestion = feedBack;
	}
	

	/**
	 * Modifie une réponse fausse déjà existante. La nouvelle réponse fausse
	 * doit être unique dans la liste des réponses fausses d'une question.
	 * @param indice celui correspondant à la réponse fausse que l'on modifie.
	 * @param nouvelleReponseFausse la nouvelle version de la réponse fausse
	 * qui remplace l'ancienne.
	 */
	public void setReponsesFaussesQuestion(ArrayList<String> liste) {
			this.listeReponsesFausses = liste;

	}
	
	/**
	 * Une réponse fausse ne peut être supprimé que s'il en existe au moins 2.
	 * @param indice l'indice de la liste des réponses fausses correspondant
	 * à la réponse fausse que l'on supprime.
	 */
	public void supprimerReponseFausseQuestion(int indice) {
		this.listeReponsesFausses.remove(indice);
	}
	
	public boolean listeReponsesFaussesValide(ArrayList<String> listeReponsesFausses) {
		boolean estValide = true;
		String reponse;
		for (int i = 0; i <listeReponsesFausses.size()
				&& estValide == true; i++) {
			reponse = listeReponsesFausses.get(i);
			if ( reponse== null || reponse.isBlank() || reponse.isEmpty()) {
				estValide = false;
			}
		}
		return estValide;
	}
	
	/**
	 * Vérifie si une réponse fausse existe dans la liste des réponses fausses
	 * d'une question.
	 * @param reponseFausseATester la réponse dont on vérifie l'existance.
	 * @return  estDansListeReponseFausse est true si la réponse se trouve dans
	 * la liste, false sinon.
	 */
	public boolean reponseFausseExiste(String reponseFausseATester) {
		boolean estDansListeReponseFausse = false;
		for (int i = 0; i < listeReponsesFausses.size(); i++) {
    		if (listeReponsesFausses.get(i)!= null && reponseFausseATester!= null && listeReponsesFausses.get(i).equals(reponseFausseATester)) {
    			estDansListeReponseFausse = true;
    		}
    	}
		return estDansListeReponseFausse;
	}
	
	public ArrayList<String> getListeReponsesOrdreAleatoire(){
		
    	ArrayList<String> listeReponses = new ArrayList<>();
    	for (int i = 0; i < listeReponsesFausses.size(); i++) {
    		listeReponses.add(listeReponsesFausses.get(i));
    	}
    	
    	// Ajout de la réponse juste;
    	listeReponses.add(getReponseJusteQuestion());
    	Collections.shuffle(listeReponses);
    	return listeReponses;
    }
}
