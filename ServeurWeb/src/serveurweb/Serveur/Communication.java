package serveurweb.Serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Corinne
 */
public class Communication extends Thread {

    private Socket socket;
    private BufferedReader IN;
    private PrintWriter OUT;
    private String fichier;
    private File fichier1;

    public Communication(Socket connexion) {
        socket = connexion;
        try {
            IN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            OUT = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        //Information
        System.out.println("Connecté : " + socket.toString());

        try {
            // Ecoute du Client
            String data = recevoirDonnees();

            // Traitement des donnees recu
            int code = traitementDonnees(data);

            // Reponse au Client
            String[] dataAEnvoyer = creationReponse(code, fichier);
            envoyerDonnees(dataAEnvoyer);

            //   socket.close();
        } catch (Exception e) {
            System.out.println("erreur");
        }
    }

    private String recevoirDonnees() {
        String str = "";
        try {
            str = IN.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Message reçu : " + str);
        return str;
    }

    private void envoyerDonnees(String[] s) {
        for (int i = 0; i < s.length; i++) {
            OUT.println(s[i]);
        }
        OUT.flush();
    }

    private void fermerConnexion() {
        try {
            IN.close();
            OUT.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private int traitementDonnees(String s) {
        String[] tabData = s.split(" ");
        if (tabData.length == 3) {
            if (tabData[0].equals("GET")) {
                if (tabData[1].equals("/")) {
                    fichier1 = new File("./src/serveurweb/Serveur/test.html");
                    fichier = getContenueFile(fichier1);
                }
                return 200;
            }
            return 0;
        }
        return 400;
    }

    public static byte[] recuperationFichier(String nomFichier) {
        try {
            FileInputStream f = new FileInputStream(nomFichier);
            int size = 1024, i, j;
            boolean loop = true;
            int lastSize = 0;
            byte[] file;
            byte[] temp = new byte[size];
            ArrayList<byte[]> tempList = new ArrayList<>();
            do {
                lastSize = f.read(temp);
                if (lastSize < size) {
                    loop = false;
                } else {
                    tempList.add(temp);
                }
            } while (loop);
            file = new byte[size * tempList.size() + lastSize];
            for (i = 0; i < tempList.size(); i++) {
                for (j = 0; j < size; j++) {
                    file[i * tempList.size() + j] = tempList.get(i)[j];
                }
            }
            for (j = 0; j < lastSize; j++) {
                file[i * tempList.size() + j] = temp[j];
            }
            return file;
        } catch (FileNotFoundException ex) {
            return null;// 404
        } catch (IOException ex) {
            return null;
        }
    }

    private String getNomCode(int code) {
        String str;
        switch (code) {
            case 200:
                str = "OK";
                break;
            case 404:
                str = "File not found";
                break;
            case 400:
                str = "Bad Request";
                break;
            default:
                str = "Bad Request";
        }

        return str;
    }

    public String[] creationReponse(int code, String message) {
        DateFormat DateFormatFR = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL, new Locale("FR", "fr"));
        String[] str = new String[8];
        str[0] = "HTTP/1.1 " + code + " " + getNomCode(code);
        str[1] = "Date: " + DateFormatFR.format(new Date());
        str[2] = "Server: CorinneLaura";
        str[3] = "Last-modified: " + DateFormatFR.format(new Date(fichier1.lastModified()));
        str[4] = "Content-Length: " + message.length();
        str[5] = "Content-Type: text/html";
        str[6] = "";
        str[7] = message;

        return str;
    }

    public static String getContenueFile(File aFile) {
        StringBuilder contents = new StringBuilder();

        try {
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            try {
                String line = null;
                while ((line = input.readLine()) != null) {
                    contents.append(line);
                    contents.append(System.getProperty("line.separator"));
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return contents.toString();
    }
}
