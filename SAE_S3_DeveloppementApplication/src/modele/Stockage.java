package modele;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import vue.Main;
/** TODO comment class responsibility (SRP)
 * @author mateo.faussurier
 *
 */
public class Stockage implements Serializable{
	
	// objet ListeCategorie contenant les catégories du serveur
	ListeCategorie listeCategorie;
	
	// objet ListeQuestion contenant les questions du serveur
	ListeQuestion listeQuestion;
	
	Joueur joueur;
	
	/**
	 * Constructeur vide;
	 */
	public Stockage() {
		listeCategorie = new ListeCategorie();
		listeQuestion = new ListeQuestion();
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
	
	public String getPseudoJoueur() {
		return joueur.getPseudoJoueur();
	}
	
	public void setPseudoJoueur(String nouveauPseudo) {
		if(!nouveauPseudo.isEmpty() || !nouveauPseudo.isBlank() || nouveauPseudo != null) {
			Joueur joueur = new Joueur(nouveauPseudo);
		}
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
		if (question.getCategorieDeQuestion() != null && listeCategorie.elementEstDansListeCategorie(question.getCategorieDeQuestion().getIntituleCategorie())) {
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
			ArrayList<String> liste, String reponse, String feedBack, String concatenation) {
		boolean toutEstBon = false;
		if ( listeQuestion.modifierDifficulteQuestion(question, difficulte, concatenation) && listeQuestion.modifierListeReponsesFaussesQuestion(question, liste, concatenation) 
			 &&	listeQuestion.modifierReponseJusteQuestion(question, reponse, concatenation) && listeQuestion.modifierFeedBackQuestion(question, feedBack, concatenation) 
			 && listeQuestion.modifierCategorieDeQuestion(question, categorie) && listeQuestion.modifierIntituleQuestion(question, intitule, concatenation) ) {
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
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filePath));

            // Ignorer la première ligne du fichier CSV (en-tête).
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            // Parcourir les lignes du fichier CSV.
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                //permet de split a chaque point virgule sauf lorsque le point virgule est entouuré de guillemets
                String[] data = line.split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

                if (data.length >= 5) {  // Assurez-vous qu'il y a au moins 5 valeurs obligatoires
                    Categorie categorieCourante;

                    String categorieValue = data[0];
                    if (listeCategorie.elementEstDansListeCategorie(categorieValue.trim())) {
                        categorieCourante = listeCategorie.getElementListeCategorie(categorieValue.trim());
                    } else {
                        categorieCourante = new Categorie(categorieValue.trim());
                        listeCategorie.ajouterElementListeCategorie(categorieCourante);
                    }

                    // Vérifier qu'au moins une des valeurs obligatoires n'est pas vide.
                    if (!data[0].isEmpty() && !data[1].isEmpty() && !data[2].isEmpty() && !data[3].isEmpty()) {
                    	if (!data[4].isEmpty() || !data[5].isEmpty() || !data[6].isEmpty() || !data[7].isEmpty()) {
	                        // Créer une liste des réponses fausses.
                    		ArrayList<String> reponsesFausses = new ArrayList<>();
                    		reponsesFausses.add(getValeurJuste(data, 4));
	                        reponsesFausses.add(getValeurJuste(data, 5));
	                        reponsesFausses.add(getValeurJuste(data, 6));
	                        reponsesFausses.add(getValeurJuste(data, 7));
	                        
                    		//supprime les valeurs vide et celle qui ne contiennent que des espaces de la liste des réponses fausses
                    		reponsesFausses.removeIf(s -> s.trim().isEmpty());

	                        // Créer une nouvelle question.
	                        Question question = new Question(
	                        		getValeurJuste(data, 2),
	                                categorieCourante,
	                                parseIntOrDefault(data[1]),
	                                reponsesFausses,
	                                getValeurJuste(data, 3),
	                                getValeurJuste(data, 8)
	                        );
	
	                        // Ajouter la question à la liste des questions.
	                        listeQuestion.ajouterElementListeQuestion(question);
	                        estImporte = true;
                    	}
                    }
                }
            }



            // Indiquer que l'importation est réussie.
            return estImporte;

        } catch (FileNotFoundException e) {
            // Indiquer que l'importation a échoué.
            return estImporte;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    // Méthode pour obtenir une valeur sûre à partir d'un tableau
    private String getValeurJuste(String[] array, int index) {
        return (index >= 0 && index < array.length) ? array[index] : "";
    }

    // Méthode pour convertir une chaîne en entier ou retourner une valeur par défaut
    private int parseIntOrDefault(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0; // ou une autre valeur par défaut selon vos besoins
        }
    }
    
    public ArrayList<String> getListeReponsesOrdreAleatoire(String nomQuestionNomCategorie) {
    	return listeQuestion.getReponsesOrdreAleatoire(listeQuestion.getElementListeQuestion(nomQuestionNomCategorie));
    }
    
    /**
     * Cette méthode crée des objets de type Point et PointNom, ensuite elle les
     * sérialise dans un fichier dont le nom est passé en paramètre
     * @param nomFichier 
     * @param une chaîne contenant le nom du fichier à créer
     * @throws EchecSerialisationRestauration 
     */
    public void serialiser()throws EchecSerialisationRestauration  {
        // Création et écriture dans le fichier
    	try (FileOutputStream fileOut = new FileOutputStream("stockage.ser");
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

               // Sérialisation
               objectOut.writeObject(Main.stockage);

           } catch (Exception e) {
        	   throw new EchecSerialisationRestauration("Problème d'accès au fichier " + "stockage.ser" + ". Echec de la sérialisation.");
           }
    	
    }
    
    /**
     * Cette méthode lit le fichier dont le nom est passé en paramètre
     * et restaure l'objet stockage qu'il contient.
     * @param nomFichier 
     * @param une chaîne contenant le nom du fichier à consulter
     * @throws EchecSerialisationRestauration 
     */
    public void restaurer() throws EchecSerialisationRestauration {
        // déclaration du fichier et lecture dans le fichier
    	try (FileInputStream fileIn = new FileInputStream("stockage.ser");
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

               // Désérialisation
               Main.stockage = (Stockage) objectIn.readObject();

           } catch (Exception e) {
               e.printStackTrace();
           }

    }
    
    
    public boolean exportCSV(ArrayList<Question> listeQuestions) {
        boolean estExporte = false;

     // Nom du fichier et extension
        String fileName = "banque_de_question_exporte.csv";

        // Obtenez le répertoire de travail actuel du projet
        String projectPath = System.getProperty("user.dir");

        // Construisez le chemin complet du fichier CSV dans le répertoire du projet
        String filePath = Paths.get(projectPath, fileName).toString();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Écrire l'en-tête du fichier CSV
            writer.write("Catégorie;Niveau;Libellé;juste;faux1;faux2;faux3;faux4;feedback");
            writer.newLine();

            // Parcourir les questions dans la liste et écrire chaque ligne dans le fichier CSV
            for (Question question : listeQuestions) {

                // Construire la ligne CSV pour chaque question
                String line = String.format("%s;%d;%s;%s;%s;%s;%s;%s;%s",
                        question.getCategorieDeQuestion().getIntituleCategorie(),
                        question.getDifficulteQuestion(),
                        question.getIntituleQuestion(),
                        question.getReponseJusteQuestion(),
                        question.getReponsesFaussesQuestion().get(0),
                        question.getReponsesFaussesQuestion().size() > 1 ? question.getReponsesFaussesQuestion().get(1) : "",
                        question.getReponsesFaussesQuestion().size() > 2 ? question.getReponsesFaussesQuestion().get(2) : "",
                        question.getReponsesFaussesQuestion().size() > 3 ? question.getReponsesFaussesQuestion().get(3) : "",
                        question.getFeedBackQuestion());

                // Écrire la ligne dans le fichier
                writer.write(line);
                writer.newLine();
            }

            // Indiquer que l'exportation est réussie
            estExporte = true;

        } catch (IOException e) {
            // Gérer les exceptions liées à l'écriture dans le fichier
            e.printStackTrace();
        }

        return estExporte;
    }

}


