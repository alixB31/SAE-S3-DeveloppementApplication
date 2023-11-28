/*
 * Client.java                                      14 nov. 2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni "copyleft" 
 */
package modele;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/** TODO commenter la responsabilité de la classe (SRP)
 * @author rayanibrahime
 *
 */
public class Client {
    /** TODO commenter le rôle de la méthode
     * @param IP L'adresse IP du serveur auquel se connecter.
     * @return true si le fichier est envoyé avec succès, false sinon.
     */

	public static boolean envoie(String IP) {
		boolean estEnvoye = false;
		try {
            // Connexion au serveur sur le port défini dans Serveur.NUM_PORT
            Socket socket = new Socket();
            
            socket.connect(new InetSocketAddress(IP, Serveur.NUM_PORT), 100);

            // Obtention du flux de sortie vers le serveur
            BufferedOutputStream sortie = new BufferedOutputStream(socket.getOutputStream());

            // Sélection du fichier CSV à envoyer
            String cheminFichier = "recu.csv";
            FileInputStream entreeFichier = new FileInputStream(cheminFichier);

            // Lecture et envoi du fichier
            byte[] tampon = new byte[1024];
            int octetsLus;
            while ((octetsLus = entreeFichier.read(tampon)) != -1) {
                sortie.write(tampon, 0, octetsLus);
            }

            System.out.println("Fichier envoyé : " + cheminFichier);
            estEnvoye = true;

            // Fermeture des flux et de la socket
            sortie.close();
            entreeFichier.close();

            // Réception de la réponse du serveur
            String reponseServeur = recevoirReponse(socket);
            System.out.println("Réponse du serveur : " + reponseServeur);

            socket.close();

        } catch (IOException e) {
            estEnvoye = false;
        }
		return estEnvoye;
    }

	private static String recevoirReponse(Socket socket) throws IOException {
	    BufferedReader lecteur = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    StringBuilder reponse = new StringBuilder();
	    String ligne;

	    while ((ligne = lecteur.readLine()) != null) {
	        reponse.append(ligne).append("\n");
	    }

	    return reponse.toString();
	}
}
