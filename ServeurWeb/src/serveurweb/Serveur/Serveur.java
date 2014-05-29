package serveurweb.Serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Corinne
 */
public class Serveur extends Thread {

    // ATTRIBUTS
    private ServerSocket socketServeur;
    private int portServeur;
    private int SO_TIMEOUT;

    // Taille des entêtes ?????????????????,
    //private final static int HEADER_SIZE = 4;
    // Taille d'un bloc de données
    // private final static int BLOCK_SIZE = 512;
    // CONSTRUCTEUR
    public Serveur() {
        portServeur = 80;
        SO_TIMEOUT = 180000; // 3 minutes

        initSocketServeur();
    }

    // METHODES
    public void initSocketServeur() {
        try {
            socketServeur = new ServerSocket(80, 6);
        } catch (IOException ex) {
            System.err.println("Port déjà occupé : " + ex.getMessage());
        }

    }

    public void initSocket() {
        try {

            socketServeur.setSoTimeout(SO_TIMEOUT);
        } catch (IOException ex) {
            System.err.println("Port déjà occupé : " + ex.getMessage());
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket connexion;
                connexion = socketServeur.accept();

                // Création d'une communication
                Communication com = new Communication(connexion);
                // activation de la communication
                com.start();

            } catch (IOException ex) {
                System.out.println("Problème de connection : " + ex.getMessage());
            }
        }

    }

}
