/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;
import java.awt.Component;
import java.io.*;
import javax.swing.JOptionPane;

/**
 *
 * @author 17067582
 */
public class CreateFolder {
    public static void createFolder(Component component, File directory) {
        boolean success = false;
        String nameOfFolder = JOptionPane.showInputDialog(component, "Name of folder");
        while (!success && nameOfFolder != null){
            assert directory.isDirectory();
            File f = new File(directory.getPath() + nameOfFolder);
        
            if (f.mkdirs()){
                JOptionPane.showMessageDialog(component, String.format("\"%s\" has been created", nameOfFolder));
                System.out.printf("\"%s\" has been created\n", nameOfFolder);
                success = true;
            } else {
                System.out.println("Directory cannot be created.");
            } 
        }
    }
}
