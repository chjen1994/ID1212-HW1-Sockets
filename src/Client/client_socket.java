/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author ChunHeng Jen
 */
public class client_socket {
   // number of words in the word txt file
   private static Thread thrd = null;
   private static final int PORT = 1234;// port number between the server and the client
   private static InetAddress host;
   public static void main(String[] args){
       try {
           //consider when not 
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
       client_thread client_handler = new client_thread(PORT, host);
       client_handler.start();
   }
}