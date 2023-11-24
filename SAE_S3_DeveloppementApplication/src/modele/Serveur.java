/*
 * Serveur.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
 */
public class Serveur {
    /** TODO comment method role
     * @param args
     * 
     */
	
	public final static int NUM_PORT = 49152;
	
	private static ServerSocket serverSocket;
	
	private static Socket clientSocket;
	
	public static boolean gererConnexion() {
	    boolean estRecu = false;
	    boolean connexionAnnulee = false;

	    try {
	        serverSocket = new ServerSocket(NUM_PORT);
	        serverSocket.setSoTimeout(60000);

	        System.out.println("Attente de connexion...");

	        try {
	            // Attente d'une connexion cliente
	            clientSocket = serverSocket.accept();

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
	            if (serverSocket != null && !serverSocket.isClosed()) {
	                serverSocket.close();
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
	        if (serverSocket != null && !serverSocket.isClosed()) {
	            serverSocket.close();
	            System.out.println("Connexion annulée par l'utilisateur.");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	
	
	/**
     * Traite la réception du fichier.
     * 
     * @param clientSocket Le socket du client.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
	private static File receivedFile;
	private static FileOutputStream fileOut;
    private static void traiterReceptionFichier() throws IOException {
        // Obtention du flux d'entrée du client
        BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());

        // Création d'un fichier pour stocker le fichier CSV reçu
        receivedFile = new File("received.csv");
        fileOut = new FileOutputStream(receivedFile);

        // Lecture et écriture du fichier
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            fileOut.write(buffer, 0, bytesRead);
        }
        
        System.out.println("Fichier reçu et enregistré : " + receivedFile.getAbsolutePath());
        // Fermeture des flux et du socket
        in.close();
        fileOut.close();
        clientSocket.close();
        
    }
    
    public static String getNomFichier() {
    	return receivedFile.getAbsolutePath();
    }
    
    public static void fermerServeur() throws IOException {
    	serverSocket.close();
    }
}
