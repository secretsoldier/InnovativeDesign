package FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExplorerButton extends JButton {
    {
        Dimension size = new Dimension(35, 35);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
        this.setMinimumSize(size);

        this.setBorderPainted(false);
        this.setBackground(Color.white);
        this.setFocusable(false);
    }
    public ExplorerButton(Icon icon){
        this.setIcon(icon);
    }
    public ExplorerButton(Icon icon1, Icon icon2){
        setIcon(icon1);
        setRolloverEnabled(true);
        setRolloverIcon(icon2);
    }
}
