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
    
    // CONSTRUCTEUR
    public Serveur() {
        portServeur = 8080;
        SO_TIMEOUT = 180000; // 3 minutes

        initSocketServeur();
    }

    // METHODES
    public void initSocketServeur() {
        try {
            socketServeur = new ServerSocket(portServeur, 6);// nombre maximum de client connecté
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
                // Attente de demande de connexion
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
