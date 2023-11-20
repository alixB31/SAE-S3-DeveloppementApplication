/*
 * Client.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import  controleur.EnvoieController;

/**
 * Classe représentant le client.
 * 
 * TODO: Ajouter des commentaires expliquant les responsabilités de la classe (SRP).
 * TODO: Ajouter des commentaires expliquant le rôle de chaque méthode.
 * 
 * @author rayanibrahime
 */


public class Client {
	private static String IP;
	private static int numPort;
	
	public Client() {
		IP = EnvoieController.getAdresseIPSaisie();
		numPort = Integer.parseInt(EnvoieController.getNumDePort());
	}

	public static void connexionServeur() {
        try {
            // Connexion au serveur sur le port 49152
            Socket socket = new Socket(IP,numPort);

            // Appel de la méthode pour envoyer le fichier au serveur
            envoyerFichier(socket);

            // Fermeture de la socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

    /**
     * Envoie le fichier CSV au serveur.
     * 
     * @param socket Le socket de connexion au serveur.
     * @throws IOException En cas d'erreur d'entrée/sortie.
     */
    private static void envoyerFichier(Socket socket) throws IOException {
        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());

        // Sélection du fichier CSV à envoyer
        String filePath = "questionsbasiques.csv";
        FileInputStream fileIn = new FileInputStream(filePath);

        // Lecture et envoi du fichier
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileIn.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        System.out.println("Fichier envoyé : " + filePath);

        // Fermeture des flux
        out.close();
        fileIn.close();
    }
}
