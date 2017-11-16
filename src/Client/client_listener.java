/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davidren
 */
//because the server can send multiple data and the client cant enter the input 
public class client_listener extends Thread implements Runnable{

    private static BufferedReader Client_input = null;
    //private static int connection;
    
    client_listener (BufferedReader input){
        Client_input = input;
    }
    
    @Override
    public void run(){
        String Display_message;
        
        
        try {
            Display_message = Client_input.readLine();
            while (Display_message != null){
                System.out.println(Display_message);
                Display_message = Client_input.readLine();    
            }
        } catch (IOException ex) {
            Logger.getLogger(client_listener.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
