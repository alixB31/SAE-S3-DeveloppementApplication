/*
 * Quiz.java									16 nov. 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */
package modele;

import java.util.ArrayList;

/**
 * Classe regroupant l'ensemble des methodes servant a la gestion d'un quiz. 
 * @author Alix.Brugier,Mateo.faussurier,Nathan.Girardin,Rayan.Ibrahime
 */
public class Quiz {

    private int difficulte;
    private int nombreQuestions;
    private Categorie categorie;
    private Stockage stockage;
    private ArrayList<Question> listeQuestion;
    private ArrayList<String> reponseSelectionnees = new ArrayList<>();
    private int score = 0;
    private boolean[] listeResultatReponse;

    /**
     * Constructeur de la classe Quiz.
     * @param difficulte La difficulté du quiz.
     * @param nombreQuestion Le nombre de questions dans le quiz.
     * @param categorie La catégorie du quiz.
     * @param stockage L'objet de stockage des données.
     */
    public Quiz(int difficulte, int nombreQuestion, Categorie categorie, Stockage stockage) {
        if (stockage != null) {
            this.difficulte = difficulte;
            nombreQuestions = nombreQuestion;
            if (categorie != null && stockage.getListeCategorie().containsKey(categorie.getIntituleCategorie())) {
                this.categorie = categorie;
            }
            listeQuestion = stockage.listeQuestionFiltreDifficulteCategorieTaille(this);
            this.nombreQuestions = listeQuestion.size();
            this.listeResultatReponse = new boolean[listeQuestion.size()];
        }
    }

    /**
     * Incrémente le score du quiz.
     */
    public void incrementerScore() {
        score++;
    }

    /**
     * Retourne la liste des réponses sélectionnées.
     * @return La liste des réponses sélectionnées.
     */
    public ArrayList<String> getListeReponsesSelectionnees() {
        return this.reponseSelectionnees;
    }

    /**
     * Retourne la difficulté du quiz.
     * @return La difficulté du quiz.
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Retourne le nombre de questions dans le quiz.
     * @return Le nombre de questions dans le quiz.
     */
    public int getNombreQuestions() {
        return nombreQuestions;
    }

    /**
     * Retourne la catégorie du quiz.
     * @return La catégorie du quiz.
     */
    public Categorie getCategorie() {
        return categorie;
    }

    /**
     * Retourne la liste des questions dans le quiz.
     * @return La liste des questions dans le quiz.
     */
    public ArrayList<Question> getListeQuestion() {
        return listeQuestion;
    }

    /**
     * Vérifie si la quantité de questions spécifiée est suffisante.
     * @param quantiteChoisi La quantité de questions choisie.
     * @return true si la quantité de questions est suffisante, false sinon.
     */
    public boolean quantiteQuestionOk(int quantiteChoisi) {
        return this.nombreQuestions >= quantiteChoisi;
    }

    /**
     * Modifie le nombre de questions dans le quiz.
     * @param nombreQuestion Le nouveau nombre de questions.
     */
    public void setNombreQuestion(int nombreQuestion) {
        this.nombreQuestions = nombreQuestion;
    }

    /**
     * Ajoute le résultat d'une question à la liste des résultats.
     * @param indice L'indice de la question.
     * @param resultat Le résultat de la question.
     */
    public void ajouterResultat(int indice, boolean resultat) {
        if (indice < listeResultatReponse.length) {
            listeResultatReponse[indice] = resultat;
        }
    }

    /**
     * Vérifie si la réponse choisie pour une question donnée est correcte.
     * @param reponseChoisie La réponse choisie.
     * @param indiceQuestion L'indice de la question dans la liste.
     * @return true si la réponse est correcte, false sinon.
     */
    public boolean estJuste(String reponseChoisie, int indiceQuestion) {
        return reponseChoisie.equals(listeQuestion.get(indiceQuestion).getReponseJusteQuestion());
    }

    /**
     * Retourne une liste de cinq questions pour une page donnée.
     * @param indicePage L'indice de la page.
     * @return Une liste de cinq questions.
     */
    public ArrayList<Question> getCinqQuestions(int indicePage) {
        int nombreQuestionAAfficher = listeQuestion.size() - (5 * indicePage);
        int indiceMinimum;
        if (indicePage != 0) {
            indiceMinimum = (indicePage * 5) - 1;
        } else {
            indiceMinimum = indicePage * 5;
        }
        if (nombreQuestionAAfficher > 5) {
            nombreQuestionAAfficher = 5;
        }
        ArrayList<Question> listeCinqQuestions = new ArrayList<>();
        for (int i = 0; i < nombreQuestionAAfficher; i++) {
            listeCinqQuestions.add(listeQuestion.get(indiceMinimum + i));
        }
        return listeCinqQuestions;
    }

    /**
     * Retourne le résultat d'une question spécifiée.
     * @param indice L'indice de la question dans la liste.
     * @return Le résultat de la question.
     */
    public boolean getResultatDeQuestion(int indice) {
        return listeResultatReponse[indice];
    }

    /**
     * Retourne le score final du quiz.
     * @return Le score final du quiz.
     */
    public int getScoreFinal() {
        return score;
    }
}