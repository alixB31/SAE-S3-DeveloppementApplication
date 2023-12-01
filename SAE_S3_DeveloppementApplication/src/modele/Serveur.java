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
 * Cette classe implémente un serveur permettant la réception de fichiers
 * chiffrés par chiffrement de type Vigenère et Diffie-Hellman.
 * 
 * @author Alix.Brugier
 * @author Mateo.faussurier
 * @author Nathan.Girardin
 * @author Rayan.Ibrahime
 */
public class Serveur {
    /** Port par défaut pour la communication avec le client. */
    public final static int NUM_PORT = 49152;
    
    /** Socket du serveur utilisé pour la communication. */
    private static ServerSocket socketServeur;
    
    /** Socket du client connecté. */
    private static Socket socketClient;
    
    /** Fichier reçu et à traiter. */
    private static File fichierRecu;
    
    /** Flux de sortie pour écrire dans le fichier reçu. */
    private static FileOutputStream sortieFichier;

    /**
     * Gère la connexion avec un client, la réception et le traitement d'un fichier chiffré.
     * 
     * @return Vrai si la réception du fichier s'est déroulée avec succès, faux sinon.
     */
    public static boolean gererConnexion() {
        boolean estRecu = false;
        boolean connexionAnnulee = false;

        try {
            // Création du socket serveur avec un timeout de 60 secondes
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
            // Fermeture du ServerSocket ici après avoir traité la connexion ou après l'annulation
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
     * Traite la réception du fichier chiffré en utilisant les algorithmes de Diffie-Hellman et Vigenère.
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
        
        // Échange de clés Diffie-Hellman
        String cleAEnvoyer = Chiffrement.chiffrementDiffieHellman();
        int clePubliqueC = cleServeur(socketClient, cleAEnvoyer);
        long cleGlobale = Chiffrement.dechiffrementDiffieHellman(clePubliqueC);
        String cleVigenere = Chiffrement.CreationCleVigenere(cleGlobale);  

        while ((octetsLus = entreeClient.read(tampon)) != -1) {
            sortieFichier.write(tampon, 0, octetsLus);
        }

        // Déchiffrement Vigenère du fichier reçu
        Chiffrement.dechiffrementVigenere(cleVigenere);

        // Fermeture des flux et du socket
        entreeClient.close();
        sortieFichier.close();
        
        // Fin de la connexion
        arreterConnexion();
        
        // Importation du fichier qui a été reçu et déchiffré
        Main.stockage.importCSV("Dechiffrer.csv");
        
        supprimerFichiersCrees();
    }

    /**
     * Arrête la connexion avec le client en fermant le socket serveur.
     */
    public static void arreterConnexion() {
        try {
            if (socketServeur != null && !socketServeur.isClosed()) {
                socketServeur.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Échange la clé Diffie-Hellman avec le client.
     * 
     * @param client Le socket du client.
     * @param cleAEnvoyer La clé Diffie-Hellman à envoyer au client.
     * @return La clé publique reçue du client.
     */
    public static int cleServeur(Socket client, String cleAEnvoyer) {
        int clePubliqueC = 0;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintStream out = new PrintStream(client.getOutputStream());
            
            // Envoi de la clé Diffie-Hellman au client
            out.println(cleAEnvoyer);
            
            // Réception de la clé publique du client
            clePubliqueC = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            System.err.println("Communication avec le serveur impossible");
            clePubliqueC = 0;
        }
        return clePubliqueC;
    }
    
    /** 
     * Supprime les fichiers créés pendant le processus.
     */
    public static void supprimerFichiersCrees() {
    	
        String cheminFichierChiffrer = "Chiffrer.csv";
        File fichierChiffrer = new File(cheminFichierChiffrer);
        if (fichierChiffrer.exists()) {
        	fichierChiffrer.delete();
            System.out.println("Fichier temporaire supprimé : " + cheminFichierChiffrer);
        }
        
        String cheminFichierDechiffrer = "Dechiffrer.csv";
        File fichierDechiffrer = new File(cheminFichierDechiffrer);
        if (fichierDechiffrer.exists()) {
        	fichierDechiffrer.delete();
            System.out.println("Fichier aEnvoyer.csv supprimé : " + cheminFichierDechiffrer);
        }
    }
}
