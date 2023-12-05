/*
 * EchecSerialisationRestauration.java									15 novembre 2023 
 * IUT de Rodez, no copyright ni "copyleft"
 */
package modele;

/**
 * Exception levée si problème d'accés au fichier lors de la 
 * sérialisation ou restauration d'une instance de type Stockage
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class EchecSerialisationRestauration extends Exception {

	private static final long serialVersionUID = 1L;

	/**
     * Constructeur avec en argument un message decrivant l'erreur
     * @param message  un texte expliquant la cause precise de l'erreur
     */
    public EchecSerialisationRestauration(String message) {
        super(message);
    }
}