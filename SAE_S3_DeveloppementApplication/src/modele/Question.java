package modele;

/**
 * Les objets question contiennent les paramètres d'une question. Les questions
 * sont données à l'utilisateur durant un quizz. Un utilisateur peu modifier,
 * créer et supprimer des questions.
 * @author mateo.faussurier
 */
public class Question {
	
	/** une question est toujours liée à une catégorie */
	public Categorie categorieQuestion;

	/** L'intitulé de la question */
	public String intituleQuestion;
	
	/** Les différentes difficultés d'une question sont du niveau 1(facile)
	 *  à 3(difficile) */
	public int difficulteQuestion;
	
	/** Liste des réponses fausses d'une question, il y en a de 1 à 4*/
	public String[] listeReponsesFausses;
	
	/** L'unique réponse juste d'une question */
	public String reponseJuste;
	
	/** Un text explicatif sur la questions, sera retourné à l'utilisateur
	 * à la fin du quiz */
	public String feedBackQuestion;
	
	/**
	 * @param intitule
	 * @param categorie
	 * @param difficulte
	 * @param liste
	 * @param reponse
	 * @param feedBack
	 */
	public Question(String intitule, Categorie categorie, int difficulte, 
					String[] liste, String reponse, String feedBack) {
		this.intituleQuestion = intitule;
		this.categorieQuestion = categorie;
		this.difficulteQuestion = difficulte;
		this.listeReponsesFausses = liste;
		this.reponseJuste = reponse;
		this.feedBackQuestion = feedBack;
	}
	
	/** TODO comment method role
	 * @param stockage
	 * @return true
	 */
	public boolean ajouterQuestion(Stockage stockage) {
	    stockage.ajouterQuestion(this);
	    return true;
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
	public String[] getReponsesFaussesQuestion() {
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
         * @param reponsesFausses les nouvelles réponses fausses de la question
         * courrante.
         */
        public void setReponsesFaussesQuestion(String[] reponsesFausses) {
            this.listeReponsesFausses = reponsesFausses;
        }
        
        /**
         * @param reponseJuste , la nouvelle réponse juste de la question
         * courrante.
         */
        public void setReponseJusteQuestion(String reponseJuste) {
            this.reponseJuste = reponseJuste;
        }
        
        /**
         * TODO comment method role
         * @param feedBack
         */
        public void setFeedBackQuestion(String feedBack) {
            this.feedBackQuestion = feedBack;
        }
}
