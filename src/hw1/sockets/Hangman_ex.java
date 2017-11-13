/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Reference from caveofprogramming 
 */
package hw1.sockets;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author davidren
 */
public class Hangman_ex {
    private static int numWords = 51528;
    
    private static Scanner KeyboardEntry;
    public static void main(String[] args){
        
        
        String[] choosenWord = new String [10000000];
        
        String read_file = "words.txt";// open up the file
        try{
            FileReader file_reader = new FileReader (read_file);
            BufferedReader bufferedReader = new BufferedReader(file_reader);
            int number = 0;
           
           while((choosenWord[number] = bufferedReader.readLine()) != null) { 
               System.out.println(choosenWord[number]);
                   number++;
            }
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
        Random rand = new Random();
        int  randomNum = rand.nextInt(51528) + 0;
          //get a reandom word
        String Current_word = choosenWord[randomNum];
          //get the life of one attempt
        
        int life = Current_word.length();
        
        StringBuilder dash = new StringBuilder(Current_word);
        
        //load the dash
        for (int n = 0; n<life; n++){
            dash.setCharAt(n, '-');
        }
        //out put the dash
        System.out.println( "dash : "+dash);
        
        int length = life;
        int score = 0;
        System.out.println(
                "Current word: "+Current_word +"  life:"+ life + "  score:" + score+ "  length:"+ length);
        System.out.println(
                "You are now playing hangman!!!!");
        System.out.println(
                "Guess the word: ");
        KeyboardEntry = new Scanner(System.in);
        String input_Word = new String(KeyboardEntry.nextLine());
           //initialize the game by creating the word, read the file, 
        String QUIT = new String("QUIT");
        //System.out.println(input_Word); 
        while (!input_Word.equals(QUIT)) {
            
             int bool = 0;
             
             //read and send data 
             //Repeat above until 'QUIT' sent by client...
              if (input_Word.length()==1){
                  //if guessed any letter right, the length will decrease
                  for(int j = 0; j<Current_word.length(); j++){
                      if (input_Word.charAt(0) == Current_word.charAt(j)){
                          //Current_word.deleteCharAt(j);
                          dash.setCharAt(j, Current_word.charAt(j));
                          length--;
                          bool = 1;
                      }
                  }
                  //System.out.println( "dash : "+dash);
              }
             if(bool == 0 && life!=1 && !input_Word.equals(Current_word)){
            //if guessed wrong
                life--;
                System.out.println( "dash : "+dash);
                System.out.println(
                "Guessed Wrong...");
                
                
             }
             else if (input_Word.equals(Current_word)){
                  System.out.println( "dash : "+Current_word+"!!!!");
                  //if guessed the whole word right
                  
                  randomNum = rand.nextInt(51528) + 0;
                  Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  System.out.println("Congrates!!!");
                  score++;
                  
              }
             else if(life == 1){
                 score--;
                 System.out.println("Sorry...");
                 System.out.println("The word is "+Current_word);
                 randomNum = rand.nextInt(51528) + 0;
                  Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  
                  
                  System.out.println("Generating new word");
                  System.out.println( "dash : "+dash);
//                  System.out.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
//                  System.out.println(
//                "Guess the word: ");
//                  input_Word = KeyboardEntry.nextLine();//continue reading from the client
             }
              else{
                  //show the location of the guessed leter to the user
                  System.out.println( "dash : "+dash);
//                  System.out.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
//                  System.out.println(
//                "Guess the word: ");
//                  input_Word = KeyboardEntry.nextLine();//continue reading from the client
              } 
            System.out.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
            System.out.println("Guess the word: ");
            input_Word = KeyboardEntry.nextLine();//continue reading from the client 
          }
          
            System.out.println("Toatl Score: "+score);
                

       //Close the connection after the dialogue is complete
    }  
    
}
