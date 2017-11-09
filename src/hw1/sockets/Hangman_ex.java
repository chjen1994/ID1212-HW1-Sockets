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
        String file_location = "/Users/davidren/Desktop/words.txt";// open up the file
    
        String[] read_line = new String[numWords];//read individual line
    
        try {
            FileReader file_reader = new FileReader (file_location);
            
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
        int Score = 0;
 
        char[] letter = new char [life];
        int counter = 0;
        while(counter !=life){
            //store the letters in the array 
            letter[counter] = read_line[randomNum].charAt(counter);
            counter++;
        }
        
        System.out.println(read_line[randomNum]);
        System.out.println(life);
        
        
        //copy to chosen word for later use
        String copyWord = read_line[randomNum];
        int length = copyWord.length();
        int original_length = copyWord.length();
        
        String  record;//record the right word and display it
        Scanner userEntry = new Scanner(System.in);//read the input from the keyborad
        StringBuilder sb = new StringBuilder(read_line[randomNum]);//put the random word into a string builder object to better delete words

       
        
        System.out.println(life+ " Letters Word"); 
        
        int num_dash = life;
        System.out.println("_ _ _ _ _ _");
        
        System.out.print("Enter: "); 
            String message = userEntry.nextLine();
        
        
        while(userEntry.nextLine()!="QUIT"){
            //the start of the game
            //show the dash
            
            System.out.print("Enter: "); 
            String message = userEntry.nextLine();
            
            for (int n = 0; n<length; n++){
                if (message.charAt(n) == sb.charAt(n)){
                //if the leters enter equals to the readline word then delete the letters using  sb.deleteCharAt(1)
                sb.deleteCharAt(0);
                length--;
                }
            }
            if(length == original_length){
                life--;
            } else{
                original_length = length;
            }
            
            
            if (sb.length() == 0){
               System.out.print("YOU WON");
               Score++;
            }
            else{
                System.out.println("Remaining life: "+life);
            }
        }
    }
}
