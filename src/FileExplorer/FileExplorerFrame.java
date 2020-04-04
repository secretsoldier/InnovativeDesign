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
public class FileExplorerFrame extends JInternalFrame {
    FileTablePanel tablePanel;

    public FileExplorerFrame(String name, AbstractFileTableModel tableModel){
        super(name);
        tablePanel = new FileTablePanel( tableModel , this);
        this.add( tablePanel, BorderLayout.CENTER);
    }

    public AbstractFileTableModel getModel(){
        return tablePanel.getModel();
    }
    
    public FileTablePanel getTablePanel() {
        return tablePanel;
    }
}
