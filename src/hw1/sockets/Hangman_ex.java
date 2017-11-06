/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Reference from caveofprogramming 
 */
package hw1.sockets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author davidren
 */
public class Hangman_ex {
    private static int numWords = 51528;
    
    private static void readWords(){
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
        } catch (FileNotFoundException ex) {
            System.out.println(
                "Unable to open file");
        } catch (IOException ex) {
             System.out.println(
                "Error reading file");
        }
        Random rand = new Random();
        int  randomNum = rand.nextInt(51528) + 0;
        int life = read_line[randomNum].length();
        
        System.out.println(read_line[randomNum]);
        System.out.println(life);
        
        String  record;//record the right word and display it
        Scanner userEntry = new Scanner(System.in);//read the input from the keyborad
        StringBuilder sb = new StringBuilder(read_line[randomNum]);//put the random word into a string builder object to better delete words

       
        
        System.out.println(life+ " Letters Word"); 
        
        
        while(life!=0 && sb.length() != 0){
            //the start of the game
            
            System.out.print("Enter: "); 
            String message = userEntry.nextLine();
            if (message.charAt(0) == sb.charAt(0)){
                //if the leters enter equals to the readline word then delete the letters using  sb.deleteCharAt(1)
                sb.deleteCharAt(0);

            }
            else{
                life--;
            }
            if (sb.length() == 0){
               System.out.print("YOU WON");
            }
            else{
                System.out.println("Remaining life: "+life);
            }
        }
    }
}
