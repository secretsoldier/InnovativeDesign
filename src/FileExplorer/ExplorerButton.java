package FileExplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ExplorerButton extends JButton {
    public ExplorerButton(String text, Font font){
        this.setText(text);
        this.setFont(font);

        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Rectangle2D dimension = font.getStringBounds(text, frc);

        this.setPreferredSize(new Dimension((int)dimension.getWidth() * 2, (int)dimension.getHeight()));
    }
}
