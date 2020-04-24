package FileExplorer;

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

    @Override
    public void mouseClicked(MouseEvent e) {
        FileTable table = (FileTable)e.getSource(); File selectedFile = table.getValueAt(table.getSelectedRow());

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
        } else if (e.getButton() == MouseEvent.BUTTON3){ // Right click menu
            class FileContext extends JPopupMenu{
                JMenuItem rename = new JMenuItem("Rename"), delete = new JMenuItem("Delete");{
                    rename.addActionListener((ActionEvent e) -> {
                        Object[] options = {"Confirm", "Cancel"};
                        Object message = new JTextField();
                        ((JTextField) message).setText(selectedFile.getName());
                        int result = JOptionPane.showConfirmDialog(parent, message, "Rename", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION){
                            String path = selectedFile.getParent();
                            System.out.printf("path: %s\n", path);
                            if (!selectedFile.renameTo(new File(path + ((JTextField) message).getText()))) {
                                    JOptionPane.showMessageDialog(parent, "Renaming has been unsuccessful", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            System.out.printf("\"%s\" has been renamed to \"%s\"\n", selectedFile.getName(), ((JTextField) message).getText());
                        }
                    });
                    this.add(rename);

                    delete.addActionListener((ActionEvent e) -> {
                        Object[] options = {"Yes", "Cancel"};
                        int result = JOptionPane.showOptionDialog(parent, "Are you sure you want to delete this file?",
                                "Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[0]);
                        if (result == JOptionPane.YES_OPTION){
                            System.out.printf("\"%s\" has been deleted\n", selectedFile.getName());
                            selectedFile.delete(); // TODO Show more indication of deletion
                        }
                    });
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