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
	
	public final static int NUM_PORT =  12345;
	private static ServerSocket serverSocket;
	private static Socket clientSocket;
	public static boolean gererConnexion() {
		boolean Ok;
		Ok = true;
	    try {
	        // Création d'un ServerSocket écoutant sur le port 12345
	        serverSocket = new ServerSocket(NUM_PORT);

	        // Définir un temps d'attente maximal en millisecondes
	        serverSocket.setSoTimeout(4000);

	        System.out.println("Attente de connexion...");
	       
	        try {
	            //Attente d'une connexion cliente
	            clientSocket = serverSocket.accept();

	            System.out.println("Connexion établie.");

	            // Appel de la méthode pour traiter la réception du fichier
	            traiterReceptionFichier();
	            
	            // Close the ServerSocket here if needed
	            serverSocket.close();
	           
	        } catch (java.net.SocketTimeoutException e) {
	            System.out.println("Temps d'attente écoulé. Aucune connexion reçue dans le délai spécifié.");
	            // Close the ServerSocket after the timeout
	            serverSocket.close();
	            Ok = false;
	        }

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return Ok;
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
