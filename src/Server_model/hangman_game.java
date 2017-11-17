/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server_model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author davidren
 */
public class hangman_game {
    private static String[] choosenWord = new String [1000000];
    //private Socket client;
    private Scanner Server_input;
    private PrintWriter Server_output;
    private StringBuilder dash;   
    private int life;
    private String Current_word;
    private Random rand = new Random();
    private int  randomNum;
    private int number;
    
    public hangman_game(Scanner input, PrintWriter output){
        Server_input = input;
        Server_output = output;
    }
    
    public void storeWord(){
        String read_file = "words.txt";// open up the file
        
        try{
            FileReader file_reader = new FileReader (read_file);
            BufferedReader bufferedReader = new BufferedReader(file_reader);
            number = 0;
           
           while((choosenWord[number] = bufferedReader.readLine()) != null) { 
               //System.out.println(choosenWord[number]);
                   number++;
            }
           //System.out.println(number);
           bufferedReader.close();
        } catch(FileNotFoundException ex){
            System.out.println(
                "Unable to open file '");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
            //handle reading the file
    }
    public void getWord(){
        randomNum = rand.nextInt(number) + 0;
          //get a reandom word
        Current_word = choosenWord[randomNum];
          //get the life of one attempt
        
        life = Current_word.length();
        
        dash = new StringBuilder(Current_word);
        
        //load the dash
        for (int n = 0; n<life; n++){
            dash.setCharAt(n, '-');
        }
    }
    public void playGame(){
        //out put the dash
        //System.out.println( "dash : "+dash);
        
        int length = life;
        int score = 0;
        Server_output.println("******HANGMAN*******");
        Server_output.println("RULE:Guess the word by either entering a letter or the word");
        Server_output.println("You must guess the whole word in order to get the points");
        Server_output.println("Leave the game by entering QUIT");
        Server_output.println("Remaining attempt: "+ life +"    Score:"+score);
        Server_output.println( "dash : "+dash);
        Server_output.println("Guess the word");
        Server_output.flush();
//        System.out.println("Current word: "+Current_word +"  life:"+ life + "  score:" + score+ "  length:"+ length);
//        System.out.println("You are now playing hangman!!!!");
        //System.out.println("Guess the word: ");
        
        String input_Word = new String(Server_input.nextLine());
        String QUIT = new String("QUIT");
        while (!input_Word.equals(QUIT)) {
             //read and send data 
             //Repeat above until 'QUIT' sent by client...
            int bool = 0;
             //read and send data 
             //Repeat above until 'QUIT' sent by client...
              if (input_Word.length()==1){
                  //if guessed any letter right, the length will decrease
                  for(int j = 0; j<Current_word.length(); j++){
                      if (input_Word.charAt(0) == Current_word.charAt(j) ){
                          if (input_Word.charAt(0) != dash.charAt(j)){
                            dash.setCharAt(j, Current_word.charAt(j));
                            
                            bool = 1;
                          }else{
                            bool = 2;  
                          }
                        
                      }
                  }
                  
              }
             if(bool == 0 && life!=1 && !input_Word.equals(Current_word)){
            //if guessed wrong
                life--;
                Server_output.println( "dash : "+dash);
                Server_output.flush();
                Server_output.println("Guessed Wrong...");
                Server_output.flush(); 
             }
             else if (bool == 2){
                 //if guessed the letter already
                //life--;
                Server_output.println( "dash : "+dash);
                Server_output.println("You have guessed this letter already");
             }
             else if (input_Word.equals(Current_word)){
                 //if guessed the whole word right
                  Server_output.println( "dash : "+Current_word+"!!!!");
                  Server_output.println("Congrates!!!");
                  score++;
                  getWord();
                  
                  
              }
             else if (dash == new StringBuilder(Current_word)){
                  Server_output.println( "dash : "+Current_word+"!!!!");
                  Server_output.println("Congrates!!!");
                  score++;
                  //if guessed the whole word right
                  getWord();
             }
             else if(life == 1){
                 score--;
                 Server_output.println("Sorry...The word is '"+Current_word+"'....Generating new word ");
                  getWord();
                  
                 
                  Server_output.println( "dash : "+dash);
             }
              else{
                  //show the location of the guessed leter to the user
                  Server_output.println("Guessed right!");
                  Server_output.println( "dash : "+dash);
                  
              }
            Server_output.println("  Remaining attempt: "+ life +"    Score:"+score);
            Server_output.flush();
            Server_output.println("Guess the word");
            Server_output.flush();
            input_Word = Server_input.nextLine();//continue reading from the client 
          }
          
            Server_output.println("Toatl Score: "+score);
            Server_output.println("QUITING");
            Server_output.close();
            Server_input.close();
    }

}
