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
    private static String[] choosenWord = null;
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
        
        try {
           FileReader file_reader = new FileReader (read_file);
           BufferedReader bufferedReader = new BufferedReader(file_reader);
           int number = 0;
           while((choosenWord[number] = bufferedReader.readLine()) != null) { 
               System.out.println(choosenWord[number]);
                   number++;
            }
            bufferedReader.close();  
        }catch (FileNotFoundException ex) {
            System.out.println(
            "Unable to open file");
        } catch (IOException ex) {
            System.out.println(
            "Error reading file");
        }
          ;  //handle reading the file
        Random rand = new Random();
        int  randomNum = rand.nextInt(51528) + 0;
          //get a reandom word
        String Current_word = choosenWord[randomNum];
          //get the life of one attempt
        int life = Current_word.length();
        int score = 0;
        String input_Word = Server_input.nextLine();//get the message from the client
           //initialize the game by creating the word, read the file, 
        do {
             //read and send data 
             //Repeat above until 'QUIT' sent by client...
             if(input_Word.charAt(0) != Current_word.charAt(0)){
            //if guessed wrong
                life--;
                Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                input_Word = Server_input.nextLine();//continue reading from the client 
             }
             else if (input_Word == Current_word){
                  //if guessed the whole word right
                  randomNum = rand.nextInt(51528) + 0;
                  Current_word = choosenWord[randomNum];
                  score++;
                  Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                  input_Word = Server_input.nextLine();//continue reading from the client
              }
              else{
                  //show one leter at the user
                  
                  Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                  input_Word = Server_input.nextLine();//continue reading from the client
              } 
          }while (!input_Word.equals("QUIT"));
          
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