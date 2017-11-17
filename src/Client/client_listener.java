/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidren
 */
//because the server can send multiple data and the client cant enter the input 
public class client_listener extends Thread implements Runnable{
    private static PrintWriter Client_output = null;
    private static Scanner KeyboardEntry = new Scanner(System.in);
    private static BufferedReader Client_input = null;
    //private static int connection;
    
    client_listener (PrintWriter output){
        Client_output = output;
    }
    //need to implement a quit exce
    @Override
    public void run(){
        
        getInput();
       
    }
    void getInput(){
//        String Display_message;
//        
//        
//        try {
////            Display_message = Client_input.readLine();
//            while ((Display_message = Client_input.readLine()) != null){
//                System.out.println(Display_message);
//                
////                Display_message = Client_input.readLine();    
//            }
//            Client_input.close();
//        }  catch (IOException ex) {
//            Logger.getLogger(client_listener.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String Input_message;
        Input_message = KeyboardEntry.nextLine();
        Client_output.println(Input_message);
           while(!Input_message.equals("QUIT")){

               Input_message = KeyboardEntry.nextLine();
               Client_output.println(Input_message);

           }
           Client_output.close();
    }
}
