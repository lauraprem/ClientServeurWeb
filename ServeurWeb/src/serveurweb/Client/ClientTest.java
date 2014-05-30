/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurweb.Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientTest {

    private Socket socket;
    private InetAddress server;
    private int port;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    DataOutputStream outToServer;

    public ClientTest(InetAddress s, int p) {
        try {
            server = s;
            port = p;

            initSocket();
            OutputStream o = socket.getOutputStream();
            out = new BufferedOutputStream(o);
            outToServer = new DataOutputStream(o);
            InputStream i = socket.getInputStream();
            in = new BufferedInputStream(i);
        } catch (Exception e) {
            System.err.println("piou" + e.getMessage());
        }
    }

    private void initSocket() {
        try {
            socket = new Socket(server, port);
            socket.setSoTimeout(10000000);
        } catch (SocketException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void run() {
        Byte[] recu;
        envoyerReq();
        while (true) {
            //recu = RecevoirData();
            // Ecoute du Client

            BufferedReader IN;
            try {
                IN = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String[] str = new String[16];
                for (int i = 0; i < str.length; i++) {
                    str[i] = IN.readLine();
                    if (!str[i].equals("")) {
                        System.out.println("Message du Serveur : " + str[i]);
                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void envoyerReq() {
        String data = "GET http://www.univ-lyon1.fr/test.html HTTP1.0" + "\r\n\r";
        try {
            outToServer.write(data.getBytes());
            //out.write(data.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("envoyé");
    }

    private Byte[] RecevoirData() {
        ArrayList<Byte> recu = new ArrayList<>();
        byte temp;
        try {
            while ((temp = (byte) in.read()) != -1) {
                recu.add(temp);
            }
        } catch (SocketTimeoutException e) {
            System.err.println("le serveur ne répond pas" + e.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
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
