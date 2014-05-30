/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serveurweb.Client;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainClient {
    public static void main(String[] args)
    {
        Client c;
        try 
        {
            InetAddress server =  InetAddress.getByName("127.0.0.1");
            int port = 80;
            c = new Client(server,port);
            c.run();
        } catch (UnknownHostException ex) 
        {
            System.err.println(ex.getMessage());
        } 
        
        
    }
    
}
