
package serveurweb.Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Client
{

    private Socket socket;
    private InetAddress server;
    private int port;
    private int timeOut;
    private DataOutputStream out;
    private DataInputStream in;
    private BufferedReader inEntete;
    private String nomFichierDistant;
    private String nomFichierLocal;
    private File fichier;
    private int longueurDonnees;

    private String extension;
    private String mime;
    private int codeOp;
    private int indicedata;

    public Client(InetAddress s, int p)
    {
        try
        {
            server = s;
            port = p;
            timeOut = 2;
            fichier = new File("./src/serveurweb/Client/Contenu/");
            nomFichierDistant = new String();
            longueurDonnees = 0;
            extension = null;
            indicedata = -1;
        } catch (Exception e)
        {
            System.err.println("piou" + e.getMessage());
        }
    }

    /**
     * Permet d'initialiser le socket et la connexion
     *
     * @return 0 si tout s'est bien passé 1 s'il y a eu un problème de connexion
     * avec le serveur 2 IO exception
     */
    @SuppressWarnings("empty-statement")
    public int initSocket()
    {
        try
        {
            socket = new Socket(server, port);
            socket.setSoTimeout(timeOut);
            OutputStream o = socket.getOutputStream();
            out = new DataOutputStream(o); //BufferedWriter(new OutputStreamWriter(o));
            InputStream i = socket.getInputStream();
            inEntete = new BufferedReader(new InputStreamReader(i));
            //    inDonnees = new BufferedInputStream(socket.getInputStream());
            //in = new DataInputStream(inDonnees);
            in = new DataInputStream(i);
        } catch (SocketException ex)
        {
            System.err.println(ex.getMessage());

            return 1;
        } catch (IOException ex)
        {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
            return 2;
        }
        return 0;
    }

    /**
     *
     * @return 0 = ok; 1 = timeout; 2 = IOexception; 3 = fichier existe déjà; 4
     * = données recu incorectes
     */
    public int run(String nom, String nomLocal)
    {
        nomFichierLocal = nomLocal;
        nomFichierDistant = nom;

        // envoie requete
        byte[] recu;
        envoyerReqGet();
        try
        {
            // reception 
            recu = recevoirData();
            if(codeOp !=200)
            {
                return codeOp;
            }

            if (recu.length == 1)
            {
                fermerConnexion();
                return recu[0];
            }
            
            String f = fichier.getPath() + "\\" + nomFichierLocal + extension;
            fichier = new File(f);
            if (fichier.exists())
            {
                return 3;
            }
            f = fichier.getPath() + "\\" + nomFichierLocal;
            fichier = new File(f);

            // ecriture du fichier
            int res = this.ecrireFichier(recu);
            if (res != 0)
            {
                fermerConnexion();
                return res;
            }

        } catch (SocketTimeoutException e)
        {
            System.err.println("le serveur ne répond pas " + e.getMessage());
            fermerConnexion();
            return 1;
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
            fermerConnexion();
            return 2;
        }

        fermerConnexion();

        return 0;
    }

    private int ecrireFichier(byte[] recu)
    {

        try
        {
            /*StringTokenizer st = new StringTokenizer(fichier.getPath(), ".");
            String f = "." + st.nextToken() + extension;

            fichier = new File(f);
            fichier.createNewFile();*/
            FileOutputStream monFileOutputStream = new FileOutputStream(fichier, true);
            for (int i = 0; i < longueurDonnees; i++)
            {
                monFileOutputStream.write(recu[i]);
            }

        } catch (IOException ex)
        {
            return 2;
        }
        return 0;
    }

    private void envoyerReqGet()
    {
        String data = "GET /" + nomFichierDistant + " HTTP1.0\r\n\r";
        System.out.println("sur le point d'envoyer");
        try
        {
            out.write(data.getBytes());
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        } catch (Exception ex)
        {
            System.err.println(ex.getMessage());
        }
        System.out.println("envoyé");
    }

    /**
     *
     * @return 1 = timeout 2 = IOexception le tableau de byte recu si ok
     */
    private byte[] recevoirData() throws SocketTimeoutException, IOException
    {
        ArrayList<String> recu = new ArrayList<>();
        String temp;
        boolean encore = true;
        //bloquant tant qu'il n'y a rien a lire ou time out
        while (!inEntete.ready());
        //lecture de l'entete
        while (inEntete.ready() && encore)
        {
            temp = inEntete.readLine();
            recu.add(temp);
            if (temp.equals(""))
            {
                encore = false;
            }
        }
        int n = lectureEntete(recu);

        if (n != 0)
        {
            byte[] b = new byte[1];
            b[0] = (byte) n;
            return b;
        }

        // Methode 1 et 2 marche 2ième fois
        byte[] res = new byte[longueurDonnees];

        in.readFully(res);
        // methode 3 ????

        return res;

    }

    public int lectureEntete(ArrayList<String> entete)
    {
        String length = null;
        String code = null;
        for (int i = 0; i < entete.size(); i++)
        {
            if (entete.get(i).startsWith("Content-Type:"))
            {
                extension = getType(entete.get(i));
            }
            if (entete.get(i).startsWith("Content-length: "))
            {
                length = entete.get(i).split("Content-length: ")[1];
            }
            if (entete.get(i).startsWith("HTTP/1.0"))
            {
                code = entete.get(i);
            }
            if (entete.get(i).equals(""))
            {
                indicedata = i + 1;
                break;
            }
        }
        try
        {
            code = code.split(" ")[1];
            codeOp = Integer.parseInt(code);
            longueurDonnees = Integer.parseInt(length);
        } catch (NumberFormatException e)
        {
            System.err.println(e.getMessage());
        }
        if (indicedata == -1 || extension == null || length == null)
        {
            return 4;
        }
        return 0;
    }

    public String getType(String dataRecu)
    {
        String[] ext;

        ext = dataRecu.split(": ");
        mime = ext[1];
        switch (ext[1])
        {
            case "text/html":
                return ".html";
            case "image/gif":
                return ".gif";
            case "image/jpeg":
                return ".jpg";
            default:
                return ".txt";
        }
    }

    private void fermerConnexion()
    {
        try
        {
            inEntete.close();
            in.close();
            out.close();
            System.out.println("stream fermés, sur le point de fermer la co\n");
            socket.close();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public String getExtension()
    {
        return extension;
    }

    public String getMime()
    {
        return mime;
    }

}
