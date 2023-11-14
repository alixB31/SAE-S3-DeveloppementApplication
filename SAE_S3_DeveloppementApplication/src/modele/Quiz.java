package modele;

import java.util.ArrayList;

public class Quiz {

	private int difficulte;
	
	private int nombreQuestions;
	
	private Categorie categorie;
	
	private Stockage stockage;
	
	private ArrayList<Question> listeQuestion; 
	
	public Quiz(int difficulte, int nombreQuestions, Categorie categorie, Stockage stockage) {
		this.difficulte = difficulte;
		listeQuestion = new ArrayList<>();//stockage.listeQuestionFiltreDifficulteCategorie();
		this.nombreQuestions = nombreQuestions;
		if (stockage.getListeCategorie().containsKey(categorie.getIntituleCategorie())) {
			this.categorie = categorie;
		} else {
			throw new IllegalArgumentException("La catégorie n'existe pas dans la liste des catégories"
					+ " associé au stockage.");
		}
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
}
