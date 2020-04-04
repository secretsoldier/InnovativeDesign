package FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import innovativedesign.Resources;

public class FileTablePanel extends JPanel {
    private JTabbedPane tabbedPane = new JTabbedPane();
    private FileTable table;
    private ExplorerPath text;{
        text = new ExplorerPath();
    } // Textfield Settings
    private ExplorerButton backButton, forwardButton, refreshButton, upButton;{
        Insets inset = new Insets(1,1,1,1);

        ImageIcon normal  = Resources.getIcon("back-arrow.png");
        ImageIcon hovered = Resources.getIcon("back-arrow_h.png");
        backButton = new ExplorerButton(normal, hovered);
        backButton.setMargin(inset);
        backButton.addActionListener((ActionEvent e) -> {
            Component x;
            if (tabbedPane.getSelectedComponent() instanceof JScrollPane &&
                    (x = ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView()) instanceof FileTable &&
                    ((FileTable)x).getModel() instanceof DefaultFileTableModel)
                ((DefaultFileTableModel)getModel()).setLast();
        });

        normal  = Resources.getIcon("next-arrow.png");
        hovered = Resources.getIcon("next-arrow_h.png");
        forwardButton = new ExplorerButton(normal, hovered);
        forwardButton.setMargin(inset);
        forwardButton.addActionListener((ActionEvent e) -> {
            Component x;
            if (tabbedPane.getSelectedComponent() instanceof JScrollPane &&
                    (x = ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView()) instanceof FileTable &&
                    ((FileTable)x).getModel() instanceof DefaultFileTableModel)
                ((DefaultFileTableModel)getModel()).setNext();
        });

        normal  = Resources.getIcon("sync.png");
        hovered = Resources.getIcon("sync_h.png");
        refreshButton = new ExplorerButton(normal, hovered);
        refreshButton.setMargin(inset);
        refreshButton.addActionListener((ActionEvent e) -> {
            Component x;
            if (tabbedPane.getSelectedComponent() instanceof JScrollPane &&
                    (x = ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView()) instanceof FileTable &&
                    ((FileTable)x).getModel() instanceof DefaultFileTableModel)
                ((DefaultFileTableModel)getModel()).refreshRoot();
        });

        normal  = Resources.getIcon("up-arrow.png");
        hovered = Resources.getIcon("up-arrow_h.png");
        upButton = new ExplorerButton(normal, hovered);
        upButton.setMargin(inset);
        upButton.addActionListener((ActionEvent e) -> {
            Component x;
            if (tabbedPane.getSelectedComponent() instanceof JScrollPane &&
                    (x = ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView()) instanceof FileTable &&
                    ((FileTable)x).getModel() instanceof DefaultFileTableModel) {
                DefaultFileTableModel model = (DefaultFileTableModel) getModel();
                java.io.File parent = ((DefaultFileTableModel) getModel()).getCurrentRoot().getParentFile();
                if (parent == null) {
                    table.setModel(new DriveFileTableModel());
                } else {
                    model.addRoot(parent);
                }
            }
        });

    } // Button Settings
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel trayPanel = new JPanel();
        trayPanel.setLayout(new BoxLayout(trayPanel, BoxLayout.LINE_AXIS));
        trayPanel.add(backButton);
        trayPanel.add(forwardButton);
        trayPanel.add(upButton);
        trayPanel.add(text);
        trayPanel.add(refreshButton);

        trayPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 12));
        this.add(trayPanel);

    } // Panel Settings

    public FileTablePanel(AbstractFileTableModel tableModel, Component parent){
        table = new FileTable(tableModel, parent);
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab(((DefaultFileTableModel)table.getModel()).getCurrentRoot().getPath(), scrollPane);
        this.add(tabbedPane);
    }
    
    public void addExplorerTab(String title, FileTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
        tabbedPane.addTab(title, scrollPane);
    }

    public AbstractFileTableModel getModel(){
        return table.getModel();
    }
}
