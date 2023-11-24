package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Chiffrement {

	private final static String CSV_ENTRANT = "banque_de_question_exporte.csv";
	private final static String CSV_CHIFFRER = "banque_de_question_exporte_chiffre.csv";
	private final static String CSV_SORTANT_DECHIFFRER = "banque_de_question_entrante_dechiffrer.csv";
	private final static String CSV_CLE_PUBLIQUE = "cle_publique.csv";

	/* nombre premier que vous pouvez choisir, vous devez avoir le meme que l'autre personne */
	private final static int MON_NOMBRE_PREMIER = 17 ;
	/* Generateur, vous devez avoir le meme que l'autre personne */
	private static final int GENERATEUR = 0;
	/* Clé que vous pouvez modifier a votre bon vouloir */
	private final static String CLE = "zfzfeDEZ";
	/* Index du caractères acuel de la clé */
	private static int indexCleActuel;	
	/* Index du caractère chiffré dans la liste de caractères*/
	private static int indexCaractereChiffre;
	/* Index du caractères actuel */
	private static int indexCaractere;
	/* Index du caractere codé */
	private static int indexChiffre;
	/* Index du caractere décodé */
	private static int indexDechiffre;
	
	private static int maClePrive;

	private final static char[] listeCaracteres = {
			'!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', '>', '?',
			'@', 'A', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'Ê',
			'Ë', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Î', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O',
			'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'P', 'Q', 'R', 'S', 'Š', 'T', 'U', 'Ù', 'Ú', 'Û',
			'Ü', 'V', 'W', 'X', 'Y', 'Ÿ', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'à', 'á',
			'â', 'ã', 'ä', 'å', 'b', 'c',' ', 'ç', 'd', 'e', 'è', 'é', 'ê', 'ë', 'f', 'g', 'h',
			'i', 'ì', 'í', 'î', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'ô', 'õ',
			'ö', 'ø', 'p', 'q', 'r', 's', 'š', 't', 'u', 'ù', 'ú', 'û', 'ü', 'v', 'w', 'x',
			'y', 'ÿ', 'z', '{', '|', '}', '~'
	};
	


	public static void chiffrementVigenere(int cle) {

		try {
			String texteAChiffrer = lireCsv(CSV_ENTRANT);

			// Chiffrement de Vigenère
			String texteChiffrer = codeVigenere(texteAChiffrer, CLE);

			// Écriture du fichier chiffré
			ecritureFichier(CSV_CHIFFRER, texteChiffrer);

			System.out.println("Chiffrement de Vigenère terminé avec succès.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void dechiffrementVigenere() {

		try {
			// Initialisation du dictionnaire avec les correspondances fournies
			String texteADechiffrer = lireCsv(CSV_CHIFFRER);

			// Chiffrement de Vigenère
			String texteDechiffrer = decodeVigenere(texteADechiffrer, CLE);

			// Écriture du fichier chiffré
			ecritureFichier(CSV_SORTANT_DECHIFFRER, texteDechiffrer);

			System.out.println("Dechiffrement de Vigenère terminé avec succès.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static String lireCsv(String filePath) throws IOException {
		// Creer une string ou l'on rajouteras tout les elements du csv
		StringBuilder ensembleTexte = new StringBuilder();
		// Lis le contenu du fichier ligne par ligne
		try (BufferedReader texte = new BufferedReader(new FileReader(filePath))) {
			// Ligne du fichier
			String ligne;

			// Ajoute les lettre ligne par ligne a la string 
			while ((ligne = texte.readLine()) != null) {

				ensembleTexte.append(ligne).append('\u0013');
			}
			// Ajoute un retour a la ligne a la fin de chaque question

		}
		System.out.println(ensembleTexte.toString());
		return ensembleTexte.toString();
	}


	private static String codeVigenere(String texte, String cle) {

		// Creer une string ou l'on rajouteras tout les lettres chiffrées
		StringBuilder texteChiffrer = new StringBuilder();
		// Obtention de la longueur de la clé et initialisation de l'index de la clé
		int longueurCle = cle.length();
		int indexCle = 0;

		// Parcours de chaque caractère dans le texte à chiffrer
		for (char caractere : texte.toCharArray()) {
			// Si le caractère est un ';', le laisser inchangé
			if (caractere == ';') {		
				texteChiffrer.append('\u0012');
			} else if (caractere == '\u0013') {
				texteChiffrer.append(caractere);
			} else {
				// Index du caractère dans la liste de caractères
				indexCaractere = trouverIndexCaractere(caractere);
				// Index du caractère de la cle dans la liste de caractères
				indexCleActuel = trouverIndexCaractere(cle.charAt(indexCle));

				// Index du caractere codé 
				indexChiffre = (indexCaractere + indexCleActuel) % listeCaracteres.length;

				// Ajout du caractere correspondant a l'index a la string
				texteChiffrer.append(listeCaracteres[indexChiffre]);
				// Passage a l'index suivant
				indexCle = (indexCle + 1) % longueurCle;
			}
		}

		return texteChiffrer.toString();
	}

	private static String decodeVigenere(String messageChiffre, String cle) {

		// Creer une string ou l'on rajouteras tout les lettres déchiffrées
		StringBuilder messageDechiffre = new StringBuilder();

		// Obtention de la longueur de la clé et initialisation de l'index de la clé
		int longueurCle = cle.length();
		int indexCle = 0;

		for (char caractereChiffre : messageChiffre.toCharArray()) {

			// Si le caractère chiffré est un ';', le laisser inchangé
			if (caractereChiffre == '\u0012') {
				messageDechiffre.append(';');
			} else if (caractereChiffre == '\u0013') {
				messageDechiffre.append('\n');
			}else {
				// Index du caractère chiffré dans la liste de caractères
				indexCaractereChiffre = trouverIndexCaractere(caractereChiffre);

				// Index du caractère de la cle dans la liste de caractères
				indexCleActuel = trouverIndexCaractere(cle.charAt(indexCle));

				// Index du caractere décodé 
				indexDechiffre = (indexCaractereChiffre - indexCleActuel + listeCaracteres.length) % listeCaracteres.length;

				// Ajout du caractere correspondant a l'index a la string
				messageDechiffre.append(listeCaracteres[indexDechiffre]);

				// Passage a l'index suivant
				indexCle = (indexCle + 1) % longueurCle;
			}
		}

		return messageDechiffre.toString();
	}


	private static int trouverIndexCaractere(char caractere) {
		// Parcoure la liste des caractéres
		for (int i = 0; i < listeCaracteres.length; i++) {
			// Renvoie l'index du caractere de la liste si il est égal au caractere rechérché
			if (listeCaracteres[i] == caractere) {
				return i;
			}
		}
		// Si le caractère n'est pas trouvé dans la liste, retourner une valeur par défaut
		return -1;
	}

	private static void ecritureFichier(String filePath, String content) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
		}
	}


	public static void chiffrementDiffieHellman() {

		try {
			// Retrouve la cle de chiffrement
			int clePrive = clePrive(MON_NOMBRE_PREMIER,GENERATEUR);
			maClePrive = clePrive;
			int clePublique = clePublique(GENERATEUR,clePrive,MON_NOMBRE_PREMIER);
			// création du fichier contenant la clé publique
			ecritureFichier(CSV_CLE_PUBLIQUE, Integer.toString(clePublique));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void dechiffrementDiffieHellman() {

		try {
			// Recupere la cle publique de l'autre personne
			String clePubliqueAutre = lireCsv(CSV_CLE_PUBLIQUE);
			int clePartage = calculClePartage(Integer.parseInt(clePubliqueAutre),MON_NOMBRE_PREMIER,maClePrive);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	private static int calculClePartage(int clePubliqueAutre, int nombrePremier, int clePrive) {
		int clePartage = (clePubliqueAutre^clePrive)%nombrePremier;
		return clePubliqueAutre;

	}

	private static int clePrive(int NombrePremier, int generateur) {
		int moduleChiffrement = NombrePremier*generateur;
		// Calcul de la fonction d'Euler en moduleChiffrement
		int fonctionEuler = (NombrePremier-1)*(generateur-1);
		// Renvoie un exposant de chiffrement premier a la fonction d'euler aléatoire
		int clePrive = exposantAleatoire(fonctionEuler);
		// Création de la clé publique
		return clePrive;

	}

	private static int clePublique(int generateur, int clePrive, int nombrePremier) {		
		int nombre = (clePrive^generateur)%nombrePremier;
		return nombre;
	}

	private static int exposantAleatoire(int fonctionEuler) {
		int[] listeDeNombres = null;
		int compteur = 0;
		for (int i = 2; i < fonctionEuler; i++) {
			// Ajoute a la liste les nombres premiers à fonctionEuler
			if (sontPremiersEntreEux(fonctionEuler, i)) {
				listeDeNombres[compteur] = i;
				compteur++;
			}
		}
		return obtenirNombreAleatoire(listeDeNombres);
	}


	public static boolean sontPremiersEntreEux(int fonctionEuler, int nombreTeste) {
		return pgcd(fonctionEuler, nombreTeste) == 1;
	}

	public static int pgcd(int fonctionEuler, int nombreTeste) {
		while (nombreTeste != 0) {
			int temporaire = nombreTeste;
			// Trouve le reste de fonctionEuler par le nombreTeste
			nombreTeste = fonctionEuler % nombreTeste;
			fonctionEuler = temporaire;
		}		
		// retourne le pgcd
		return fonctionEuler;
	}
	
    public static int obtenirNombreAleatoire(int[] liste) {
        Random random = new Random();
        // Sélection aléatoire d'un index dans la liste
        int indexAleatoire = random.nextInt(liste.length);
        // Retour du nombre correspondant à l'index sélectionné
        return liste[indexAleatoire];
    }
}
