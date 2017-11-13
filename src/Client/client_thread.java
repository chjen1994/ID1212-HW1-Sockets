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
 * @author davidren
 */
public class client_thread extends Thread{
    private static int PORT;
    private static InetAddress host;
    private static Socket Client_Link = null;
    private static Scanner Client_input = null;
    private static PrintWriter Client_output = null;
    private static Scanner KeyboardEntry = new Scanner(System.in);
    client_thread (int portNum, InetAddress ip){
        PORT = portNum;
        host = ip;
    }
    @Override
    public void run(){
        
        connectServer();
        GameRule();
        getinput();
       
        
        try{
               System.out.println("Leaving the game...");
               Client_Link.close();
            }catch(IOException ioEx){
               System.out.println("Unable to leave the game!!");
               System.exit(1);
            }
        }
       
    
    void connectServer(){
        try{
            Client_Link = new Socket(host,PORT);
           //Set up input & output streams 
            Client_input = new Scanner( Client_Link.getInputStream( ) );
            Client_output = new PrintWriter ( Client_Link.getOutputStream( ), true );

        } catch(IOException ioEx){
            System.out.println("Connection Refused...");
            System.exit(1);
        }
    }
    void GameRule(){
        System.out.println("******HANGMAN*******");
        System.out.println("RULE:Guess the word by either entering a letter or the word");
        System.out.println("Leave the game by entering QUIT");
    }
    void getinput(){
        
        String Display_message, Input_message;
           do{
               System.out.println("Guess the word");
               Input_message = KeyboardEntry.nextLine();
               Client_output.println(Input_message);//output the word from the keyboard to the server
               Display_message = Client_input.nextLine();//get the word from the server 
               System.out.println(Display_message);//Display the message from the server
               Display_message = Client_input.nextLine();//get the word from the server 
               System.out.println(Display_message);//Display the message from the server
               Display_message = Client_input.nextLine();//get the word from the server 
               System.out.println(Display_message);//Display the message from the server
           }while(!Input_message.equals("QUIT"));
    }
}
