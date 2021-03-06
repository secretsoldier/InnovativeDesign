package PieChartPack;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author 18074751
 */

public class PieChartC extends JComponent { // Stands for Nebula Pie Chart Component; Copied and altered from MyCanvas.java
    private final ArrayList<PieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public PieChartC(Map<String, PieChartObj> map){ // Constructor
        fileList.addAll(map.values()); // Adds all the extension data into the ArrayList "filelist"
        
        JComponent  pieChart = new ChartG(), // Declares the Pie Chart component
                    keyChart = new KeyG();   // Declares the Key component

        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); // Creates padding around the pie chart components
        this.setLayout(new GridLayout(0, 2, 25, 0)); // Splits the frame in half with the GridLayout layout manager

        this.add(pieChart);
        this.add(keyChart);
    }

    private class ChartG extends JComponent { // This component paints the Pie Chart graphics

        private double totalSpace = 0; // Total space of the file extensions
        {
            for (PieChartObj obj : fileList) { // Loops through the file extensions and adds to "totalSpace"
                totalSpace += obj.extensionTotalLength;
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); // Calls JComponent's paint method

            // Pie Chart //
            int pos = 90; // Starts pos at 90 so the arcs get drawn from the top center of the pie chart
            for (PieChartObj obj : fileList) {
                g.setColor(obj.segmentColour);

                if (obj.equals(fileList.get(fileList.size() - 1))){ // Checks if paint is drawing the last segment of the Pie Chart
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, 450-pos); // Fills in remaining space, fix for white space in Pie Chart
                } else {
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, (int)(obj.extensionTotalLength / (totalSpace/360)) ); // Segment of pie chart
                }

                pos += (obj.extensionTotalLength / totalSpace)*360;
            }

            // Border //
            g.setColor(Color.black); // Set colour for border
            for (int i = 0; i < 4; i++) {
                g.drawOval(this.getX() - i , this.getY() - i, this.getWidth() + i + i, this.getHeight() + i + i); // Draw border for pie chart
            }
        }
    }

    private class KeyG extends JPanel { // Key component for Pie Chart

        private class KeyColourComponent extends JComponent { // This is the component showing the segment colour for the Key
            private final Color colour; // Colour of the box

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
                // Colour square //
                g.setColor(colour); // Colour of the component
                g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); // Fills key square

                // Border //
                g.setColor(Color.black); // Set black for border
                g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); // Draw key border
            }
        }

        private class KeyComponent extends JPanel { // Component that holds the coloured square and the label
            private final JLabel keyString;
            private final KeyColourComponent colourBox;

            public KeyComponent(PieChartObj info){
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
            
            for (PieChartObj obj : fileList){
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