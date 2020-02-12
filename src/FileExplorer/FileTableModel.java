/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExplorer;

import java.io.File;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 18074751
 */
public class FileTableModel extends DefaultTableModel {
    private File root;
    
    public FileTableModel(File root){
        setRoot(root);
    }
    
    public final void setRoot(File root){
        assert root.isDirectory();
        
        this.root = root;
        new Thread(() -> {
            for (File obj : root.listFiles()){
                this.addRow(new Object[]{obj.getName(), obj.lastModified(), obj.length()});
            }
        });
    }
    public final File getRoot(){
        return this.root;
    }
    
}
