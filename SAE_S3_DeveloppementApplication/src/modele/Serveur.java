package modele;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe représentant le serveur.
 * 
 * TODO: Ajouter des commentaires expliquant les responsabilités de la classe (SRP).
 * TODO: Ajouter des commentaires expliquant le rôle de chaque méthode.
 * 
 * @author rayanibrahime
 */
public class Serveur {
    public final static int NUM_PORT = 49152;

    /**
     * Constructeur de la classe Serveur.
     */
    public Serveur() {
        // Appel de la méthode pour gérer la connexion
        gererConnexion();
    }

    /**
     * Gère la connexion avec le client.
     */
    public static void gererConnexion() {
        try {
            ServerSocket serverSocket = new ServerSocket(NUM_PORT);
            System.out.println("Attente de connexion...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Connexion établie.");

            // Appel de la méthode pour confirmer l'écriture et la réception du fichier
            confirmerEcritureEtReception(clientSocket);

            // Fermeture des flux et des sockets
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Confirme l'écriture et la réception du fichier.
     * 
     * @param clientSocket Le socket du client.
     */
    private static void confirmerEcritureEtReception(Socket clientSocket) {
        try {
            BufferedInputStream in = new BufferedInputStream(clientSocket.getInputStream());

            // Création d'un fichier pour stocker le fichier CSV reçu
            File receivedFile = new File("received.csv");
            FileOutputStream fileOut = new FileOutputStream(receivedFile);

            // Lecture et écriture du fichier
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }

            System.out.println("Fichier reçu et enregistré : " + receivedFile.getAbsolutePath());

            // Fermeture des flux
            in.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static int getNumPort() {
		return NUM_PORT;
	}
}
