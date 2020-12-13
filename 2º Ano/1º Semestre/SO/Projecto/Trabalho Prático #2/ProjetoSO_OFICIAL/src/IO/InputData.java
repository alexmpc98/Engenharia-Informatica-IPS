/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author hugomodesto
 */
public class InputData {
    
    public static int[][] inputFileName(Scanner reader) throws IOException{
        System.out.println("Insert map file name:");
        String filePath = System.getProperty("user.dir")+"/maps/";
        String inputText;
        BufferedReader in = null;
        while(true){
            inputText = reader.next();
            try{
                in = new BufferedReader(new FileReader(filePath+inputText));
                return Import.importFile(in);
            }catch(FileNotFoundException e){
                System.out.println("You must insert a valid file");
            }
        }
    }
    
    public static int inputInt(Scanner reader){
        String inputText;
        while(true){
            inputText = reader.next();
            try{
                return Integer.parseInt(inputText);
            }catch(NumberFormatException e){
                System.out.println("You must insert a valid int");
            }
        }
    }
}
