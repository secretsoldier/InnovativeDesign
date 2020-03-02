package FileExplorer;

import javax.swing.*;
import java.awt.*;

public class FileTablePanel extends JPanel {
    private FileTable table;
    private ExplorerPath text;{
        text = new ExplorerPath();
    } // Textfield Settings
    private ExplorerButton backButton, forwardButton, refreshButton, upButton;{
        Font font = new Font("Explorer Font", 1, 24);

        backButton = new ExplorerButton("←", font);
        forwardButton = new ExplorerButton("→", font);
        refreshButton = new ExplorerButton("↻", font);
        upButton = new ExplorerButton("↑", font);
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

        this.add(trayPanel);

    } // Panel Settings
    public FileTablePanel(AbstractFileTableModel tableModel){
        table = new FileTable(tableModel);
        this.add(table);
    }
}
