/*
 * Client.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import controleur.EnvoieController;

/** TODO comment class responsibility (SRP)
 * @author rayanibrahime
 *
 */
public class Client {
    /** TODO comment method role
     * @param args
     */

	public static void envoie(String IP, int numPort) {
        try {
            // Connexion au serveur sur le port 12345
            Socket socket = new Socket(IP, numPort);

            // Obtention du flux de sortie vers le serveur
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

            // Fermeture des flux et de la socket
            out.close();
            fileIn.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
