package serveurweb.Serveur;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Corinne
 */
public class Communication extends Thread {

    private Socket socket;
    private int SO_TIMEOUT;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedOutputStream outDonnees;

    //Root est le chemin courrent
    private static final File SERVEUR_ROOT = new File("./src/serveurweb/Serveur/Contenue/");
    private static final String FICHIER_DEFAUT = "fichierRacine.html";

    // client
    private String commande;
    private String fichierDemande;
    private byte[] fichierDonnees;

    public Communication(Socket connexion) {
        SO_TIMEOUT = 180000; // 3 minutes
        socket = connexion;
        try {
            socket.setSoTimeout(SO_TIMEOUT);
        } catch (SocketException ex) {
            System.out.println("Erreur socket time-out : " + ex.getMessage());
        }
        in = null;
        out = null;
        outDonnees = null;
    }

    @Override
    public void run() {

        //Information
        System.out.println("Connecté : " + socket.toString());

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            outDonnees = new BufferedOutputStream(socket.getOutputStream());

            //recupere la premiere ligne de la requete du client
            String ligne = in.readLine();

            DecoupageRequeteClient(ligne);

            //Verification que la commande existe
            //Il n'y a que les requetes GET qui est implemente
            if (!commande.equals("GET")) {
                erreurCommande();
            } else {
                // Traitement de la requete
                //Si la commande est GET, on envoie le contenue du fichier
                if (commande.equals("GET")) {
                    int code = recuperationFichier();
                    switch (code) {
                        case 200:
                            envoiFichier();
                            break;
                        case 404:
                            erreurFichierNonTrouve();
                            break;
                        case 500:
                            break;
                    }
                }
            }

        } catch (IOException ex) {
            System.out.println("Erreur : " + ex.getMessage());//500
        } finally {
            close(in);
            close(out);
            close(outDonnees);
            close(socket);
        }
        System.out.println("Deconnecté : " + socket.toString());
    }

    private void DecoupageRequeteClient(String ligne) {
        if (ligne != null) {
            //creer StringTokenizer pour parser la requete
            StringTokenizer parse = new StringTokenizer(ligne);
            //recupere la commande du client
            commande = parse.nextToken().toUpperCase();
            //recupere le fichier demande par le client
            fichierDemande = parse.nextToken().toLowerCase();
        }else{
            commande = "";
        }
    }

    private void erreurCommande() {
        //Reponse au client : commande non implemente
        int code = 501;
        out.println("HTTP/1.0 " + getNomCode(code));
        out.println("Server: Java HTTP Server CorinneLaura 1.0");
        out.println("Date: " + new Date());
        out.println("Content-Type: text/html");
        out.println();
        out.println("<HTML>");
        out.println("<HEAD><TITLE>Not Implemented</TITLE>"
                + "</HEAD>");
        out.println("<BODY>");
        out.println("<H2>" + getNomCode(code) + ": " + commande
                + " method.</H2>");
        out.println("</BODY></HTML>");
        out.flush();
    }

    private String getNomCode(int code) {
        String str;
        switch (code) {
            case 200:
                str = code + " OK";
                break;
            case 404:
                str = code + " File Not Found";
                break;
            case 400:
                str = code + " Bad Request";
                break;
            case 501:
                str = code + " Not Implemented";
                break;
            case 500:
                str = code + " Internal Server Error";
                break;
            default:
                str = code + " Bad Request";
        }

        return str;
    }

    private int recuperationFichier() {
        int code = 0;
        if (fichierDemande.endsWith("/")) {
            //file par defaut
            fichierDemande += FICHIER_DEFAUT;
        }

        //Creer un fichier
        File fichier = new File(SERVEUR_ROOT, fichierDemande);

        FileInputStream fileIn = null;
        //creeer une liste de byte pour enregister les donnees du fichier
        fichierDonnees = new byte[(int) fichier.length()];

        try {
            //on ouvre le fichier et on le lit
            fileIn = new FileInputStream(fichier);
            fileIn.read(fichierDonnees);

            close(fileIn); //close file input stream
            return 200;
        } catch (FileNotFoundException ex) {
            return 404;
        } catch (IOException ex) {
            return 500;
        }
    }

    /**
     * getContentType returns le (MIME) content type qui correspond à
     * l'extension du fichier.
     *
     * @param fileDemande File requested by client
     */
    public String getContentType(String fichierDemande) {
        if (fichierDemande.endsWith(".htm")
                || fichierDemande.endsWith(".html")) {
            return "text/html";
        } else if (fichierDemande.endsWith(".gif")) {
            return "image/gif";
        } else if (fichierDemande.endsWith(".jpg")
                || fichierDemande.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fichierDemande.endsWith(".class")
                || fichierDemande.endsWith(".jar")) {
            return "applicaton/octet-stream";
        } else {
            return "text/plain";
        }
    }

    /**
     * close method closes stream.
     *
     * @param stream
     */
    public void close(Object stream) {
        if (stream == null) {
            return;
        }

        try {
            if (stream instanceof Reader) {
                ((Reader) stream).close();
            } else if (stream instanceof Writer) {
                ((Writer) stream).close();
            } else if (stream instanceof InputStream) {
                ((InputStream) stream).close();
            } else if (stream instanceof OutputStream) {
                ((OutputStream) stream).close();
            } else if (stream instanceof Socket) {
                ((Socket) stream).close();
            } else {
                System.err.println("Unable to close object: " + stream);
            }
        } catch (Exception e) {
            System.err.println("Error closing stream: " + e);
        }
    }

    /**
     * Informe le client que le fichier demande n'existe pas
     *
     * @param out Client output stream
     * @param file fichier demande par le client
     */
    private void envoiFichier() throws IOException {
        int code = 200;
        // Entete
        out.println("HTTP/1.0 " + getNomCode(code));
        out.println("Server: Java HTTP Server CorinneLaura 1.0");
        out.println("Date: " + new Date());
        out.println("Content-type: " + getContentType(fichierDemande));
        out.println("Content-length: " + (int) fichierDonnees.length);
        out.println(); //ligne blanche entre l'entête et le contenue
        out.flush(); //flush les characteres (output stream buffer)

        // Contenue
        outDonnees.write(fichierDonnees, 0, (int) fichierDonnees.length); //ecrit le fichier
        outDonnees.flush(); //flush binary
    }

    /**
     * Informe le client que le fichier demande n'existe pas
     *
     * @param out Client output stream
     * @param file fichier demande par le client
     */
    private void erreurFichierNonTrouve() {
        int code = 404; // code erreur

        //Enete
        out.println("HTTP/1.0 " + getNomCode(code));
        out.println("Server: Java HTTP Server CorinneLaura");
        out.println("Date: " + new Date());
        out.println("Content-Type: text/html");
        out.println();

        //Contenue
        out.println("<HTML>");
        out.println("<HEAD><TITLE>File Not Found</TITLE>"
                + "</HEAD>");
        out.println("<BODY>");
        out.println("<H2>404 File Not Found: " + fichierDemande + "</H2>");
        out.println("</BODY>");
        out.println("</HTML>");

        // envoi sur le flux
        out.flush();
    }
}
