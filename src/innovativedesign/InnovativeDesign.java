/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import java.io.File;
import java.util.*;
/**
 *
 * @author 17067582
 */
public class InnovativeDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        System.out.println("Enter the file path");
        String dir = io.nextLine();
        
        File folder = new File(dir);
        //Sort by Name
        if(folder.isDirectory()){
            List listFile = Arrays.asList(folder.list());
            
            System.out.println("Ascending order listing:");
            Collections.sort(listFile);
            for(Object s:listFile){
                System.out.println(s);
            }
            
            System.out.println("");
            System.out.println("");
            
            System.out.println("Descending order listing:");
            Collections.sort(listFile,Collections.reverseOrder());
            for(Object s:listFile){
                System.out.println(s);
            }
        }
        //Sort by date modified
        File[] files = folder.listFiles();
        System.out.println("Sort files in ascending order based on last modification");
        
        Arrays.sort(files,LastModifiedFileComparator.LASTMODIFIED_COMPARATOR);
        for(int i=0;i<files.length;i++){
            File file=files[i];
            System.out.printf("File: %s - " + new Date(file.lastModified()) + "\n", file.getName());
        }
        System.out.println("");
        System.out.println("Sort files in descending order based on last modification");
        Arrays.sort(files,LastModifiedFileComparator.LASTMODIFIED_REVERSE);
        for(int i=0;i<files.length;i++){
            File file=files[i];
            System.out.printf("File: %s - " + new Date(file.lastModified()) + "\n", file.getName());

        }
    }
    
}
