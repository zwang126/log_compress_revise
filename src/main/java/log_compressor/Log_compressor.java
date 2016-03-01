/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_compressor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import static log_compressor.event_checker.event_checker;
import static log_compressor.import_file.import_file;
import static log_compressor.space_checker.space_checker;

/**
 *
 * @author Zan_Wang
 */
public class Log_compressor {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
      
        actions();
        
    }
    public static void actions() throws IOException{
         String choice = null;
        System.out.println("welcome to pci it log compression system"); 
        System.out.println("what would you like to look on?");
        System.out.println(" Event or Spce (E/S)");
        Scanner input = new Scanner(System.in);
        choice = input.next().toString().toUpperCase();
        switch(choice){
            case "E":
                event_checker();
                break;
            case "S":
                space_checker();
                break;
            default:
                System.out.println("please try again");
                actions();
                break;
        }
    }

}
