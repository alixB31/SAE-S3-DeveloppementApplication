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
import java.io.PrintStream;
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
			//TODO s'envoyer
            // Connexion au serveur sur le port défini dans Serveur.NUM_PORT
            Socket socket = new Socket();
            
            socket.connect(new InetSocketAddress(IP, Serveur.NUM_PORT), 100);

            // Obtention du flux de sortie vers le serveur
            BufferedOutputStream sortie = new BufferedOutputStream(socket.getOutputStream());

            // Sélection du fichier CSV à envoyer
            String cheminFichier = "aEnvoyer.csv";
            FileInputStream entreeFichier = new FileInputStream(cheminFichier);

            // Lecture et envoi du fichier
            byte[] tampon = new byte[1024];
            int octetsLus;
            String cleAEnvoyer = Chiffrement.chiffrementDiffieHellman();
            int clePubliqueS = cleClient(socket, cleAEnvoyer);
            int cleGlobale = Chiffrement.dechiffrementDiffieHellman(clePubliqueS);
            
            while ((octetsLus = entreeFichier.read(tampon)) != -1) {
                sortie.write(tampon, 0, octetsLus);
            }

            System.out.println("Fichier envoyé : " + cheminFichier);
            estEnvoye = true;

            // Fermeture des flux et de la socket
            sortie.close();
            entreeFichier.close();
            socket.close();

        } catch (IOException e) {
            estEnvoye = false;
        }
		return estEnvoye;
    }
	
	private static int cleClient(Socket serveur, String cleAEnvoyer) {
		int clePubliqueS = 0;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(serveur.getInputStream()));
	        PrintStream out = new PrintStream(serveur.getOutputStream());
	        out.println(cleAEnvoyer);
	        clePubliqueS = Integer.parseInt(in.readLine());
		} catch (IOException e) {
            System.err.println("Communication avec le serveur impossible");
            clePubliqueS = 0;
        }
		return clePubliqueS;
	}
}
