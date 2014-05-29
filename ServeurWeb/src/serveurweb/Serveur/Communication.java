/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveurweb.Serveur;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Corinne
 */
public class Communication extends Thread {

    private Socket socket;
    private BufferedOutputStream outputStream;
    private BufferedInputStream inputStream;

    public Communication(Socket connexion) {
        socket = connexion;
    }

    @Override
    public void run() {

        //Information
        System.out.println("Connecté : " + socket.toString());

        //    try {
        while (true) {// tant que connection non close
            try {
                //Recuperation de la demande
                /////////////////////////////////////////////////////////         InputStream is = socket.getInputStream();
                //BufferedInputStream buff = new BufferedInputStream(is);
                //DataInputStream dis = new DataInputStream(is);

         //       message = is.read();
                /*while (buff.available() != 0) {
                 System.out.println(buff.read());
                 }*/
                /*String message = null;*/
                // read until a single byte is available
                //while (in.available() !=-1) {
                // read the byte and convert the integer to character
                //char c = (char) in.read();
                // print the characters
                //  System.out.println("Char: " + c);
                //message = message + c;
                //}
                //in.close();
                // Affichage de la demande
                // System.out.println("Message : " + message);
                /*byte[] by = new byte[1024];
                 int j = 0;
                 while ((j = in.read(by)) != -1) {
                 String st = new String(by, 0, j);
                 System.out.print(st);
                 }*/
    ///////////////////////////////////////            StringBuffer response = new StringBuffer();
                //  try {
                //BufferedInputStream buff = new BufferedInputStream(System.in);
                /*          int in = 0;
                 char inChar;
                 while(in!= )
              
                
                 in = buff.readline();
                 inChar = (char) in;
                 //buff.close();
                 System.out.println("Message: " + response.toString());*/
                /* } catch (IOException e) {
                 System.out.println("Exception: " + e.getMessage());
                 }*/

                /*  } catch (IOException ex) {
                 Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
                 }*/
                //     }
            } catch (Exception ex) {
                Logger.getLogger(Communication.class.getName()).log(Level.SEVERE, null, ex);
            }

            while (true) {
                try {
                    OutputStream o = socket.getOutputStream();
                    outputStream = new BufferedOutputStream(o);
                    InputStream i = socket.getInputStream();
                    inputStream = new BufferedInputStream(i);
             

                Byte[] recu = RecevoirData();
                String s = recu.toString();
                System.out.println(s);
                
                   } catch (Exception e) {
                    System.err.println("piou" + e.getMessage());
                }
            }
        }
    }

    private Byte[] RecevoirData() {
        ArrayList<Byte> recu = new ArrayList<>();
        byte temp;
        try {
            while ((temp = (byte) inputStream.read()) != -1) {
                recu.add(temp);
            }
        } catch (SocketTimeoutException e) {
            System.err.println("le serveur ne répond pas" + e.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return (Byte[]) recu.toArray();

    }

    private void envoyerReq() {
        String data = "GET http://www.univ-lyon1.fr/test.html";
        System.out.println("sur le point d'envoyer");
        try {
            outputStream.write(data.getBytes());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("envoyé");
    }

    private void fermerConnexion() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
