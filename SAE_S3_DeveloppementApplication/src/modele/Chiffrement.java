package modele;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 * Chiffrement de code csv avec methode de Vigenere.
 * Cle chiffré avec la méthode de Diffie-Hellman
 * @author alix.brugier, mateo.faussurier, rayan.ibrahime, girardin.nathan
 */
public class Chiffrement {

	private final static String CSV_ENTRANT = "aEnvoyer.csv";
	private final static String CSV_CHIFFRER = "Crypter.csv";
	private final static String CSV_SORTANT_DECHIFFRER = "Dechiffrer.csv";

	/* nombre premier que vous pouvez choisir, vous devez avoir le meme que l'autre personne */
	private final static int MON_NOMBRE_PREMIER = 29 ;
	/* Generateur, vous devez avoir le meme que l'autre personne */
	private static final int GENERATEUR = 3;
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
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', '<', '=', '>', '?',
			'@', 'A', 'À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'Ê',
			'Ë', 'F', 'G', 'H', 'I', 'Ì', 'Í', 'Î', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O',
			'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'P', 'Q', 'R', 'S', 'Š', 'T', 'U', 'Ù', 'Ú', 'Û',
			'Ü', 'V', 'W', 'X', 'Y', 'Ÿ', 'Z', '[', '\\', ']', '^', '_', '`', 'a', 'à', 'á',
			'â', 'ã', 'ä', 'å', 'b', 'c',' ', 'ç', 'd', 'e', 'è', 'é', 'ê', 'ë', 'f', 'g', 'h',
			'i', 'ì', 'í', 'î', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'ô', 'õ',
			'ö', 'ø', 'p', 'q', 'r', 's', 'š', 't', 'u', 'ù', 'ú', 'û', 'ü', 'v', 'w', 'x',
			'y', 'ÿ', 'z', '{', '|', '}', '~'
	};



	/** 
	 * Chiffre un fichier csv entrant a l'aide d'une cle de chiffrement
	 * @param cle utilisé pour chiffrer
	 */
	public static void chiffrementVigenere(String cle) {

		try {
			String texteAChiffrer = lireCsv(CSV_ENTRANT);
			// Chiffrement de Vigenère
			String texteChiffrer = codeVigenere(texteAChiffrer, cle);
			// Écriture du fichier chiffré
			ecritureFichier(CSV_CHIFFRER, texteChiffrer);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 
	 * Dechiffre un fichier csv entrant a l'aide d'une cle de chiffrement
	 * @param cle utilisé pour chiffrer
	 */
	public static void dechiffrementVigenere(String cle) {

		try {
			// Initialisation du dictionnaire avec les correspondances fournies
			String texteADechiffrer = lireCsv(CSV_CHIFFRER);
			// Chiffrement de Vigenère
			String texteDechiffrer = decodeVigenere(texteADechiffrer, cle);
			// Écriture du fichier chiffré
			ecritureFichier(CSV_SORTANT_DECHIFFRER, texteDechiffrer);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}




	/** 
	 * Chiffre par une méthode de Vigenere une chaine de caractéres
	 * @param texte a chiffrer
	 * @param cle a utiliser pour chiffrer
	 * @return String chaine de caractères chiffrées
	 */
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


	/** 
	 * Dechiffre par une méthode de Vigenere une chaine de caractéres
	 * @param texte a déchiffrer
	 * @param cle a utiliser pour déchiffrer
	 * @return String chaine de caractères déchiffrées
	 */
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

	/** 
	 * Trouve l'index du caractere dans le dictionnaire prédéfinis
	 * @param caractere 
	 * @return int l'index du caractere dans le dictionnaire
	 */
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

	/** 
	 * Creer une clé publique et une cle prive puis renvoie la clé publique
	 */
	public static String chiffrementDiffieHellman() {
		// Retrouve la cle de chiffrement
		int clePrive = clePrive(MON_NOMBRE_PREMIER,GENERATEUR);
		maClePrive = clePrive;
		long clePublique = clePublique(GENERATEUR,clePrive,MON_NOMBRE_PREMIER);
		return String.valueOf(clePublique);

	}

	/** 
	 * Creer la clé partagée avec la clé publique de l'autre.
	 * @param clePubliqueAutre
	 * @return 
	 */
	public static long dechiffrementDiffieHellman(int clePubliqueAutre) {
		// Recupere la cle publique de l'autre personne
		long cleGlobale = calculClePartage(clePubliqueAutre,maClePrive,MON_NOMBRE_PREMIER);
		return cleGlobale;

	}

	/** 
	 * Calcule la clé partagée grace a un calcul modulaire.
	 * @param clePubliqueAutre
	 * @param nombrePremier
	 * @param clePrive
	 */
	public static long calculClePartage(long clePubliqueAutre,long clePrive, long nombrePremier) {
		System.out.println("Ma cle privé " +clePrive);
		long clePartage = exponentiationModulaire(clePubliqueAutre,clePrive,nombrePremier);
		return clePartage;

	}

	/** 
	 * Simplifie les calculs pour éviter que l'exponentielle dépasse la limite des long
	 * @param base a exponentié
	 * @param exposant du calcul
	 * @param modulo du calcul
	 */
	private static long exponentiationModulaire(long base, long exposant, long modulo) {
		long resultat = base; 
		for(int i =1; i<exposant;i++) {
			resultat *= exposant;
			resultat %= modulo;
		}
		return resultat;  // Retourne le résultat final
	}

	/** 
	 * Calcule la clé privé grace a un calcul modulaire.
	 * @param clePubliqueAutre
	 * @param nombrePremier
	 * @param clePrive
	 * @return int clePrive
	 */
	public static int clePrive(int NombrePremier, int generateur) {
		// Calcul de la fonction d'Euler en moduleChiffrement
		int fonctionEuler = (NombrePremier-1)*(generateur-1);
		// Renvoie un exposant de chiffrement premier a la fonction d'euler aléatoire
		int clePrive = exposantAleatoire(fonctionEuler);
		// Création de la clé publique
		return clePrive;

	}

	/** 
	 * Calcule la clé publique.
	 * @param generateur
	 * @param clePrive
	 * @param nombrePremier
	 * @return int clePublique
	 */
	public static long clePublique(int generateur, int clePrive, int nombrePremier) {		
		long clePublique = exponentiationModulaire(clePrive,generateur,nombrePremier);
		return clePublique;
		//		int nombre = (clePrive^generateur)%nombrePremier;
		//		return clePublique;
	}

	/** 
	 * Renvoie un nombre aléatoire qui est premier a fonctionEuler
	 * @param generateur
	 * @param clePrive
	 * @param nombrePremier
	 * @return int nombre aléatoire
	 */
	private static int exposantAleatoire(int fonctionEuler) {
		// Déclaration d'une liste de nombres
		ArrayList<Integer> listeDeNombres = new ArrayList<>();
		for (int i = 2; i<fonctionEuler; i++) {
			// Ajoute a la liste les nombres premiers à fonctionEuler
			if (sontPremiersEntreEux(fonctionEuler, i)) {
				listeDeNombres.add(i) ;
			}
		}

		return obtenirNombreAleatoire(listeDeNombres);
	}

	/** 
	 * Regarde si deux nombres sont premiers entre eux. Pour cela regarde si leur 
	 * pgcd = 1
	 * @param fonctionEuler
	 * @param nombreTeste
	 * @return true si premier false sinon
	 */
	public static boolean sontPremiersEntreEux(int fonctionEuler, int nombreTeste) {
		return pgcd(fonctionEuler, nombreTeste) == 1;
	}

	/** 
	 * Renvoie le plus grand denominateur commun de deux nombres
	 * @param fonctionEuler
	 * @param nombreTeste
	 * @return int
	 */
	public static int pgcd(int fonctionEuler, int nombreTeste) {
		while (nombreTeste != 0 && fonctionEuler != 1) {
			int temporaire = nombreTeste;
			// Trouve le reste de fonctionEuler par le nombreTeste
			nombreTeste = fonctionEuler % nombreTeste;
			fonctionEuler = temporaire;
		}		
		// retourne le pgcd
		return fonctionEuler;
	}

	/** 
	 * Renvoie un nombre aleatoire appartenant a une liste
	 * @param listeDeNombres
	 * @return int
	 */
	public static int obtenirNombreAleatoire(ArrayList<Integer> listeDeNombres) {
		Random random = new Random();
		// Sélection aléatoire d'un index dans la liste
		int indexAleatoire = random.nextInt(listeDeNombres.size());
		// Retour du nombre correspondant à l'index sélectionné
		return listeDeNombres.get(indexAleatoire);
	}

	/** 
	 * Creer la cle de vigenere a l'aide de la cle globale créer par
	 * l'échange de Diffie-Hellman
	 * @param cleGlobale
	 * @return String cleVigenere
	 */
	public static String CreationCleVigenere(long cleGlobale) {
		// Creer une string ou l'on rajouteras tout les caractere de la cle de vigenere
		StringBuilder cleVigenere = new StringBuilder();
		String cle = String.valueOf(cleGlobale*24);
		for (int i = 0; i < cle.length(); i++) {
	        // Multiplier chaque chiffre par sa place dans la clé et l'ajouter à la StringBuilder
	        int chiffre = Character.getNumericValue(cle.charAt(i));
	        int valeurModifiee = chiffre * (i + 1);
	        cleVigenere.append(listeCaracteres[valeurModifiee%listeCaracteres.length]);
	    }
		// retourne la cle de vigenere créer
		System.out.println(cleVigenere);
		return cleVigenere.toString();
	}


	/** 
	 * Lis un fichier et ajoute un à un les caractères a une string en remplacant 
	 * les retours a la ligne par un caractére spécial
	 * @param fichier a lire
	 * @return String chaine de tout les caractères du fichier 
	 */
	private static String lireCsv(String filePath) throws IOException {
		// Creer une string ou l'on rajouteras tout les elements du csv
		StringBuilder ensembleTexte = new StringBuilder();
		// Lis le contenu du fichier ligne par ligne
		try (BufferedReader texte = new BufferedReader(new FileReader(filePath))) {
			// Ligne du fichier
			String ligne;

			// Ajoute les lettre ligne par ligne a la string 
			while ((ligne = texte.readLine()) != null) {
				// Lorsque la ligne ajoute un caractere spécial pour remplacer le retour a la ligne
				ensembleTexte.append(ligne).append('\u0013');
			}
			// Ajoute un retour a la ligne a la fin de chaque question
		}
		return ensembleTexte.toString();
	}


	/** 
	 * Lis un fichier cle et ajoute un à un les caractères a une string
	 * @param fichier a lire
	 * @return String chaine de tout les caractères du fichier 
	 */
	private static String lireCle(String filePath) throws IOException {
		// Creer une string ou l'on rajouteras tout les elements du csv
		StringBuilder ensembleTexte = new StringBuilder();
		// Lis le contenu du fichier ligne par ligne
		try (BufferedReader texte = new BufferedReader(new FileReader(filePath))) {
			// Ligne du fichier
			String ligne;
			// Ajoute les lettre ligne par ligne a la string 
			while ((ligne = texte.readLine()) != null) {
				ensembleTexte.append(ligne);
			}
		}
		return ensembleTexte.toString();
	}
	/**
	 * Écrit le contenu spécifié dans un fichier donné.
	 * @param filePath Le chemin du fichier dans lequel écrire le contenu.
	 * @param content Le contenu à écrire dans le fichier.
	 * @throws IOException En cas d'erreur lors de l'écriture dans le fichier.
	 */
	private static void ecritureFichier(String filePath, String content) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(content);
		}
	}
}
