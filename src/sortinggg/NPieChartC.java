package sortinggg;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

class NPieChartObj { // I miss C++
    public String extention;
    public Long extentionTotalLength;
    public Color segmentColour;

    public NPieChartObj(String extention, Long extentionTotalLength){
        this.extention = extention;
        this.extentionTotalLength = extentionTotalLength;
    }
}

public class NPieChartC extends JComponent { // Stands for Nebula Pie Chart Component; Copied and altered from MyCanvas.java
    private ArrayList<NPieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public NPieChartC(Map<String, NPieChartObj> map){
        map.forEach((str, obj) -> {
            obj.segmentColour = (new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
        });
        fileList.addAll(map.values());
        
        JComponent pieChart = new ChartG(), keyChart = new KeyG();

        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        this.setLayout(new GridLayout(0, 2, 25, 0));
        this.add(pieChart);
        this.add(keyChart);
    }

    private class ChartG extends JComponent { // Stands for Pie Chart Graphic, this will be the actual pie chart

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            double totalSpace = 0;
            for (NPieChartObj obj : fileList) {
                totalSpace += obj.extentionTotalLength;
            }

            int pos = 90;
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.segmentColour);
                if (obj.equals(fileList.get(fileList.size() - 1))){
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, 450-pos);
                } else {
                    g.fillArc(this.getX(), this.getY(), this.getWidth(), this.getHeight(), pos, (int)(obj.extentionTotalLength/(totalSpace/360)));
                } // Segment of pie

                pos += (obj.extentionTotalLength / totalSpace)*360;
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
                    this.setMaximumSize(new Dimension(10, 10));
                    this.setMinimumSize(new Dimension(10, 10));
                    this.setPreferredSize(new Dimension(10, 10));

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
                this.keyString = new JLabel(info.extention + "   -  " + (double)info.extentionTotalLength/1024 + "/kb");
                this.keyString.setAlignmentY(Component.CENTER_ALIGNMENT);

                this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

                this.add(colourBox);
                this.add(Box.createRigidArea(new Dimension(10, 0)));
                this.add(keyString);

                this.setAlignmentX(Component.LEFT_ALIGNMENT);
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
        return chart;
    }
    public JComponent getKeyComponent(){
        return key;
    }

}