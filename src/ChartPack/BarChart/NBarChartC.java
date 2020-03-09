package ChartPack.BarChart;

import ChartPack.PieChart.NPieChartObj;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 17007976
 */
public class NBarChartC extends JComponent {
    private ArrayList<NPieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public NBarChartC(Map<String, NPieChartObj> map){
        fileList.addAll(map.values());
        
        JComponent BarChart = new ChartG(), keyChart = new KeyG();

        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2)); // Creates padding around the Bar chart components
        this.setLayout(new GridLayout(0, 2, 25, 0)); // Splits the frame in half

        this.add(BarChart);
        this.add(keyChart);
    }

    private class ChartG extends JComponent { // Stands for Bar Chart Graphic, this will be the actual Bar chart

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            double totalSpace = 0;
            for (NPieChartObj obj : fileList) { // Finds the total space taken by the files in the list
                totalSpace += obj.extensionTotalLength;
            }
            
            int pos = 0; // Starts pos at 90 so the arcs get drawn from the top center of the Bar chart
            long height =  fileList.get(0).extensionTotalLength;
            for (NPieChartObj obj : fileList) {
                g.setColor(obj.segmentColour);
                float preh = (((float)obj.extensionTotalLength/(float)height) * (float) 300);
                g.fillRect(pos,0, 25, (int)(preh));
                pos += 25;
            }
            
      
        }
    }

    private class KeyG extends JPanel { // Stands for Key Graphics, this will be the key for the Bar chart

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
            
            fileList.sort((NPieChartObj obj1, NPieChartObj obj2) -> obj2.extensionTotalLength.compareTo(obj1.extensionTotalLength));
            
            for (NPieChartObj obj : fileList){
                this.add(new KeyComponent(obj));
                this.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }

    }

    public JComponent getBarChartComponent(){
        return this.chart;
    }
    public JComponent getKeyComponent(){
        return this.key;
    }

}

