package FileExplorer;

import innovativedesign.CreateFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileTableMouseActions extends MouseAdapter {
    Component parent;
    public FileTableMouseActions(Component parent) {
        this.parent = parent;
    }
    
    private File fileAtCursor(MouseEvent e, FileTable table) {
        int row = table.rowAtPoint(e.getPoint());
        return table.getValueAt(row);
    }

    private File fileAtCursor(MouseEvent e){
        FileTable table = (FileTable)e.getSource();
        return fileAtCursor(e, table);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        FileTable table = (FileTable)e.getSource(); File selectedFile = fileAtCursor(e, table);

        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){ // Double click
            if (selectedFile.isDirectory()) {
                if (table.getModel() instanceof DefaultFileTableModel) {
                    ((DefaultFileTableModel)table.getModel()).addRoot(selectedFile);
                } else
                    table.setModel(new DefaultFileTableModel(selectedFile));
            } else if (selectedFile.isFile()){
                try {
                    Desktop.getDesktop().open(selectedFile);
                } catch (Exception exc){
                    System.err.println(exc.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        File selectedFile = fileAtCursor(e);

        if (e.getButton() == MouseEvent.BUTTON3){ // Right click menu
            class FileContext extends JPopupMenu{
                JMenuItem rename = new JMenuItem("Rename"), delete = new JMenuItem("Delete");
                JMenuItem createFolder = new JMenuItem("Create Folder");
                {
                    rename.addActionListener((ActionEvent e) -> {
                        Object[] options = {"Confirm", "Cancel"};
                        JTextField message = new JTextField();
                        message.setText(selectedFile.getName());
                        int result = JOptionPane.showConfirmDialog(parent, message, "Rename", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION){
                            String path = selectedFile.getParentFile().getPath();
                            if (!selectedFile.renameTo(new File(path + message.getText()))) {
                                JOptionPane.showMessageDialog(parent, "Renaming has been unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            System.out.printf("\"%s\" has been renamed to \"%s\"\n", selectedFile.getName(), message.getText());
                        }
                    });

                    delete.addActionListener((ActionEvent e) -> {
                        Object[] options = {"Yes", "Cancel"};
                        int result = JOptionPane.showOptionDialog(parent, "Are you sure you want to delete this file?",
                                "Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]);
                        if (result == JOptionPane.YES_OPTION){
                            System.out.printf("\"%s\" has been deleted\n", selectedFile.getName());
                            if (selectedFile.delete())
                                JOptionPane.showMessageDialog(parent, String.format("\"%s\" has been deleted", selectedFile.getName()));
                        }
                    });
                    
                    createFolder.addActionListener((ActionEvent e) -> {
                        CreateFolder.createFolder(parent, selectedFile.getParentFile());
                    });
                    
                    
                    this.add(rename);
                    this.add(createFolder);
                    this.add(delete);
                }
            }
            FileContext menu = new FileContext();
            menu.pack();
            menu.setVisible(true);
            menu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
}