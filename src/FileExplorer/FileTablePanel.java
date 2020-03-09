package FileExplorer;

import javax.swing.*;
import java.awt.*;
import innovativedesign.InnovativeDesign;
import innovativedesign.Resources;

public class FileTablePanel extends JPanel {
    private JScrollPane scrollPane;
    private FileTable table;
    private ExplorerPath text;{
        text = new ExplorerPath();
    } // Textfield Settings
    private ExplorerButton backButton, forwardButton, refreshButton, upButton;{
        Font font = new Font("Explorer Font", 1, 24);
        Insets inset = new Insets(1,1,1,1);

        ImageIcon normal  = Resources.getIcon("back-arrow.png");
        ImageIcon hovered = Resources.getIcon("back-arrow_h.png");
        backButton = new ExplorerButton(normal, hovered);
        backButton.setMargin(inset);

        normal  = Resources.getIcon("next-arrow.png");
        hovered = Resources.getIcon("next-arrow_h.png");
        forwardButton = new ExplorerButton(normal, hovered);
        forwardButton.setMargin(inset);

        normal  = Resources.getIcon("sync.png");
        hovered = Resources.getIcon("sync_h.png");
        refreshButton = new ExplorerButton(normal, hovered);
        refreshButton.setMargin(inset);

        normal  = Resources.getIcon("up-arrow.png");
        hovered = Resources.getIcon("up-arrow_h.png");
        upButton = new ExplorerButton(normal, hovered);
        upButton.setMargin(inset);

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
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
    }
}
