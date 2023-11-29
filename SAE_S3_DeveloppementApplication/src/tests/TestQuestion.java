package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modele.Question;
import modele.Categorie;
import modele.Stockage;

class TestQuestion {
<<<<<<< Updated upstream
	/* Liste contenant des questions de tests */
	private ArrayList<Question> jeuDeTestQuestionValide;  

	/* Liste contenant des categories de tests */
	private ArrayList<Categorie> jeuDeTest;

	/* Objet Stockage servant pour les tests */
	private Stockage stockageTest;

	/* liste de reponse fausse */
	private ArrayList<String> listeReponsesFausses = new ArrayList<>();

	/* liste trop longue de reponse fausse */
	private ArrayList<String> listeReponsesFaussesTropLongue = new ArrayList<>();

	/* Liste de réponses justes valides */
	private ArrayList<String> listeReponseJusteValide = new ArrayList<>();

	/* Liste de réponses justes valides */
	private final String[] LISTE_REPONSES_JUSTES_VALIDES = {"unique", "    rouge    ", "01234567890123456789",
			"j", ")à_(è&çà'", " b ", "JAVA", "identique"};

	/* Liste de réponses justes invalides */
	private final String[] LISTE_REPONSES_JUSTES_INVALIDES = {"       ", "", null};

	/* Liste de réponses fausses valides */
	private final String[] LISTE_REPONSES_FAUSSES_VALIDES = {"php", "entre des '' ", "5", "   toujours",
			"jamais   ", "     le crochet    ", "rhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",};

	/* Liste de liste de réponses fausses */
	private final String[][] LISTE_DE_LISTES_DE_REPONSES_FAUSSES_VALIDES = {{"java", "gijsdfgmfdughqgljhsehqklgnmiouyhJRBHFG BEG§OILHG",
	"Franchement la raclette en sah"}, {"une seule réponse fausses, à oui ça suffit."},
			{"Le poulet c'est trop bon", "C'est la Java? OUUUUUUU"}};

	/* Liste de liste de réponses fausses invalides */
	private final String[][] LISTE_DE_LISTES_DE_REPONSES_FAUSSES_INVALIDES = {{"un", "deux", null}, {"", "la première est vide donc ca marchra pas",
		"franchement à ça"	}, {null, "la ca va", "mais la première est null", "donc ca va pas"},
			{"            ", "              ", "Beaucoup de blanc"},{"indentique", "identique", "Oui"}, {"unique", "c'est la même que la réponse juste"}};
	
	/* Liste de réponses fausses invalides */
	private final String[] LISTE_REPONSES_FAUSSES_INVALIDES = {"       ", "", null, "identique"};

	/* Liste intitulés valides de question */
	private final String[] LISTE_INTITULES_QUESTION_VALIDES = {"Comment ça va?", "Java existe depuis combien de temps?",
			"Selectionner les assertions vraies.             ", "         Combien il y a de point?", "         Une question        très      espacée",
			" b ", "  , ", "?"};

	/* Liste intitulés invalides question */
	private final String[] LISTE_INTITULES_QUESTION_INVALIDES = {"", "      ", null};

	/* Liste difficultes valides */
	private final int[] LISTE_DIFFICULTES_VALIDES = {1, 2, 3};

	/* Liste difficultes invalides */
	private final int[] LISTE_DIFFICULTES_INVALIDES = {0, 4, 5, 50, 100, 1000000000, -1, -2, -3, -4, -100};

	/* Liste de feedback valides */
	private final String[] LISTE_FEEDBACK_VALIDES = {"Pour comprendre la réponse il fallait penser à manger.",
			"La priorité des opérations se font dans un ordre bien précis. Il en est de même sur java, cet ordre peut être modifier en mettant des parenthèse.",
			"   Très court   ", "but   ", "       aller Kante", " Beaucoup de chiffre  1010101011111001110101011001010010010110110101011000011001010010010101"};

	/* Liste de feeedback invalides */
	private final String[] LISTE_FEEDBACK_INVALIDES = {null};

	/* Liste des catégories Invalides */
	private final Categorie[] LISTE_CATEGORIES_INVALIDES = {null, new Categorie("inexistante")};

	/* Liste de catégories valides */
	private final Categorie[] LISTE_CATEGORIES_VALIDES = {new Categorie("France"), new Categorie("Géographie"), new Categorie("3000")};
	
	/*  */
	private ArrayList<String> listeReponsesFaussesInvalides = new ArrayList<>();

	@BeforeEach
	void setUp() throws Exception {
		//Jeu de catégories
		jeuDeTest = new ArrayList<>(5);
		jeuDeTestQuestionValide = new ArrayList<>(5);
		jeuDeTest.add(new Categorie("Java"));
		jeuDeTest.add(new Categorie("Math"));
		jeuDeTest.add(new Categorie("Général"));
		jeuDeTest.add(new Categorie("categoriePasDansListe"));
=======
	/*Liste contenant des questions de tests*/
    private ArrayList<Question> jeuxDeTestQuestion;  
    
    /*Liste contenant des categories de tests*/
    private ArrayList<Categorie> jeuxDeTest;
    
    private Stockage stockageTest = null;
    
    /* liste de reponse fausse */
    private ArrayList<String> listeReponsesFausses = new ArrayList<>();
    
    /* liste trop longue de reponse fausse */
    private ArrayList<String> listeReponsesFaussesTropLongue = new ArrayList<>();
    
    private ArrayList<String> listeReponseJusteValide = new ArrayList<>();
    
    /* Liste de réponses justes valides */
    private final String[] LISTE_REPONSES_JUSTES_VALIDES = {"unique", "    rouge    ", "01234567890123456789",
    		"j", ")à_(è&çà'", " b ", "JAVA", "identique"};
    
    /* Liste de réponses justes invalides */
    private final String[] LISTE_REPONSES_JUSTES_INVALIDES = {"       ", "", null};
    
    /* Liste de réponses fausses valides */
    private final String[] LISTE_REPONSES_FAUSSES_VALIDES = {"php", "entre des '' ", "5", "   toujours",
    		"jamais   ", "     le crochet    ", "rhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",};
    
    /* Liste de réponses fausses invalides */
    private final String[] LISTE_REPONSES_FAUSSES_INVALIDES = {"       ", "", null, "identique"};
    
    /* Liste intitulés valides de question */
    private final String[] LISTE_INTITULES_QUESTION_VALIDES = {"Comment ça va?", "Java existe depuis combien de temps?",
    		"Selectionner les assertions vraies.             ", "         Combien il y a de point?", "         Une question        très      espacée",
    		" b ", "  , ", "?"};
    
    /* Liste intitulés invalides question */
    private final String[] LISTE_INTITULES_QUESTION_INVALIDES = {"", "      ", null};
    
    /* Liste difficultes valides */
    private final int[] LISTE_DIFFICULTES_VALIDES = {1, 2, 3};
    
    /* Liste difficultes invalides */
    private final int[] LISTE_DIFFICULTES_INVALIDES = {0, 4, 5, 50, 100, 1000000000, -1, -2, -3, -4, -100};
    
    /* Liste de feedback valides */
    private final String[] LISTE_FEEDBACK_VALIDES = {"Pour comprendre la réponse il fallait penser à manger.",
    		"La priorité des opérations se font dans un ordre bien précis. Il en est de même sur java, cet ordre peut être modifier en mettant des parenthèse.",
    		"   Très court   ", "but   ", "       aller Kante", " Beaucoup de chiffre  1010101011111001110101011001010010010110110101011000011001010010010101"};
    
    /* Liste de feeedback invalides */
    private final String[] LISTE_FEEDBACK_INVALIDES = {"", null, "       "};
    
	@BeforeEach
	void setUp() throws Exception {
		//Jeu de catégories
		jeuxDeTest = new ArrayList<>(5);
		jeuxDeTestQuestion = new ArrayList<>(5);
		jeuxDeTest.add(new Categorie("Java"));
		jeuxDeTest.add(new Categorie("Math"));
		jeuxDeTest.add(new Categorie("Général"));
		jeuxDeTest.add(new Categorie("categoriePasDansListe"));
>>>>>>> Stashed changes
		
		//Jeu de réponses fausses 
		listeReponsesFausses.add("2");
		listeReponsesFausses.add("7");
		listeReponsesFausses.add("10");
		listeReponsesFaussesTropLongue.add("1");
		listeReponsesFaussesTropLongue.add("3");
		listeReponsesFaussesTropLongue.add("2");
		listeReponsesFaussesTropLongue.add("4");
		listeReponsesFaussesTropLongue.add("5");
<<<<<<< Updated upstream

		//Jeu de réponses
		for (int i = 0; i < LISTE_REPONSES_FAUSSES_INVALIDES.length; i++) {
			listeReponsesFaussesInvalides.add(LISTE_REPONSES_FAUSSES_INVALIDES[i]);
		}

		stockageTest = new Stockage();

		// Ajout des catégories
		stockageTest.ajouterCategorie(jeuDeTest.get(0));
		stockageTest.ajouterCategorie(jeuDeTest.get(1));
		stockageTest.ajouterCategorie(jeuDeTest.get(2));

		//Jeu de question
		jeuDeTestQuestionValide.add(new Question("Combien y a t'il de nombre boucle en java ?",jeuDeTest.get(0),1,listeReponsesFausses,"3","il y a 3 boucles"));
		jeuDeTestQuestionValide.add(new Question("3+2",jeuDeTest.get(1),1,listeReponsesFausses,"5","3 + 2 = 5"));
		jeuDeTestQuestionValide.add(new Question("3+1",jeuDeTest.get(1),1,listeReponsesFaussesTropLongue,"4","3 + 1 = 4"));
		jeuDeTestQuestionValide.add(new Question("3+3",jeuDeTest.get(2),1,listeReponsesFausses,"6","3 + 3 = 6"));

		stockageTest.getListeCategorie().put("Général",new Categorie("Général"));
=======
		
		//Jeu de question
		jeuxDeTestQuestion.add(new Question("Combien y a t'il de nombre boucle en java ?",jeuxDeTest.get(0),1,listeReponsesFausses,"3","il y a 3 boucles"));
		jeuxDeTestQuestion.add(new Question("3+2",jeuxDeTest.get(1),1,listeReponsesFausses,"5","3 + 2 = 5"));
		jeuxDeTestQuestion.add(new Question("3+1",jeuxDeTest.get(1),1,listeReponsesFaussesTropLongue,"4","3 + 1 = 4"));
		jeuxDeTestQuestion.add(new Question("3+3",jeuxDeTest.get(2),1,listeReponsesFausses,"6","3 + 3 = 6"));
		stockageTest = new Stockage();
		stockageTest.ajouterCategorie(jeuxDeTest.get(0));
		stockageTest.ajouterCategorie(jeuxDeTest.get(1));
		stockageTest.ajouterCategorie(jeuxDeTest.get(2));
		
		
>>>>>>> Stashed changes
		// jeu de test de questions
	}

	@AfterEach
	void clean() {
		stockageTest.getListeCategorie().clear();
		stockageTest.getListeQuestion().clear();
	}

	@Test
	void testAjouterQuestion() {
		// Vérification des caractères valides d'une question
		HashMap<String , Question> mapVoulu = new HashMap<>();

		for (int i = 0; i < jeuDeTestQuestionValide.size(); i++) {
			assertTrue(stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(i)), "Ajout de la question " + jeuDeTestQuestionValide.get(i).getIntituleQuestion());
			mapVoulu.put(jeuDeTestQuestionValide.get(i).getIntituleQuestion() + jeuDeTestQuestionValide.get(i).getCategorieDeQuestion().getIntituleCategorie(), jeuDeTestQuestionValide.get(i));
		}

		// On vérifie si l'ensemble de question final est correct
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification de l'ensemble des questions.");

		// Vérification des ajouts invalides
		// Ajout d'une question déjà présente dans la liste des questions
		for (int i = 0; i < jeuDeTestQuestionValide.size(); i++) {
			assertFalse(stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(i)), "Ajout d'une question déjà existante. question : " + jeuDeTestQuestionValide.get(i).getIntituleQuestion());
		}
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts de questions déjà existantes.");

		// Ajout de questions avec un intitulé invalide.
		for (int i = 0; i < LISTE_INTITULES_QUESTION_INVALIDES.length; i++) {
			assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_INVALIDES[i], jeuDeTest.get(i),LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec intitulé invalide.");
		}

		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec l'intitulé faux.");

		// Ajout d'une question avec une catégorie non existante dans la liste des categorie.
		assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], new Categorie("Le connemara"),LISTE_DIFFICULTES_INVALIDES[0], listeReponsesFausses,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec une catégorie inexistante dans la liste des catégories.");
		assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], null,LISTE_DIFFICULTES_INVALIDES[0], listeReponsesFausses,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec une catégorie null");
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec des catégories invalides.");


		// Ajout de questions avec un niveau de difficulté invalide.
		for (int i = 0; i < LISTE_DIFFICULTES_INVALIDES.length; i++) {
			assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], jeuDeTest.get(0),LISTE_DIFFICULTES_INVALIDES[i], listeReponsesFausses,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec niveau de difficulté invalide.");
		}
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec une difficulté fausse.");


		// Ajout de questions avec des listes de réponses fausses invalides.
		for (int i = 0; i<LISTE_REPONSES_FAUSSES_INVALIDES.length; i++) {
			listeReponsesFaussesInvalides.add(LISTE_REPONSES_FAUSSES_INVALIDES[i]);
			assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], jeuDeTest.get(0),LISTE_DIFFICULTES_INVALIDES[0], listeReponsesFaussesInvalides,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec une liste de réponses fausses invalide.");
			listeReponsesFaussesInvalides.remove(LISTE_REPONSES_FAUSSES_INVALIDES[i]);
		}
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec des réponses fausses invalides.");

		// Ajout de questions avec une réponse juste invalide.
		for (int i = 0; i < LISTE_REPONSES_JUSTES_INVALIDES.length; i++) {
			assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], jeuDeTest.get(0),LISTE_DIFFICULTES_INVALIDES[0], listeReponsesFausses,LISTE_REPONSES_JUSTES_INVALIDES[i],LISTE_FEEDBACK_VALIDES[0])), "Ajout de questions avec la réponse juste invalide.");
		}
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec des réponses justes invalides.");

		// Ajout de questions avec un feedback invalide.
		for (int i = 0; i < LISTE_FEEDBACK_INVALIDES.length; i++) {
			assertFalse(stockageTest.ajouterQuestion(new Question(LISTE_INTITULES_QUESTION_VALIDES[0], jeuDeTest.get(0),LISTE_DIFFICULTES_INVALIDES[0], listeReponsesFausses,LISTE_REPONSES_JUSTES_VALIDES[0],LISTE_FEEDBACK_INVALIDES[i])), "Ajout de questions avec un feedback invalide.");
		}
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification après des ajouts avec des feedback invalides.");
	}

	@Test
	void testListeQuestionParCategorie() {
		for (int i = 0; i < jeuDeTestQuestionValide.size();i++) {
			stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(i));
		}
		ArrayList<Question> listeCorrecte = new ArrayList<>();
		listeCorrecte.add(jeuDeTestQuestionValide.get(1));
		listeCorrecte.add(jeuDeTestQuestionValide.get(2));

		assertEquals(listeCorrecte, stockageTest.listeQuestionParCategorie(jeuDeTest.get(1)), "Vérification de la récupération d'une liste de questions par rapport à une catégorie.");
	}

	@Test
	void testSupprimerQuestion() {

		Map<String, Question> mapVoulu = new HashMap<>();

		// Suppression d'une question existante dans la liste des questions avec qu'une question
		assertEquals(stockageTest.getListeQuestion(), mapVoulu, "Vérification de l'initialisation des enesembles pour les testes.");
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(0));
		assertTrue(stockageTest.supprimerElementListeQuestion("Combien y a t'il de nombre boucle en java ?"
				+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()), "Suppression d'un élément présent dans la liste des questions.");
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "Vérification de la liste après suppression d'un élément présent.");

		// Suppression d'un éléemnt existant dans une liste avec d'autres élément
		mapVoulu.put("3+2"+ jeuDeTest.get(1).getIntituleCategorie(),jeuDeTestQuestionValide.get(1));
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(1));
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(0));
		stockageTest.supprimerElementListeQuestion(jeuDeTestQuestionValide.get(0)
				.getIntituleQuestion()+jeuDeTestQuestionValide.get(0)
				.getCategorieDeQuestion().getIntituleCategorie());
		assertEquals(stockageTest.getListeQuestion(),mapVoulu, "");

		//cas ou la question n'existe pas 
		assertFalse(stockageTest.supprimerElementListeQuestion("Question inexistante"));
	}
<<<<<<< Updated upstream

=======
	
//	@Test
//	void testModifierIntituleQuestion() {
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.modifierQuestion(jeuxDeTestQuestion.get(1),"Que fais 2 +3 ?");
//		assertEquals(jeuxDeTestQuestion.get(1).getIntituleQuestion(),"Que fais 2 +3 ?");
//		//cas intitule vide
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
//		assertFalse(stockageTest.modifierQuestion(jeuxDeTestQuestion.get(0),""));
//		
//		//cas ou la question n'existe pas 
//		assertFalse(stockageTest.modifierQuestion(jeuxDeTestQuestion.get(2),"AAAAA"));
//
//	}
	
>>>>>>> Stashed changes
	@Test
	void testModifierCategorieQuestion() {
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(1));

		//cas ou la nouvelle categorie n'existe pas 
		assertFalse(stockageTest.modifierCategorieQuestion(jeuDeTestQuestionValide.get(1),jeuDeTest.get(3)));

		// Vérification, si la catégorie est réellement restée inchangée.
		assertEquals(jeuDeTestQuestionValide.get(1).getCategorieDeQuestion(), jeuDeTest.get(1), "Verification " 
				+ "après tentative de modification de la catégorie d'une question par une catégorie invalide");

		// avec une catégorie null
		assertFalse(stockageTest.modifierCategorieQuestion(jeuDeTestQuestionValide.get(1),null), "Tentatice de modification de la catégorie par une catégorie null.");

		// Modification d'une question vers une catégorie contenant une question identique
		stockageTest.ajouterQuestion(new Question("3+2",jeuDeTest.get(2),1,listeReponsesFausses,"5","3 + 2 = 5"));
		assertFalse(stockageTest.modifierCategorieQuestion((Question)stockageTest.getListeQuestion()
				.get("3+2"+jeuDeTest.get(2).getIntituleCategorie()), jeuDeTest.get(1)), "Tentative de "
						+ "migration d'une question vers une catégorie avec une question identique.");

		// Modification d'une question valide qui n'est pas dans la liste des questions de l'objet Stockage.
		assertFalse(stockageTest.modifierCategorieQuestion(new Question("pas dans le stockage", jeuDeTest.get(2), 2, listeReponsesFausses, "vrai", ""), jeuDeTest.get(0)),
				"Tentative de modification de la catégorie d'une question qui n'est pas présente dans la liste des questions.");
	}

<<<<<<< Updated upstream
	@Test
	void testmodifierQuestion() {
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(0));
		// Modification d'une question avec différentes difficultés valides
		for (int i = 0; i< LISTE_DIFFICULTES_VALIDES.length; i++) {
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[i], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la difficulté pour la difficulté : " + LISTE_DIFFICULTES_VALIDES[i]);
		}

		// Modification d'une question avec différents intitulés valides
		for (int i = 0; i< LISTE_INTITULES_QUESTION_VALIDES.length; i++) {
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					LISTE_INTITULES_QUESTION_VALIDES[i],
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de l'intitulé de la question par des intitulés valides");
		}
		// Modification d'une question avec différentes catégories valides
//		
		for (int i = 0; i < LISTE_CATEGORIES_VALIDES.length;i++){
			stockageTest.ajouterCategorie(LISTE_CATEGORIES_VALIDES[i]);
		}	
		for (int i = 0; i <LISTE_CATEGORIES_VALIDES.length; i++) {
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					LISTE_CATEGORIES_VALIDES[i],
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la catégories d'une question par des catégories valides " + LISTE_CATEGORIES_VALIDES[i].getIntituleCategorie());
			stockageTest.postModificationCategorie();
		}

		// Modification d'une question avec différentes liste de réponses fausses valides
		ArrayList<String> listeDeReponsesFaussesValides = new ArrayList<>();
		for (int i = 0; i <LISTE_DE_LISTES_DE_REPONSES_FAUSSES_VALIDES.length; i++) {
			// Initialisation des ArrayList de réponses fausses valides
			listeDeReponsesFaussesValides.clear();
			for (int j = 0; j<LISTE_DE_LISTES_DE_REPONSES_FAUSSES_VALIDES[i].length; j++) {
				listeDeReponsesFaussesValides.add(LISTE_DE_LISTES_DE_REPONSES_FAUSSES_VALIDES[i][j]);
			}
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0],  listeDeReponsesFaussesValides,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la liste des réponses fausses d'une question par des listes de réponses fausses valides");
		}

		// Modification d'une question avec différentes réponses justes valides
		for (int i = 0; i <LISTE_REPONSES_JUSTES_VALIDES.length; i++) {
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[i], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la réponse juste d'une question par une réponse juste valide");
		}

		// Modification d'une question avec différents feedback valides
		for (int i = 0; i <LISTE_FEEDBACK_VALIDES.length; i++) {
			assertTrue(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[i],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification du feedback de la question par des feedback valides");
		}

		// INVALIDES
		// Modification d'une question avec différentes difficultés invalides
		for (int i = 0; i< LISTE_DIFFICULTES_INVALIDES.length; i++) {
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_INVALIDES[i], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la difficulté pour la difficulté : " + LISTE_DIFFICULTES_INVALIDES[i]);
		}

		// Modification d'une question avec différents intitulés invalides
		for (int i = 0; i< LISTE_INTITULES_QUESTION_INVALIDES.length; i++) {
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					LISTE_INTITULES_QUESTION_INVALIDES[i],
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de l'intitulé de la question par des intitulés invalides");
		}
		// Modification d'une question avec différentes catégories invalides
		for (int i = 0; i <LISTE_CATEGORIES_INVALIDES.length; i++) {
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					LISTE_CATEGORIES_INVALIDES[i],
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la catégories d'une question par des catégories invalides");
		}
		
		// Modification d'une question avec différentes liste de réponses fausses invalides
		ArrayList<String> listeDeReponsesFaussesInvalides = new ArrayList<>();
		for (int i = 0; i <LISTE_DE_LISTES_DE_REPONSES_FAUSSES_INVALIDES.length; i++) {
			// Initialisation des ArrayList de réponses fausses invalides
			listeDeReponsesFaussesValides.clear();
			for (int j = 0; j<LISTE_DE_LISTES_DE_REPONSES_FAUSSES_INVALIDES[i].length; j++) {
				listeDeReponsesFaussesInvalides.add(LISTE_DE_LISTES_DE_REPONSES_FAUSSES_INVALIDES[i][j]);
			}
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0],  listeDeReponsesFaussesInvalides,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la liste des réponses fausses d'une question par des listes de réponses fausses invalides");
		}

		// Modification d'une question avec différentes réponses justes invalides
		for (int i = 0; i <LISTE_REPONSES_JUSTES_INVALIDES.length; i++) {
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_INVALIDES[i], LISTE_FEEDBACK_VALIDES[0],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification de la réponse juste d'une question par une réponse juste invalide");
		}

		// Modification d'une question avec différents feedback invalides
		for (int i = 0; i <LISTE_FEEDBACK_INVALIDES.length; i++) {
			assertFalse(stockageTest.modifierQuestion(jeuDeTestQuestionValide.get(0),
					jeuDeTestQuestionValide.get(0).getIntituleQuestion(),
					jeuDeTestQuestionValide.get(0).getCategorieDeQuestion(),
					LISTE_DIFFICULTES_VALIDES[0], listeReponsesFausses ,
					LISTE_REPONSES_JUSTES_VALIDES[0], LISTE_FEEDBACK_INVALIDES[i],
					jeuDeTestQuestionValide.get(0).getIntituleQuestion()
					+jeuDeTestQuestionValide.get(0).getCategorieDeQuestion().getIntituleCategorie()),
					"Modification du feedback de la question par des feedback invalides");
		}

	}
	@Test
	void testSupprimerReponseFausseQuestion() {
		ArrayList arrayVoulu = new ArrayList<>();
		arrayVoulu.add("7");
		arrayVoulu.add("10");
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(1));
		assertTrue(stockageTest.supprimerReponseFausseQuestion(jeuDeTestQuestionValide.get(1),"2"),
				"Suppression d'une réponse fausse.");
		assertEquals(jeuDeTestQuestionValide.get(1).getReponsesFaussesQuestion(),arrayVoulu,
				"Vérification de la suppression.");
		
		ArrayList arrayVoulu2 = new ArrayList<>();
		arrayVoulu2.add("2");
		arrayVoulu2.add("10");
		
		
		
		jeuDeTestQuestionValide.get(0).setReponsesFaussesQuestion(new ArrayList<String>() {{add("2");add("7");add("10");}});
		stockageTest.ajouterQuestion(jeuDeTestQuestionValide.get(0));
		assertTrue(stockageTest.supprimerReponseFausseQuestion(jeuDeTestQuestionValide.get(0),"7"));
		assertEquals(jeuDeTestQuestionValide.get(0).getReponsesFaussesQuestion(),arrayVoulu2);
		
		//suppresion jusqu'a qu'il n'y ai plus de reponses fausses
		stockageTest.supprimerReponseFausseQuestion(jeuDeTestQuestionValide.get(0),"2");
		assertFalse(stockageTest.supprimerReponseFausseQuestion(jeuDeTestQuestionValide.get(0),"10"));
	}
=======
//	@Test
//	void testModifierDifficulteQuestion() {
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(1),3);
//		assertEquals(jeuxDeTestQuestion.get(1).getDifficulteQuestion(),3);
//		//cas ou difficultes pas valide
//		assertFalse(stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(1),5));
//		//cas ou la question n'existe pas 
//		assertFalse(stockageTest.modifierDifficulteQuestion(jeuxDeTestQuestion.get(0),2));
//
//	}
//	
//	@Test
//	void testModifierFeedBackQuestion() {
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(1),"le résultat est 5");
//		assertEquals(jeuxDeTestQuestion.get(1).getFeedBackQuestion(),"le résultat est 5");
//		//cas ou feedback vide
//		assertFalse(stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(1),""));
//		//cas ou la question n'existe pas 
//		assertFalse(stockageTest.modifierFeedBackQuestion(jeuxDeTestQuestion.get(3),"2"));
//
//	}
//	
//	@Test
//	void testModifierReponseJusteQuestion() {
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(1),"55");
//		assertEquals(jeuxDeTestQuestion.get(1).getReponseJusteQuestion(),"55");
//		//cas ou reponse vide 
//		assertFalse(stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(1),""));
//		//cas ou la question n'existe pas 
//		assertFalse(stockageTest.modifierReponseJusteQuestion(jeuxDeTestQuestion.get(3),"55"));
//		
//	}
//	
//	@Test
//	void testAjouterReponseFausseQuestion() {
//		ArrayList arrayVoulu = new ArrayList<>();
//		arrayVoulu.add("2");
//		arrayVoulu.add("7");
//		arrayVoulu.add("10");
//		arrayVoulu.add("55");
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(1),"55");
//		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
//		//cas avec plus de 4 reponses fausses
//		assertFalse(stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(1),"trop long"));
//		//cas ou la nouvelle reponses fausses est fausses
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
//		assertFalse(stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(0),""));
//	}
//	
//	@Test
//	void testSupprimerReponseFausseQuestion() {
//		ArrayList arrayVoulu = new ArrayList<>();
//		arrayVoulu.add("7");
//		arrayVoulu.add("10");
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(1),"2");
//		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
//		
//		ArrayList arrayVoulu2 = new ArrayList<>();
//		stockageTest.ajouterReponseFausseQuestion(jeuxDeTestQuestion.get(0),"2");
//		arrayVoulu2.add("10");
//		arrayVoulu2.add("2");
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(0));
//		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"7");
//		assertEquals(jeuxDeTestQuestion.get(0).getReponsesFaussesQuestion(),arrayVoulu2);
//		
//		//suppresion jusqu'a qu'il n'y ai plus de reponses fausses
//		stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"2");
//		assertFalse(stockageTest.supprimerReponseFausseQuestion(jeuxDeTestQuestion.get(0),"10"));
//	}
//	
//	@Test
//	void testModifierListeReponseFausseQuestion() {
//		ArrayList arrayVoulu = new ArrayList<>();
//		arrayVoulu.add("2000");
//		arrayVoulu.add("7");
//		arrayVoulu.add("10");
//		stockageTest.ajouterQuestion(jeuxDeTestQuestion.get(1));
//		stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"2","2000");
//		assertEquals(jeuxDeTestQuestion.get(1).getReponsesFaussesQuestion(),arrayVoulu);
//		// cas ou on modifie par la meme reponse
//		assertTrue(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"7","7"));
//		// cas ou on modifie par une reponse vide
//		assertFalse(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"7",""));
//		// cas ou on modifie une reponse inexistante
//		assertFalse(stockageTest.modifierListeReponseFausseQuestion(jeuxDeTestQuestion.get(1),"50","25"));
//		
//		
//	}
	
	
>>>>>>> Stashed changes
}
