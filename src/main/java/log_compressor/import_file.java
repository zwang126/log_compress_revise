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

/**
 *
 * @author Zan_Wang
 */
public class import_file {

    public static List<String> import_file(String filename) throws IOException  {
        
        List<String> temps = new ArrayList<String>();

     
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                
                temps.add(line.toString());

            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }
      
        return temps;
    }

}
