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
	
	/**
	 * @param nouvelleCategorie
	 */
	public boolean modifierCategorieQuestion(Categorie nouvelleCategorie) {
		//TODO ecrire le corps...
		return false;
	}
	
	/**
	 * @param nouvelleDifficulte
	 */
	public boolean modifierDifficulteQuestion(int nouvelleDifficulte) {
		//TODO ecrire le corps...
		return false;
	}
	
	public boolean modifierReponseFausseQuestion(String[] nouvelleReponseFausse) {
		//TODO ecrire le corps...
		return false;
	}
	
	public boolean modifierReponseJusteQuestion(String nouvelleReponseJuste) {
		//TODO ecrire le corps...
		return false;
	}
	
	public boolean ajouterReponseFausse(String nouvelleReponseFausse) {
		//TODO ecrire le corps...
		return false;
	}
	
	public boolean modifierFeedBackQuestion(String nouveauFeedBack) {
		//TODO ecrire le corps...
		return false;
	}
	
	public boolean supprimerReponseFausse(String reponse) {
		//TODO ecrire le corps...
		return false; 
	}
	
	public boolean ajouterQuestion(Question question) {
		//TODO ecrire le corps...
		return false;
	}
	
	
}
