/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 *
 * @author 18074751
 */
public class NFileExplorerFrame extends JInternalFrame {
    public NFileExplorerFrame(){
        super("File Explorer", true, true, true, true);
        this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        this.setSize(500, 500);
        this.add(new JScrollPane(new NFileTable(new DefaultFileTableModel(new File("/C:/")))), BorderLayout.CENTER);
    }
}
