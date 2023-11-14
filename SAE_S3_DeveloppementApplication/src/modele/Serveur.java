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
     */
    public static void main(String[] args) {
        try {
            // Création d'un ServerSocket écoutant sur le port 12345
            ServerSocket serverSocket = new ServerSocket(12345);

            System.out.println("Attente de connexion...");

            // Attente d'une connexion cliente
            Socket clientSocket = serverSocket.accept();

            System.out.println("Connexion établie.");

            // Obtention du flux d'entrée du client
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

            // Fermeture des flux et des sockets
            in.close();
            fileOut.close();
            clientSocket.close();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
