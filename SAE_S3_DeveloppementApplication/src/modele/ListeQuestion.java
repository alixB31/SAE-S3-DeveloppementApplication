/*
 * ListeQuestion.java
 * année 2023-2024, BUT2 Informatique, no copyright
 */
package modele;

import java.util.HashMap;

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
		 * @return
		 */
		public boolean elementEstDansListeQuestion(String cle) {
			return listeQuestion.containsKey(cle);
		}
}
