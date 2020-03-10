/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package createfolder;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author 17067582
 */
public class CreateFolder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean success= false;
        Scanner io = new Scanner(System.in);
            System.out.println("enter the folder name:");
        while (!success){
            
            String name = io.nextLine();
            File f = new File("H:\\"+name);
        
            if (f.mkdirs()){
                System.out.println("Directory is created.");
                success = true;
            }
            else{
                System.out.println("Directory cannot be created.");
                System.out.println("Please enter a new directory.");
            } 
        }
        
    }
    
}
