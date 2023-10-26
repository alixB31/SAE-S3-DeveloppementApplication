/*
 * 
 */
package modele;

import java.util.HashMap;

/**
 * 
 */
public class ListeQuestion {
	
	// Liste contenant les catégories, la clé est l'équivalante à l'intitulé d'une question
		// Cette intitulé est unique.
		HashMap<String, Question> listeQuestion;
		
		/**
		 * Construit un objet ListeQuestion.
		 */
		public ListeQuestion() {
			listeQuestion = new HashMap<>();
		}
		
		/**
		 * @return
		 */
		public HashMap getListeQuestion() {
			return listeQuestion;
		}
		
		/**
		 * @param cle
		 * @return
		 */
		public Question getElementListeQuestion(String cle) {
			return listeQuestion.get(cle);
		}
		
		/**
		 * @param cle
		 * @return
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
		 * @param cle
		 * @return
		 */
		public boolean elementEstDansListeQuestion(String cle) {
			return listeQuestion.containsKey(cle);
		}
}
