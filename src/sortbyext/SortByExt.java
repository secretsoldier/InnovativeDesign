/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortbyext;
import org.apache.commons.io.FilenameUtils;
import static org.apache.commons.io.comparator.ExtensionFileComparator.*;
import java.io.File;
import java.util.*;
/**
 *
 * @author 17067582
 */
public class SortByExt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner io = new Scanner(System.in);
        System.out.println("Enter the file path");
        String dir = io.nextLine();
        
        File folder = new File(dir);
        List listFile = Arrays.asList(folder.list());
        if(folder.isDirectory()){
            File[] files=folder.listFiles(File::isFile);
            //sort in ascending order
            Arrays.sort(files,EXTENSION_COMPARATOR);
            SortByExt.displayFileOrder(files);
            //sort in descending order
            Arrays.sort(files,EXTENSION_REVERSE);
            SortByExt.displayFileOrder(files);
        }
    }
    public static void displayFileOrder(File[]files){
        System.out.printf("%-20s | %s%n", "Name", "Ext");
        System.out.println("--------------------------------");
        for (File file : files) {
            System.out.printf("%-20s | %s%n", file.getName(),
                    FilenameUtils.getExtension(file.getName()));
        }
    }
    
}
