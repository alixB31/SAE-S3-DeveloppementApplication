package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/** TODO comment class responsibility (SRP)
 * @author mateo.faussurier
 *
 */
public class Stockage {
	
	// objet ListeCategorie contenant les catégories du serveur
	ListeCategorie listeCategorie;
	
	// objet ListeQuestion contenant les questions du serveur
	ListeQuestion listeQuestion;
	
	// objet ListeJouteur contenant les joueurs enregistrés sur le serveur
	ListeJoueur listeJoueur;
	
	/**
	 * Constructeur vide;
	 */
	public Stockage() {
		listeCategorie = new ListeCategorie();
		listeQuestion = new ListeQuestion();
		listeJoueur = new ListeJoueur();
	}
	
	/** 
	 * Retourne la liste des catégories contenant les catégories.
	 * @return listeCategorie la liste des catégories, c'est un objet de
	 * type HashMap
	 */
	public HashMap getListeCategorie() {
		return listeCategorie.getListeCategorie();
	}
	
	/**
	 * Retourne la liste des questions.
	 * @return listeQuestion la liste des questions.
	 */
	public HashMap getListeQuestion() {
		return listeQuestion.getListeQuestion();
	}
	
	/**
	 * Ajoute la catégorie en paramètre dans la liste des catégories.
	 * @param categorie la catégorie à ajouter.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	public boolean ajouterCategorie(Categorie categorie) {
	    return listeCategorie.ajouterElementListeCategorie(categorie);
	}
	/**
	 * Supprime une catégorie de la liste des catégories.
	 * @param categorie la catégorie à supprimer.
	 * @return true si la catégorie est supprimée, false sinon.
	 */
	public boolean supprimerElementListeCategorie(Categorie categorie) {
		boolean toutEstSupprime = true;
//		toutEstSupprime &= listeQuestion.supprimerQuestionParCategorie(categorie);
		toutEstSupprime &= listeCategorie.supprimerElementListeCategorie(categorie.getIntituleCategorie());
		return toutEstSupprime;
	}

	
	/**
	 * Modifie la catégorie en paramètre en remplacent l'ancien intitule par le nouveau
	 * @param ancienneCategorie la catégorie à modifier
	 * @param nouveauIntitule le nouvel intitulé de la catégorie
	 * @return true si la modification a réussi, false sinon.
	 */
	public boolean modifierElementListeCategorie(Categorie ancienneCategorie, String nouveauIntitule) {
		
		boolean estModifiee = true;
		if (!listeCategorie.elementEstDansListeCategorie(nouveauIntitule)
				&& !nouveauIntitule.isBlank() && !nouveauIntitule.isEmpty()
				&& !ancienneCategorie.getIntituleCategorie().equals("Général")) {
			Categorie categorie = new Categorie(nouveauIntitule);
	    	estModifiee &= listeCategorie.ajouterElementListeCategorie(categorie);
	    	estModifiee &= listeQuestion.modifierQuestionParCategorie(ancienneCategorie, categorie);
	    	estModifiee &= listeCategorie.supprimerElementListeCategorie(ancienneCategorie.getIntituleCategorie());
		} else {
			estModifiee = false;
		}
		return estModifiee;
	}
	
	/**
	 * Ajoute la question  en paramètre dans la liste des questions
	 * @param question la question à modifier.
	 * @return true si l'ajout a réussi, false sinon.
	 */
	public boolean ajouterQuestion(Question question) {
		boolean estAjoutee = false;
		if (listeCategorie.elementEstDansListeCategorie(question.getCategorieDeQuestion().getIntituleCategorie())) {
			estAjoutee = listeQuestion.ajouterElementListeQuestion(question);
		}
		return estAjoutee;
	}
	
	/**
	 * Retourne la liste des questions ayant la même catégorie que celle en
	 * paramètre.
	 * @param categorie la catégorie qui filtre les questions.
	 * @return ListeQuestion, la liste des questions correspondantes.
	 */
	public ArrayList<Question> listeQuestionParCategorie(Categorie categorie) {
	    return listeQuestion.listeQuestionParCategorie(categorie);
	}
	
//	public boolean modifierIntituleQuestion(Question question, String intitule) {
//		return listeQuestion.modifierIntituleQuestion(question, intitule);
//	}
	
	public boolean modifierCategorieQuestion(Question question, Categorie categorie) {
		boolean estModifiee = false;
		if (listeCategorie.elementEstDansListeCategorie(categorie.getIntituleCategorie())) {
			estModifiee = listeQuestion.modifierCategorieDeQuestion(question, categorie);
		}
		return estModifiee;
	}
//	
//	public boolean modifierDifficulteQuestion(Question question, int difficulte) {
//		return listeQuestion.modifierDifficulteQuestion(question, difficulte);
//	}
//	
//	public boolean modifierFeedBackQuestion(Question question, String feedBack) {
//		return listeQuestion.modifierFeedBackQuestion(question, feedBack);
//	}
//	
//	public boolean modifierReponseJusteQuestion(Question question, String reponseJuste) {
//		return listeQuestion.modifierReponseJusteQuestion(question, reponseJuste);
//	}
//	public boolean ajouterReponseFausseQuestion(Question question, String reponseFausse) {
//		return listeQuestion.ajouterReponseFausse(question, reponseFausse);
//	}
//	
	public boolean supprimerElementListeQuestion(String intitule) {
		return listeQuestion.supprimerElementListeQuestion(intitule);
	}
//	
//	public boolean supprimerReponseFausseQuestion(Question question, String ancienneReponseFausse) {
//		return listeQuestion.supprimerReponseFausse(question, ancienneReponseFausse);
//	}
	
	
	public boolean modifierQuestion(Question question, String intitule, Categorie categorie, int difficulte, 
			ArrayList<String> liste, String reponse, String feedBack) {
		boolean toutEstBon = false;
		if (listeQuestion.modifierIntituleQuestion(question, intitule) && listeQuestion.modifierDifficulteQuestion(question, difficulte)
			 && listeQuestion.modifierListeReponsesFaussesQuestion(question, liste) &&	listeQuestion.modifierReponseJusteQuestion(question, reponse)
			 && listeQuestion.modifierFeedBackQuestion(question, feedBack)) {
			toutEstBon = true;
		}
		return toutEstBon;
		
	}
	
	public ArrayList<Question> listeQuestionFiltreDifficulteCategorieTaille(Quiz quiz){
		return listeQuestion.listeQuestionFiltreDifficulteCategorieTaille(quiz);
	}
	
	/**
     * Importe les données depuis un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV à importer.
     * @return true si l'importation est réussie, false sinon.
     */
    public boolean importCSV(String filePath) {
    	boolean estImporte = false;
        try {
            Scanner scanner = new Scanner(new File(filePath));

            // Ignorer la première ligne du fichier CSV (en-tête).
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Parcourir les lignes du fichier CSV.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");

                Categorie categorieCourante;
                
				// Vérifier si la catégorie existe déjà dans la liste.
                if (listeCategorie.elementEstDansListeCategorie(data[0])) {
                    categorieCourante = listeCategorie.getElementListeCategorie(data[0]);
                } else {
                    categorieCourante = new Categorie(data[0]);
                    listeCategorie.ajouterElementListeCategorie(categorieCourante);
                }

                // Créer une liste des réponses fausses.
                ArrayList<String> reponsesFausses = new ArrayList<>();
                reponsesFausses.add(data[4]);
                reponsesFausses.add(data[5]);
                reponsesFausses.add(data[6]);
                reponsesFausses.add(data[7]);

                // Créer une nouvelle question.
                Question question = new Question(
                        data[2],
                        categorieCourante,
                        Integer.parseInt(data[1]),
                        reponsesFausses,
                        data[3],
                        data[8]
                );

                // Ajouter la question à la liste des questions.
                listeQuestion.ajouterElementListeQuestion(question);
                estImporte = true;
            }

            // Fermer le scanner.
            scanner.close();

            // Indiquer que l'importation est réussie.
            return estImporte;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Indiquer que l'importation a échoué.
            return estImporte;
        }
    }
}
