package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe Import permet d'importer des données depuis un fichier CSV dans une application Java.
 * Elle utilise les classes ListeCategorie, ListeQuestion, Categorie et Question pour structurer
 * les données importées.
 */
public class Import {

    /** Liste des catégories existantes. */
    private static ListeCategorie listeCategorie = new ListeCategorie();

    /** Liste des questions importées. */
    private static ListeQuestion listeQuestion = new ListeQuestion();

    /** Catégorie courante lors de l'importation. */
    private static Categorie categorieCourante;

    /**
     * La méthode principale qui est exécutée lors du lancement de l'application.
     * Elle importe les données à partir d'un fichier CSV et affiche un message en fonction du succès de l'importation.
     *
     * @param args Les arguments de la ligne de commande (non utilisés dans cet exemple).
     */
    public static void main(String[] args) {
        String filePath = "D:/BUT2/SAE-S3-DeveloppementApplication/questionsbasiques.csv";
        if (importCSV(filePath)) {
            System.out.print("L'import a été effectué avec succès !\n");
        } else {
            System.out.print("L'import n'a pas fonctionné !");
        }
    }

    /**
     * Importe les données depuis un fichier CSV.
     *
     * @param filePath Le chemin du fichier CSV à importer.
     * @return true si l'importation est réussie, false sinon.
     */
    private static boolean importCSV(String filePath) {
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

                // Vérifier si la catégorie existe déjà dans la liste.
                if (listeCategorie.elementEstDansListeCategorie(data[0])) {
                    categorieCourante = listeCategorie.getElementListeCategorie(data[0]);
                } else {
                    categorieCourante = new Categorie(data[0]);
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
            }

            // Fermer le scanner.
            scanner.close();

            // Indiquer que l'importation est réussie.
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // Indiquer que l'importation a échoué.
            return false;
        }
    }
}
