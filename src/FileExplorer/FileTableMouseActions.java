package FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileTableMouseActions extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        NFileTable table = (NFileTable)e.getSource(); File selectedFile = (File)table.getValueAt(table.getSelectedRow(), 0);

        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){ // Double click
            if (selectedFile.isDirectory()) {
                ((DefaultFileTableModel) table.getModel()).setRoot(selectedFile);
            } else if (selectedFile.isFile()){
                try {
                    Desktop.getDesktop().open(selectedFile);
                } catch (Exception exc){
                    System.err.printf("%s\n", exc.getLocalizedMessage());
                }
            }
        } else if (e.getButton() == MouseEvent.BUTTON2){ // Right click menu

        }
    }
}
