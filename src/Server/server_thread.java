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

/**
 *
 * @author davidren
 */
public class server_thread extends Thread{
    private static String[] choosenWord = new String [1000000];
    private Socket client;
    private Scanner Server_input;
    private PrintWriter Server_output;
       
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
        String read_file = "/Users/davidren/NetBeansProjects/HW1-Sockets/src/Server/words.txt";// open up the file
        
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
        System.out.println("Current word: "+Current_word +"  life:"+ life + "  score:" + score+ "  length:"+ length);
        System.out.println("You are now playing hangman!!!!");
        //System.out.println("Guess the word: ");
        
        String input_Word = new String(Server_input.nextLine());
        System.out.println(input_Word);
           //initialize the game by creating the word, read the file, 
        String QUIT = new String("QUIT");
        //System.out.println(input_Word); 
        while (!input_Word.equals(QUIT)) {
            
             //read and send data 
             //Repeat above until 'QUIT' sent by client...
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
                Server_output.println( "dash : "+dash);
                Server_output.println("Guessed Wrong...");
                
                
             }
             else if (input_Word.equals(Current_word)){
                  Server_output.println( "dash : "+Current_word+"!!!!");
                  Server_output.println("Congrates!!!");
                  score++;
                  //if guessed the whole word right
                  
                  randomNum = rand.nextInt(51528) + 0;
                  Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  
                  
              }
             else if(life == 1){
                 score--;
                 Server_output.println("Sorry...The word is "+Current_word+"....Generating new word ");
                 randomNum = rand.nextInt(51528) + 0;
                 Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  
                  
                 
                  Server_output.println( "dash : "+dash);
//                  System.out.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
//                  System.out.println(
//                "Guess the word: ");
//                  input_Word = KeyboardEntry.nextLine();//continue reading from the client
             }
              else{
                  //show the location of the guessed leter to the user
                  Server_output.println("Guessed right!");
                  Server_output.println( "dash : "+dash);
                  
              }
            Server_output.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
            //Server_output.println("Guess the word: ");
            input_Word = Server_input.nextLine();//continue reading from the client 
          }
          
            Server_output.println("Toatl Score: "+score);
                
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
