/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurweb.Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class Client {

    private Socket socket;
    private InetAddress server;
    private int port;
    private int timeOut;
    private BufferedOutputStream out;
    private BufferedInputStream in;

    public Client(InetAddress s, int p) {
        try {
            server = s;
            port = p;
            timeOut = 1500;

        } catch (Exception e) {
            System.err.println("piou" + e.getMessage());
        }
    }

    /**
     * Permet d'initialiser le socket et la connexion
     *
     * @return 0 si tout s'est bien passé 1 s'il y a eu un problème de connexion
     * avec le serveur 2 IO exception
     */
    public int initSocket() {
        try {
            socket = new Socket(server, port);
            socket.setSoTimeout(timeOut);
            OutputStream o = socket.getOutputStream();
            out = new BufferedOutputStream(o);
            InputStream i = socket.getInputStream();
            in = new BufferedInputStream(i);
        } catch (SocketException ex) {
            System.err.println("pouet  " + ex.getMessage());

            return 1;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return 2;
        }
        return 0;
    }

    /**
     * 
     * @return  1 = timeout 2 = IOexception 0 = ok
     */
    public int run() {
        Byte[] recu;
        envoyerReqGet("http://www.univ-lyon1.fr/test.html");
        recu = RecevoirData();

        if (recu.length == 1) {
            return recu[0];
        }
        String s = recu.toString();
        System.out.println(s);
        fermerConnexion();
        return 0;
    }

    private void envoyerReqGet(String site) {
        String data = "GET " + site + " HTTP/1.1";
        byte[] envoi = new byte[data.length()];
        envoi = data.getBytes();
        System.out.println("sur le point d'envoyer");
        try {
            for (int i = 0 ; i<data.length() ; i++)
            {
                out.write(envoi[i]);
            }
          //  out.write(data.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("envoyé");
    }

    /**
     *
     * @return 1 = timeout 2 = IOexception le tableau de byte recu si ok
     */
    private Byte[] RecevoirData() {
        ArrayList<Byte> recu = new ArrayList<>();
        byte temp;
        try {
            while ((temp = (byte) in.read()) != -1) {
                recu.add(temp);
            }
        } catch (SocketTimeoutException e) {
            System.err.println("le serveur ne répond pas " + e.getMessage());
            Byte[] b = new Byte[1];
            b[0] = 1;
            return b;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            Byte[] b = new Byte[1];
            b[0] = 2;
            return b;
        }

        return (Byte[]) recu.toArray();

    }

    private void fermerConnexion() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
