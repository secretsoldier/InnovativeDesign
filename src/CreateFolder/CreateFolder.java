/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateFolder;
import java.awt.Component;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author 17067582
 */
public class CreateFolder {

    /**
     * @param args the command line arguments
     */
    public static void createFolder(Component component) {
        boolean success= false;
        String nameOfFolder = JOptionPane.showInputDialog("Name of folder");
        while (!success){
            
            File f = new File("H:\\"+nameOfFolder);
        
            if (f.mkdirs()){
                JOptionPane.showMessageDialog(component, String.format("\"%s\" has been created!", nameOfFolder));
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
