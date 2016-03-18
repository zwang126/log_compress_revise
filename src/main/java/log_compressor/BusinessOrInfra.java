/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log_compressor;

import java.util.Scanner;

/**
 *
 * @author Zan_Wang
 */
public class BusinessOrInfra {
    public boolean isInfrastructure(){
        String choice = null;
        boolean result = false;
        System.out.println("Is this input data relate to infrastructure?(Y/N)");
        Scanner input = new Scanner(System.in);
        choice = input.next().toString().toUpperCase();
        if(choice.equals("Y")){
            result = true;
        }
        return result;
    }
    
}
