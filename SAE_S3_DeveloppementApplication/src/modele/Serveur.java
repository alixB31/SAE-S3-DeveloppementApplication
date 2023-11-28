/*
 * Serveur.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** TODO commenter la responsabilité de la classe (SRP)
 * @author rayanibrahime
 *
 */
public class Serveur {
    /** TODO commenter le rôle de la méthode
     * @param args
     * 
     */
	
	public final static int NUM_PORT = 49152;
	
	private static ServerSocket socketServeur;
	private static Socket socketClient;
	
	public static boolean gererConnexion() {
	    boolean estRecu = false;
	    boolean connexionAnnulee = false;

	    try {
	        socketServeur = new ServerSocket(NUM_PORT);
	        socketServeur.setSoTimeout(60000);

	        System.out.println("Attente de connexion...");

	        try {
	            // Attente d'une connexion cliente
	            socketClient = socketServeur.accept();

	            if (!connexionAnnulee) {
	                System.out.println("Connexion établie.");
	                traiterReceptionFichier();
	                estRecu = true;
	            } else {
	                System.out.println("Connexion annulée par l'utilisateur.");
	            }

	        } catch (java.net.SocketTimeoutException e) {
	            System.out.println("Temps d'attente écoulé. Aucune connexion reçue dans le délai spécifié.");
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

	// Ajouter cette méthode à la classe Serveur pour arrêter la connexion
	public static void arreterConnexion() {
	    try {
	        if (socketServeur != null && !socketServeur.isClosed()) {
	            socketServeur.close();
	            System.out.println("Connexion annulée par l'utilisateur.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
     * Traite la réception du fichier.
     * 
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
	private static File fichierRecu;
	private static FileOutputStream sortieFichier;
	
    private static void traiterReceptionFichier() throws IOException {
        // Obtention du flux d'entrée du client
        BufferedInputStream entreeClient = new BufferedInputStream(socketClient.getInputStream());

        // Création d'un fichier pour stocker le fichier CSV reçu
        fichierRecu = new File("recu.csv");
        sortieFichier = new FileOutputStream(fichierRecu);

        // Lecture et écriture du fichier
        byte[] tampon = new byte[1024];
        int octetsLus;
        while ((octetsLus = entreeClient.read(tampon)) != -1) {
            sortieFichier.write(tampon, 0, octetsLus);
        }
        
        System.out.println("Fichier reçu et enregistré : " + fichierRecu.getAbsolutePath());
        repondreReceptionFichier(socketClient);
        
        // Fermeture des flux et du socket
        entreeClient.close();
        sortieFichier.close();
    }
    
    private static void repondreReceptionFichier(Socket socket) {
    	try {
            // Obtention du flux de sortie du client
            BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

            // Envoyer la réponse au client
            String reponse = "Fichier reçu avec succès par le serveur.";
            out.write(reponse.getBytes());

            System.out.println("Réponse envoyée au client : " + reponse);

            // Fermer le flux de sortie
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
