/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import Server_model.hangman_game;
/**
 *
 * @author davidren
 */
public class server_thread extends Thread implements Runnable{
    private static String[] choosenWord = new String [1000000];
    private Socket client;
    private Scanner Server_input;
    private PrintWriter Server_output;
    private StringBuilder dash;   
    private int life;
    private String Current_word;
    private Random rand = new Random();
    private int  randomNum;
    private int number;
    public server_thread(Socket socket){
        client = socket;
        try {
            Server_input = new Scanner(client.getInputStream());
            Server_output = new PrintWriter(client.getOutputStream(),true);
        } catch(IOException ioEx){
            ioEx.printStackTrace();
            System.exit(1);
        }   
    }
    
    @Override
    public void run(){
        //read file
        hangman_game player = new hangman_game(Server_input,Server_output);
        
        player.storeWord();
        player.getWord();
        player.playGame();
        
        
        try {
            if (client != null){
               System.out.println("Quiting the game...");
               client.close();
           }
        }catch(IOException ioEx){
               System.out.println("Unable to quiting the game!!");
               System.exit(1);
           }
       //Close the connection after the dialogue is complete
    }


}
