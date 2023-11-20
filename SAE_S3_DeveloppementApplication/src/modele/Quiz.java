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
	
	public Quiz(int difficulte,int nombreQuestion, Categorie categorie, Stockage stockage) {
		this.difficulte = difficulte;
		nombreQuestions = nombreQuestion;
		if (categorie != null && stockage.getListeCategorie().containsKey(categorie.getIntituleCategorie())) {
			this.categorie = categorie;
			System.out.println(categorie.getIntituleCategorie());
		} else if (categorie == null) {
			System.out.println("Aucune catégorie n'est disponible!");
		} else {
			System.out.println("La catégorie n'existe pas dans la liste des catégories"
					+ " associée au stockage.");
		}
		listeQuestion = stockage.listeQuestionFiltreDifficulteCategorieTaille(this);
		this.nombreQuestions = listeQuestion.size();
		this.listeResultatReponse = new boolean[listeQuestion.size()];
		
	}
	
	public void incrementerScore() {
		score++;
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
	}
	
	public boolean estJuste(String reponseChoisie, int indiceQuestion) {
		return reponseChoisie.equals(listeQuestion.get(indiceQuestion).getReponseJusteQuestion());
	}
	
	public ArrayList<Question> getCinqQuestions(int indicePage) {
    	int nombreQuestionAAfficher = listeQuestion.size()-(5*indicePage);
    	int indiceMinimum;
    	if (indicePage != 0) {
    		indiceMinimum = (indicePage*5)-1;
    	} else {
    		indiceMinimum = indicePage*5;
    	}
		if(nombreQuestionAAfficher > 5) {
			nombreQuestionAAfficher = 5;
		}
		ArrayList<Question> listeCinqQuestions = new ArrayList<>();
    	for (int i = 0; i < nombreQuestionAAfficher ;i++) {
    		listeCinqQuestions.add(listeQuestion.get(indiceMinimum+i));
    	}
    	System.out.println("Longueur liste cinq questions " +listeCinqQuestions.size());
		return listeCinqQuestions;
	}
	
	public boolean getResultatDeQuestion(int indice) {
		return listeResultatReponse[indice];
	}
	
	public int getScoreFinal() {
		return score;
	}
}
