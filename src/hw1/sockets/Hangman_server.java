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
   private static String[] choosenWord = null;
   private static int numWords = 51528;// number of words in the word txt file
   private static ServerSocket Hangman_server_socket;//decalre the socket 
   private static final int PORT = 1234;// port number between the server and the client

   public static void main(String[] args){
        
       System.out.println("Opening port");
       //open the port
       try {
            Hangman_server_socket = new ServerSocket(PORT);//the port number is currenttly just a radom number 1234
           

       } catch (IOException ex) {
            System.out.println("Unable to attach to port!");//reference from the textbook
            System.exit(1);
        }
       do {
            handle_Hangman_client();
      }while (true);
       
   
    }
   private static void handle_Hangman_client(){
       
       Socket linkA = null;
       try{
          //load the file 
          read_Hangman_client(choosenWord);  //handle reading the file
          Random rand = new Random();
          int  randomNum = rand.nextInt(51528) + 0;
          //get a reandom word
          String Current_word = choosenWord[randomNum];
          //get the life of one attempt
          int life = Current_word.length();
          int score = 0;
          
          
          
          //Start the connection
          linkA = Hangman_server_socket.accept();//accept connection
          //Set up input & output streams 
          Scanner Server_input = new Scanner( linkA.getInputStream( ) ); 
          PrintWriter Server_output = new PrintWriter ( linkA.getOutputStream( ), true );
          
          
          String input_Word = Server_input.nextLine();//get the message from the client
           //initialize the game by creating the word, read the file, 
          
          //read and send data 
          while(!input_Word.equals("QUIT") ){
              input_Word = Server_input.nextLine();//continue reading from the client
              if(input_Word.charAt(0) != Current_word.charAt(0)){
                  //if guessed wrong
                  life--;
                  Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                  input_Word = Server_input.nextLine();
              }
              else if (input_Word == Current_word){
                  //if guessed the whole word right
                  score++;
                  Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                  input_Word = Server_input.nextLine();
              }
              else{
                  //show one leter at the user
                  
                  Server_output.println(Current_word+"Remaining attempt: "+ life +"    Score:"+score);
                  input_Word = Server_input.nextLine();
              } 
          }Server_output.println("Toatl Score: "+score);
           
       } catch (IOException ex) {
           System.out.println("Unable to accept connection!");
           System.exit(1);
       }  
       //Close the connection after the dialogue is complete
       finally{
           try {
               System.out.println("Quiting the game...");
               linkA.close();
           }catch(IOException ioEx){
               System.out.println("Unable to quiting the game!!");
               System.exit(1);
           }
       }
    }
   
    private static void read_Hangman_client(String[] message){
        
        //read file
        String read_file = "/Users/davidren/Desktop/words.txt";// open up the file
        String[] read_line = new String[numWords];//read individual line
        try {
           FileReader file_reader = new FileReader (read_file);
           BufferedReader bufferedReader = new BufferedReader(file_reader);
           int number = 0;
           while((read_line[number] = bufferedReader.readLine()) != null) { 
               System.out.println(read_line[number]);
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

    }
         
 }