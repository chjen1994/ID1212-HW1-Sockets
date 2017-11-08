/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw1.sockets;

import java.io.*;
import java.net.*;
import java.util.*;


/**
 *
 * @author ChunHeng Jen
 */
public class Hangman_client {
   // number of words in the word txt file
   private static final int PORT = 1234;// port number between the server and the client
   private static InetAddress host;
   public static void main(String[] args){
       try {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException uhEx)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
       accessServer();
   }
   private static void accessServer(){
       Socket Client_Link = null;
       try{
           Client_Link = new Socket(host,PORT);
           //Set up input & output streams 
           Scanner Client_input = new Scanner( Client_Link.getInputStream( ) );
           PrintWriter Client_output = new PrintWriter ( Client_Link.getOutputStream( ), true );
           //get input from keyboard
           Scanner KeyboardEntry = new Scanner(System.in);

           //read and send data 
           
           System.out.println("******HANGMAN*******");
           System.out.println("RULE:Guess the word by either entering a letter or the word");
           System.out.println("Leave the game by entering QUIT");
           
           String Display_message, Input_message;
           do{
               System.out.println("Guess the word");
               Input_message = KeyboardEntry.nextLine();
               Client_output.println(Input_message);//output the word from the keyboard to the server
               Display_message = Client_input.nextLine();//get the word from the server 
               System.out.println(Display_message);//Display the message from the server
           }while(!Input_message.equals("QUIT"));
           
       } catch(IOException ioEx){
            ioEx.printStackTrace();
       }
       finally{
           try {
               System.out.println("Leaving the game...");
               Client_Link.close();
           }catch(IOException ioEx){
               System.out.println("Unable to leave the game!!");
               System.exit(1);
           }
       }
   }
}
