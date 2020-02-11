package sortinggg;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

class NPieChartObj {
    private String extention;
    private Long extentionTotalLength;
    private Color segmentColour;

    public NPieChartObj(String extention, Long extentionTotalLength){
        this.extention = extention;
        this.extentionTotalLength = extentionTotalLength;
    }

    public String getExtention(){
        return this.extention;
    }
    public void setExtention(String extention){
        this.extention = extention;
    }

    public Long getExtentionTotalLength(){
        return this.extentionTotalLength;
    }
    public NPieChartObj setExtentionTotalLength(Long extentionTotalLength){
        this.extentionTotalLength = extentionTotalLength;
        return this;
    }

    public Color getSegmentColour() {
        return segmentColour;
    }
    public NPieChartObj setSegmentColour(Color segmentColour){
        this.segmentColour = segmentColour;
        return this;
    }
}

public class NPieChartC extends JComponent { // Stands for Nebula Pie Chart Component; Copied and altered from MyCanvas.java
    private ArrayList<NPieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public NPieChartC(Map<String, NPieChartObj> map){
        map.forEach((str, obj) -> {
            obj.setSegmentColour(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
        });
        fileList.addAll(map.values());
        
        JComponent pieChart = new ChartG(), keyChart = new KeyG();
        
        // http://developer.classpath.org/doc/java/awt/Container-source.html
        // http://developer.classpath.org/doc/java/awt/BorderLayout-source.html
        LayoutManager2 layoutManager = new BorderLayout(10, 0){ 
            public void layoutContainer(Container target){
                synchronized (target.getTreeLock()){
                    Component north = this.getLayoutComponent(BorderLayout.NORTH),
                            east = this.getLayoutComponent(BorderLayout.EAST),
                            south = this.getLayoutComponent(BorderLayout.SOUTH),
                            west = this.getLayoutComponent(BorderLayout.WEST),
                            center = this.getLayoutComponent(BorderLayout.CENTER);
                    
                    Insets i = target.getInsets();
                    int top = i.top;
                    int bottom = target.getHeight() - i.bottom;
                    int left = i.left;
                    int right = target.getWidth() - i.right;
                    
                    boolean northC = north != null, eastC = east != null, southC = south != null, westC = west != null, centerC = center != null;
                    int activeSlots = (northC ? 1 : 0) + (eastC ? 1 : 0) + (southC ? 1 : 0) + (westC ? 1 : 0) + (centerC ? 1 : 0);
                    
                    int width = (right / (activeSlots)) - (this.getHgap() * (activeSlots - 2)), height = (bottom / (activeSlots)) - (this.getVgap() * (activeSlots - 2));
                    width = (target.getWidth() - i.left - i.right) / (east != null || west != null ? 2 : 1);
                    width = 300;
                    height = (700) / (north != null || south != null ? 2 : 1);
                    height = 300;
                    System.out.printf("%s\n%s, %s -- %s, %s\n", target.toString(), width, height, target.getWidth(), target.getHeight());
                    
                    if (northC){
                        //Dimension n = north.getPreferredSize();
                        north.setBounds((target.getWidth()) - (width), top, width, height);
                        System.out.printf("North - (X: %s, Y: %s)(W: %s, H: %s)\n", (right / 2) - (width / 2), top, width, height);
                    } 
                    if (eastC){
                        //Dimension e = east.getPreferredSize();
                        width = (right - left) / (east != null || west != null ? 2 : 1);
                        east.setBounds(((right - width)/2) + this.getHgap(), top, right, bottom);
                        System.out.printf("East - (X: %s, Y: %s)(W: %s, H: %s)\n", right - width, top, right, bottom);
                    } 
                    if (southC){
                        //Dimension s = south.getPreferredSize();
                        south.setBounds(0, (target.getHeight()/4), 300, (target.getHeight()- (target.getHeight()/3)));
                        System.out.printf("South - (X: %s, Y: %s)(W: %s, H: %s)\n", (0), (0), 0, (target.getHeight()- target.getHeight()));
                    } 
                    if (westC){
                        //Dimension w = west.getPreferredSize();
                        width = right / (east != null || west != null ? 2 : 1);
                        west.setBounds( left, top, width, bottom);
                        System.out.printf("West - (X: %s, Y: %s)(W: %s, H: %s)\n", left, top, width, bottom);
                    } 
                    if (centerC){
                        //Dimension c = center.getPreferredSize();
                        
                    }
                    System.out.printf("\n");
                }
            }
        };
        //layoutManager.addLayoutComponent(BorderLayout.WEST, pieChart);
        //layoutManager.addLayoutComponent(BorderLayout.EAST, keyChart);

        this.setLayout(layoutManager);
        this.add(pieChart, BorderLayout.WEST);
        this.add(keyChart, BorderLayout.EAST);
    }

    private class ChartG extends JComponent { // Stands for Pie Chart Graphic, this will be the actual pie chart

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            double totalSpace = 0;
            for (NPieChartObj obj : fileList) {
                totalSpace += obj.getExtentionTotalLength();
            }

            int pos = 90;
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.getSegmentColour());
                if (obj.equals(fileList.get(fileList.size() - 1))){
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, 450-pos);
                } else {
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, (int)(obj.getExtentionTotalLength()/(totalSpace/360)));
                } // Segment of pie

                pos += (obj.getExtentionTotalLength() / totalSpace)*360;
            }

            g.setColor(Color.black); // Set colour for border
            for (int i = 1; i < 4; i++) {
                g.drawOval(this.getX() - i , this.getY() - i, this.getWidth() + i, this.getHeight()); // Draw border for pie chart
            }
        }
    }

    private class KeyG extends JComponent { // Stands for Key Graphics, this will be the key for the pie chart

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            int counter = 0;
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.getSegmentColour());
                g.fillRect(this.getX(), this.getY() + (25*counter), 10, 10); // Fills key square

                g.setColor(Color.black); // Set black for border
                g.drawRect(this.getX(), this.getY() + (25*counter), 10, 10); // Draw key border
                g.drawString(fileList.get(counter).getExtention() + "   space:  " + obj.getExtentionTotalLength()/1024 + "/kb", this.getX() + 25, this.getY() + 10 + (25*counter)); // Draw key information

                counter++;
            }
        }
    }

    public JComponent getPieChartComponent(){
        return chart;
    }
    public JComponent getKeyComponent(){
        return key;
    }

}
