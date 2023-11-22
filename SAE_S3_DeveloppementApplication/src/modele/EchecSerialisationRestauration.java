package modele;

/**
 * Exception lev�e si probl�me d'acc�s au fichier lors de la 
 * s�rialisation ou restauration d'une instance de type Stockage
 */
public class EchecSerialisationRestauration extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructeur avec en argument un message d�crivant l'erreur
     * @param message  un texte expliquant la cause pr�cise de l'erreur
     */
    public EchecSerialisationRestauration(String message) {
        super(message);
    }
}