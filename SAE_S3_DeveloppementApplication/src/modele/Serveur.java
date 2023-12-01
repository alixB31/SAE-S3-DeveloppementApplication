/*
 * Serveur.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import vue.Main;

/** 
 * Gestion des échanges du serveur avec chiffrement des données.
 * @author Alix.Brugier Mateo.faussurier Nathan.Girardin Rayan.Ibrahime
 */
public class Serveur {
    /** TODO commenter le rôle de la méthode
     * @param args
     * 
     */
	
	public final static int NUM_PORT = 49152;
	
	private static ServerSocket socketServeur;
	private static Socket socketClient;
	private static File fichierRecu;
	private static FileOutputStream sortieFichier;
	
	public static boolean gererConnexion() {
	    boolean estRecu = false;
	    boolean connexionAnnulee = false;

	    try {
	        socketServeur = new ServerSocket(NUM_PORT);
	        socketServeur.setSoTimeout(60000);

	        try {
	            // Attente d'une connexion cliente
	            socketClient = socketServeur.accept();

	            if (!connexionAnnulee) {
	                traiterReceptionFichier();
	                estRecu = true;
	            }

	        } catch (java.net.SocketTimeoutException e) {
	            connexionAnnulee = true;
	        }

	    } catch (IOException e) {
	    	connexionAnnulee = true;
	    } finally {
	        // Fermez le ServerSocket ici après avoir traité la connexion ou après l'annulation
	        try {
	            if (socketServeur != null && !socketServeur.isClosed()) {
	                socketServeur.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return estRecu;
	}
	
	/**
     * Traite la réception du fichier.
     * 
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    private static void traiterReceptionFichier() throws IOException {
        // Obtention du flux d'entrée du client
        BufferedInputStream entreeClient = new BufferedInputStream(socketClient.getInputStream());

        // Création d'un fichier pour stocker le fichier CSV reçu
        fichierRecu = new File("Chiffrer.csv");
        sortieFichier = new FileOutputStream(fichierRecu);

        // Lecture et écriture du fichier
        byte[] tampon = new byte[1024];
        int octetsLus;
        String cleAEnvoyer = Chiffrement.chiffrementDiffieHellman();
        int clePubliqueC = cleServeur(socketClient, cleAEnvoyer);
        long cleGlobale = Chiffrement.dechiffrementDiffieHellman(clePubliqueC);
        String cleVigenere = Chiffrement.CreationCleVigenere(cleGlobale);  
        while ((octetsLus = entreeClient.read(tampon)) != -1) {
            sortieFichier.write(tampon, 0, octetsLus);
        }
        Chiffrement.dechiffrementVigenere(cleVigenere);
        // Fermeture des flux et du socket
        entreeClient.close();
        sortieFichier.close();
        // Fin de la connexion
        arreterConnexion();
        // Importation du fichier qui a été reçu et déchiffré
        Main.stockage.importCSV("Dechiffrer.csv");
    }
    
	// Ajouter cette méthode à la classe Serveur pour arrêter la connexion
	public static void arreterConnexion() {
	    try {
	        if (socketServeur != null && !socketServeur.isClosed()) {
	            socketServeur.close();

	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	public static int cleServeur(Socket client, String cleAEnvoyer) {
		int clePubliqueC = 0;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        PrintStream out = new PrintStream(client.getOutputStream());
	        out.println(cleAEnvoyer);
	        clePubliqueC = Integer.parseInt(in.readLine());
		} catch (IOException e) {
            System.err.println("Communication avec le serveur impossible");
            clePubliqueC = 0;
        }
		return clePubliqueC;
	}
}
