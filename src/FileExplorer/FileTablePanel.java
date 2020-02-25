package FileExplorer;

import javax.swing.*;
import java.awt.*;

public class FileTablePanel extends JPanel {
    private FileTable table;
    private ExplorerPath text;{
        text = new ExplorerPath();
    } // Textfield Settings
    private ExplorerButton backButton, forwardButton, refreshButton, upButton;{
        backButton = new ExplorerButton(); forwardButton = new ExplorerButton();
        refreshButton = new ExplorerButton(); upButton = new ExplorerButton();

        Font buttonFont = new Font("Explorer Font", 1, 24);

        backButton.setText("←");
        backButton.setFont(buttonFont);

        forwardButton.setText("→");
        backButton.setFont(buttonFont);

        refreshButton.setText("↻");
        refreshButton.setFont(buttonFont);

        upButton.setText("↑");
        upButton.setFont(buttonFont);
    } // Button Settings
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel trayPanel = new JPanel(new BoxLayout(this, BoxLayout.LINE_AXIS));
        trayPanel.add(backButton);
        trayPanel.add(forwardButton);
        trayPanel.add(upButton);
        trayPanel.add(text);
        trayPanel.add(refreshButton);

        this.add(trayPanel);
        this.add(table);

    } // Panel Settings
    public FileTablePanel(AbstractFileTableModel tableModel){
        table = new FileTable(tableModel);
    }
}
