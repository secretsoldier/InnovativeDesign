package sortinggg;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author 18074751
 */

public class NPieChartC extends JComponent { // Stands for Nebula Pie Chart Component; Copied and altered from MyCanvas.java
    private ArrayList<NPieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public NPieChartC(Map<String, NPieChartObj> map){
        fileList.addAll(map.values());
        
        JComponent pieChart = new ChartG(), keyChart = new KeyG();

        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); // Creates padding around the pie chart components
        this.setLayout(new GridLayout(0, 2, 25, 0)); // Splits the frame in half

        this.add(pieChart);
        this.add(keyChart);
    }

    private class ChartG extends JComponent { // Stands for Pie Chart Graphic, this will be the actual pie chart

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            double totalSpace = 0;
            for (NPieChartObj obj : fileList) { // Finds the total space taken by the files in the list
                totalSpace += obj.extensionTotalLength;
            }

            int pos = 90; // Starts pos at 90 so the arcs get drawn from the top center of the pie chart
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.segmentColour);
                if (obj.equals(fileList.get(fileList.size() - 1))){
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, 450-pos); // 450 is 360 plus the initial 90, this is to fix weird rounding bug
                } else {
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, (int)(obj.extensionTotalLength /(totalSpace/360)));
                } // Segment of pie chart

                pos += (obj.extensionTotalLength / totalSpace)*360;
            }

            g.setColor(Color.black); // Set colour for border
            for (int i = 0; i < 4; i++) {
                g.drawOval(this.getX() - i , this.getY() - i, this.getWidth() + i + i, this.getHeight() + i + i); // Draw border for pie chart
            }
        }
    }

    private class KeyG extends JPanel { // Stands for Key Graphics, this will be the key for the pie chart

        private class KeyComponent extends JPanel { // This holds the key colour and the key text

            private class KeyColourComponent extends JComponent { // This is the key colour square
                private Color colour;

                public KeyColourComponent(Color colour){
                    this.colour = colour;

                    // This is to limit the size of the boxes to 10 by 10 pixels
                    //this.setMaximumSize(new Dimension(10, 10));
                    //this.setMinimumSize(new Dimension(10, 10));
                    //this.setPreferredSize(new Dimension(10, 10));

                    this.setAlignmentY(Component.CENTER_ALIGNMENT);
                }

                @Override
                public void paint(Graphics g) {
                    super.paint(g);
                    g.setColor(colour);
                    g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); // Fills key square

                    g.setColor(Color.black); // Set black for border
                    g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); // Draw key border
                }
            }

            private JLabel keyString;
            private KeyColourComponent colourBox;

            public KeyComponent(NPieChartObj info){
                colourBox = new KeyColourComponent(info.segmentColour);
                this.keyString = new JLabel(info.extension + "  -  " + (double)info.extensionTotalLength /1024 + "/kb");
                this.keyString.setAlignmentY(Component.CENTER_ALIGNMENT);

                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                this.add(colourBox);
                this.add(Box.createRigidArea(new Dimension(10, 0))); // Adds space between the box and text
                this.add(keyString);

                this.setAlignmentX(Component.LEFT_ALIGNMENT); // Makes sure that all keys align to the far left
            }

        }

        public KeyG(){
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
            
             
            
            for (NPieChartObj obj : fileList){
                this.add(new KeyComponent(obj));
                this.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

    }

    public JComponent getPieChartComponent(){
        return this.chart;
    }
    public JComponent getKeyComponent(){
        return this.key;
    }

}