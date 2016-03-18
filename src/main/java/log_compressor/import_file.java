/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_compressor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static log_compressor.Log_compressor.actions;

/**
 *
 * @author Zan_Wang
 */
public class import_file {
    List<String> temps = new ArrayList<String>();
    public static List<String> import_file(String filename) throws IOException, InterruptedException {

        List<String> temps = new ArrayList<String>();
        if(temps != null && temps.size() != 0){
            return temps;
        }
        File f = new File(filename);
        if (f.exists() && !f.isDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // process the line.

                    temps.add(line.toString());

                }
            } catch (FileNotFoundException fe) {
                fe.printStackTrace();

            }

        } else {
            f.getParentFile().mkdir();
            f.createNewFile();
            import_file(filename);
        }
        if (temps == null || temps.size() == 0) {
            System.out.println("please copy the log you want to deal with to D:/log/input.txt and close that file");
            System.out.println("if finish press Y");
            Scanner input = new Scanner(System.in);
            String choice = input.next().toString().toUpperCase();
            switch (choice) {
                case "Y":
                    
                    import_file(filename);
                    break;
                default:
                    System.out.println("please try again");
                    actions();
            }

        }
        return temps;
        
    }

}
