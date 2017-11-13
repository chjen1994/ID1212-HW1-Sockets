/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author davidren
 */
public class server_socket {
       private static int numWords = 51528;// number of words in the word txt file
   private static ServerSocket Hangman_server_socket;//decalre the socket 
   private static final int PORT = 1234;// port number between the server and the client
   private static Socket linkA;
   public static void main(String[] args) throws IOException{
        
     
       try {
            Hangman_server_socket = new ServerSocket(PORT);//the port number is currenttly just a radom number 1234
            System.out.println("Opening port");
              //open the port

       }catch (IOException ioEx){
           System.out.println("\nUnable to set up port!");
           System.exit(1);
       }
       do {
            //Wait for client...
            linkA = Hangman_server_socket.accept();
            System.out.println("\nNew client accepted.\n");
            server_thread server_handler = new server_thread(linkA);
            server_handler.start();
            
      }while(true);  
    }
}


