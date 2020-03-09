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
    }
    public ExplorerButton(Icon icon){
        this.setIcon(icon);
    }
    public ExplorerButton(Icon icon1, Icon icon2){
        this.setIcon(icon1);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(icon2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(icon1);
            }
        });
    }

    @Override
    public void setIcon(Icon defaultIcon) {
        if (defaultIcon.getIconHeight() != this.getHeight()){
            // TODO Icon resizing
        }
        if (defaultIcon.getIconWidth() != this.getWidth()){
            // TODO Icon resizing
        }
        super.setIcon(defaultIcon);
    }
}
