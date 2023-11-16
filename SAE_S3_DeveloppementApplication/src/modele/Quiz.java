package modele;

import java.util.ArrayList;

public class Quiz {

	private int difficulte;
	
	private int nombreQuestions;
	
	private Categorie categorie;
	
	private Stockage stockage;
	
	private ArrayList<Question> listeQuestion; 
	
	private int score = 0;
	
	private boolean[] listeResultatReponse;
	
	public Quiz(int difficulte, Categorie categorie, Stockage stockage) {
		this.difficulte = difficulte;
		listeQuestion = stockage.listeQuestionFiltreDifficulteCategorieTaille(this);
		this.nombreQuestions = listeQuestion.size();
		this.listeResultatReponse = new boolean[listeQuestion.size()];
		if (categorie != null && stockage.getListeCategorie().containsKey(categorie.getIntituleCategorie())) {
			this.categorie = categorie;
		} else if (categorie == null) {
			System.out.println("Aucune catégorie n'est disponible!");
		} else {
			System.out.println("La catégorie n'existe pas dans la liste des catégories"
					+ " associé au stockage.");
		}
	}
	
	public void incrementerScore() {
		score++;
	}
	
	public void initialiserListeQuestionQuiz() {
		
	}
	
	public int getDifficulte() {
		return difficulte;
	}
	
	public int getNombreQuestions() {
		return nombreQuestions;
	}
	
	public Categorie getCategorie() {
		return categorie;
	}
	
	public ArrayList<Question> getListeQuestion(){
		return listeQuestion;
	}
	
	public boolean quantiteQuestionOk(int quantiteChoisi) {
		return this.nombreQuestions>=quantiteChoisi;
	}
	
	public void setNombreQuestion(int nombreQuestion) {
		this.nombreQuestions = nombreQuestion;
	}
	
	public void ajouterResultat(int indice, boolean resultat) {
		if (indice < listeResultatReponse.length) {
			listeResultatReponse[indice] = resultat;
			System.out.println("Ajout du résultat.");
		}
		System.out.println("Pas d'ajout de résultat!");
	}
	
	public boolean estJuste(String reponseChoisie, int indiceQuestion) {
		return reponseChoisie.equals(listeQuestion.get(indiceQuestion).getReponseJusteQuestion());
	}
	
	public ArrayList<Question> getCinqQuestions(int indicePage) {
    	int nombreQuestionAAfficher = listeQuestion.size()-(5*indicePage);
    	int indiceMinimum = indicePage*5;;
		if(nombreQuestionAAfficher>=5) {
			nombreQuestionAAfficher = 5;
		}

		ArrayList<Question> listeQuestion = new ArrayList<>();
    	for (int i = indiceMinimum; i < nombreQuestionAAfficher ;i++) {
    		listeQuestion.add(listeQuestion.get(indiceMinimum));
    	}
		return listeQuestion;
	}
}
