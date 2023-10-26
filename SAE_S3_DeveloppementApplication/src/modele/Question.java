package modele;

public class Question {
	
	/**/
	public Categorie categorieQuestion;

	/**/
	public String intituleQuestion;
	
	/**/
	public int difficulteQuestion;
	
	/**/
	public String[] listeReponseFausse;
	
	/**/
	public String reponseJuste;
	
	/**/
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
		this.listeReponseFausse = liste;
		this.reponseJuste = reponse;
		this.feedBackQuestion = feedBack;
	}
	
	/** TODO comment method role
	 * @param stockage
	 * @return
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
	
	
}
