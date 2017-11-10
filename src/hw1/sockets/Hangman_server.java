/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Take refference from textbook for java intrduction
 */
package hw1.sockets;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author ChunHeng Jen
 */
public class Hangman_server {
   
   private static int numWords = 51528;// number of words in the word txt file
   private static ServerSocket Hangman_server_socket;//decalre the socket 
   private static final int PORT = 1234;// port number between the server and the client

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
            Socket linkA = Hangman_server_socket.accept();
            System.out.println("\nNew client accepted.\n");
            handle_Hangman_client handler = new handle_Hangman_client(linkA);
            handler.start();
      }while(true);  
    }
}


class handle_Hangman_client extends Thread {
    private static String[] choosenWord = new String [1000000];
    private Socket client;
    private Scanner Server_input;
    private PrintWriter Server_output;
       
    public handle_Hangman_client(Socket socket){
        client = socket;
        try {
            Server_input = new Scanner(client.getInputStream());
            Server_output = new PrintWriter(client.getOutputStream(),true);
        } catch(IOException ioEx){
            ioEx.printStackTrace();
        }   
    }
    
    public void run(){
        //read file
        String read_file = "/Users/davidren/Desktop/words.txt";// open up the file
        
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
        
        String input_Word = new String(Server_input.nextLine());
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
                Server_output.println(
                "Guessed Wrong...");
                
                
             }
             else if (input_Word.equals(Current_word)){
                  Server_output.println( "dash : "+Current_word+"!!!!");
                  //if guessed the whole word right
                  
                  randomNum = rand.nextInt(51528) + 0;
                  Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  Server_output.println("Congrates!!!");
                  score++;
                  
              }
             else if(life == 1){
                 score--;
                 Server_output.println("Sorry...");
                 Server_output.println("The word is "+Current_word);
                 randomNum = rand.nextInt(51528) + 0;
                 Current_word = choosenWord[randomNum];
                  life = Current_word.length();
                  dash = new StringBuilder(Current_word);
                  //load the dash
                  for (int n = 0; n<life; n++){
                    dash.setCharAt(n, '-');
                  }
                  
                  
                  Server_output.println("Generating new word");
                  Server_output.println( "dash : "+dash);
//                  System.out.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
//                  System.out.println(
//                "Guess the word: ");
//                  input_Word = KeyboardEntry.nextLine();//continue reading from the client
             }
              else{
                  //show the location of the guessed leter to the user
                  Server_output.println( "dash : "+dash);
                  
              }
            Server_output.println("Current word: "+Current_word+"  Remaining attempt: "+ life +"    Score:"+score);
            Server_output.println("Guess the word: ");
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