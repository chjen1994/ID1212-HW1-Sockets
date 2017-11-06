/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Take refference from textbook for java intrduction
 */
package hw1.sockets;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author davidren
 */
public class Hangman_server {
   private static int numWords = 51528;// number of words in the word txt file
   private static ServerSocket Hangman_server_socket;
   private static final int PORT = 1234;// port number between the server and the client

   public static void main(String[] args){

       try {
            Hangman_server_socket = new ServerSocket(PORT);//the port number is currenttly just a radom number 1234
           

       } catch (IOException ex) {
            System.out.println("Unable to attach to port!");
            System.exit(1);
        }
       do {
            handle_Hangman_client();
      }while (true);
       
   
    }
   private static void handle_Hangman_client(){
       
       Socket linkA = null;
       try{
           
           linkA = Hangman_server_socket.accept();//accept connection
                    
       } catch (IOException ex) {
           System.out.println("Unable to accept connection!");
           System.exit(1);
       }
       //Set up input & output streams
       Scanner Server_input = new Scanner( linkA.getInputStream( ) ); 
       PrintWriter Server_output = new PrintWriter ( linkA.getOutputStream( ), true );


       //read and send data   
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

    private static Socket accept() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
    
