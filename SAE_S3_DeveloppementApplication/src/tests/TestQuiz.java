package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Quiz;
import modele.Stockage;
import modele.Question;
import modele.Categorie;


public class TestQuiz {

	
	private final int[] DIFFICULTE_VALIDE = {0,1,2,3};
	
	private final int[] DIFFICULTE_INVALIDE = {-1,-2,-3,4,10,100,1000};
	
	private final int[] NOMBRE_QUESTION_VALIDE = {5,10,20};
	
	/**
	 *  Liste des catégories qui seront valide lors de la création du quiz.
	 * Si le paramètre catégorie los de la construction du quiz est égale à null
	 * cela signifie que le joueur à sélectionné l'option "Toutes les catgéories".
	 */
	private final Categorie[] CATEGORIE_VALIDE = {new Categorie("Bonjour"),new Categorie("Java"),
			new Categorie("Type"), new Categorie("Photosynthèse"), null};
	
	
	private final Categorie[] CATEGORIE_INVALIDE = {new Categorie("Pas dans le stockage"),
			new Categorie("Toujours pas")};
	
	private final Stockage[] STOCKAGE_VALIDE = {new Stockage()};
	
	private final Stockage[] STOCKAGE_INVALIDE = {null};
	
	private final ArrayList<String> REPONSES_FAUSSES = new ArrayList<String>() {{add("faux");add("faux2");add("faux3");}};
	
	private final Question[] QUESTIONS_INITIALISATION_STOCKAGE = {new Question("Q1", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q2", CATEGORIE_VALIDE[1], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q3", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q4", CATEGORIE_VALIDE[1], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q5", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q6", CATEGORIE_VALIDE[1], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q7", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q8", CATEGORIE_VALIDE[1], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q9", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q10", CATEGORIE_VALIDE[1], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q11", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q12", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q13", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q14", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q15", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q16", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q17", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q18", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q19", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""),
			new Question("Q20", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", ""), new Question("Q21", CATEGORIE_VALIDE[2], 1, REPONSES_FAUSSES, "vrai", "")};
	
	private Stockage stockageTest;
	
	private Quiz quizTestValide;
	
	private Quiz quizTestInvalide;
	
	@BeforeEach
	void setUp() throws Exception{
		stockageTest = new Stockage();
		
		// Ajout des catégories dans le stockage
		for (int i = 0; i < CATEGORIE_VALIDE.length; i++) {
			stockageTest.ajouterCategorie(CATEGORIE_VALIDE[i]);
		}
		
		// Ajout des questions dans le stockage
		for (int i = 0; i < QUESTIONS_INITIALISATION_STOCKAGE.length; i++) {
			stockageTest.ajouterQuestion(QUESTIONS_INITIALISATION_STOCKAGE[i]);
		}
		
	}
	
	@Test
	void testListeQuestionFiltreDifficulteCategorieTaille() {
		Quiz quiz = new Quiz(DIFFICULTE_VALIDE[0], NOMBRE_QUESTION_VALIDE[2],CATEGORIE_VALIDE[2],stockageTest);
		stockageTest.listeQuestionFiltreDifficulteCategorieTaille(quiz);
		assertEquals(quiz.getNombreQuestions(), 16, "Vérification du nombre de questions");
		
		Quiz quiz2 = new Quiz(DIFFICULTE_VALIDE[0], NOMBRE_QUESTION_VALIDE[1],CATEGORIE_VALIDE[2],stockageTest);
		stockageTest.listeQuestionFiltreDifficulteCategorieTaille(quiz);
		assertEquals(quiz.getNombreQuestions(), 16, "Vérification du nombre de questions");
	}
	
	@Test
	void tetConstructeurQuiz() {
		// Quiz avec diffrérentes difficultés valides.
		for (int i = 0; i < DIFFICULTE_VALIDE.length; i++) {
			quizTestValide = new Quiz(DIFFICULTE_VALIDE[i], NOMBRE_QUESTION_VALIDE[0],CATEGORIE_VALIDE[0],stockageTest);
		}
		
		// Quiz avec des difficultés invalides.
		for (int i = 0; i < DIFFICULTE_INVALIDE.length; i++) {
			quizTestValide = new Quiz(DIFFICULTE_INVALIDE[i], NOMBRE_QUESTION_VALIDE[0],CATEGORIE_VALIDE[0],stockageTest);
		}
		
		// Quiz avec des catégories valides
		for (int i = 0; i < CATEGORIE_VALIDE.length; i++) {
			quizTestValide = new Quiz(DIFFICULTE_VALIDE[0], NOMBRE_QUESTION_VALIDE[0],CATEGORIE_VALIDE[i],stockageTest);
		}
	}
	
	@Test
	void testGetScoreFinal() {
		Quiz quiz = new Quiz(DIFFICULTE_VALIDE[0], NOMBRE_QUESTION_VALIDE[2],CATEGORIE_VALIDE[2],stockageTest);
		for (int i = 0; i < quiz.getListeQuestion().size(); i++) {
			quiz.ajouterResultat(i, false);
		}
		assertFalse(quiz.estJuste("faux", 5));
		assertEquals(quiz.getScoreFinal(), 0, "Resultat du quiz à 0.");
		
		for (int i = 0; i < quiz.getListeQuestion().size(); i++) {
			quiz.ajouterResultat(i, true);
			quiz.incrementerScore();
		}
		assertTrue(quiz.estJuste("vrai", 5));
		assertEquals(quiz.getScoreFinal(), 16, "Resultat du quiz parfait");
	}
	
	@Test
	void testGetCinqQuestions() {
		// Quiz avec 0 question correspondante
		Quiz quizVide = new Quiz(DIFFICULTE_VALIDE[2], NOMBRE_QUESTION_VALIDE[1], CATEGORIE_VALIDE[0], stockageTest);
		assertEquals(new ArrayList<Question>(), quizVide.getCinqQuestions(0));
		
		// Quiz avec assez de questions
		Quiz quiz = new Quiz(DIFFICULTE_VALIDE[0], NOMBRE_QUESTION_VALIDE[2],CATEGORIE_VALIDE[2],stockageTest);
		
		System.out.println(quiz.getListeQuestion());
		ArrayList<Question> cinqQuestions = new ArrayList<>();
		int nombreQuestion = quiz.getListeQuestion().size();
		for (int i = 0; i < nombreQuestion && i < 5; i++) {
			cinqQuestions.add(quiz.getListeQuestion().get(i));
		}
		assertEquals(cinqQuestions, quiz.getCinqQuestions(0), "5 premiere question du feedback.");
		
		cinqQuestions.clear();
		for (int i = 4; i < nombreQuestion && i < 9; i++) {
			cinqQuestions.add(quiz.getListeQuestion().get(i));
		}
		assertEquals(cinqQuestions, quiz.getCinqQuestions(1), "10 premiere question du feedback.");
	}
}
