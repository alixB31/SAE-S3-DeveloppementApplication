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
	
	public Quiz(int difficulte, int nombreQuestions, Categorie categorie, Stockage stockage) {
		this.difficulte = difficulte;
		listeQuestion = stockage.listeQuestionFiltreDifficulteCategorieTaille(this);
		this.listeResultatReponse = new boolean[listeQuestion.size()];
		if (stockage.getListeCategorie().containsKey(categorie.getIntituleCategorie())) {
			this.categorie = categorie;
		} else {
			throw new IllegalArgumentException("La catégorie n'existe pas dans la liste des catégories"
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
		return listeQuestion.size()>=quantiteChoisi;
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
}
