/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidren
 */


//have another multithread for litsening from the server

public class client_thread extends Thread implements Runnable{
    private static int PORT;
    private static InetAddress host;
    private static Socket Client_Link = null;
    private static BufferedReader Client_input = null;
    private static PrintWriter Client_output = null;
    private static Scanner KeyboardEntry = new Scanner(System.in);
    
    client_thread (int portNum, InetAddress ip){
        PORT = portNum;
        host = ip;
    }
    @Override
    public void run(){
        
        try{
            
            connectServer();
            
            getinput();
            
            
            try{
                System.out.println("Leaving the game...");
                Client_Link.close();
            }catch(IOException ioEx){
                System.out.println("Unable to leave the game!!");
                System.exit(0);
            }
        }catch(IOException ex){
               Logger.getLogger(client_thread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    
    void connectServer(){
        try{
            Client_Link = new Socket(host,PORT);
           //Set up input & output streams 
           //bite not stream
            Client_input = new BufferedReader ( new InputStreamReader(Client_Link.getInputStream( ) ));
            Client_output = new PrintWriter ( Client_Link.getOutputStream( ), true );

        } catch(IOException ioEx){
            System.out.println("Connection Refused...");
            System.exit(1);
        }
    }

   


//have another multithread for litsening from the server
    void getinput() throws IOException{
        String Input_message;
        //int connection = 1;
        //client_listener servere_message_handler = new client_listener(Client_input);
        client_listener servere_message_handler = new client_listener(Client_output);
        servere_message_handler.start();
        
        
//        Display_message = Client_input.readLine();
//        while (Display_message != null){
//            System.out.println(Display_message);
//            Display_message = Client_input.readLine();    
//        }
        String Display_message;
        
        try {
//            Display_message = Client_input.readLine();
            while (Client_Link.isClosed()==false){
                Display_message = Client_input.readLine();
                System.out.println(Display_message);
                
//                    
            }
            Client_input.close();
        }  catch (IOException ex) {
            Logger.getLogger(client_listener.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Input_message = KeyboardEntry.nextLine();
//        Client_output.println(Input_message);
//           while(!Input_message.equals("QUIT")){
//
//               Input_message = KeyboardEntry.nextLine();
//               Client_output.println(Input_message);
//
//           }
//           Client_output.close();
    }
}
