package sortinggg;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Map;

public class NPieChartC extends JComponent { // Stands for Nebula Pie Chart Component; Copied and altered from MyCanvas.java
    private ArrayList<NPieChartObj> fileList = new ArrayList<>();
    private JComponent chart, key;

    public NPieChartC(Map<String, NPieChartObj> map){
        map.forEach((str, obj) -> {
            obj.setSegmentColour(new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256)));
        });
        fileList.addAll(map.values());

        this.setLayout(new BorderLayout());
        this.add(new ChartG(), BorderLayout.CENTER);
        //this.add(new KeyG());
    }

    private class ChartG extends JComponent { // Stands for Pie Chart Graphic, this will be the actual pie chart
        private boolean change = true;

        public ChartG(){
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    change = true;
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    super.componentMoved(e);
                    change = true;
                }

                @Override
                public void componentShown(ComponentEvent e) {
                    super.componentShown(e);
                    change = true;
                }
            });
        }

        private void ChartDraw(Graphics g){
            Dimension prefferedSize = new Dimension(200, 200);

            double totalSpace = 0;
            for (NPieChartObj obj : fileList) {
                totalSpace += obj.getExtentionTotalLength();
            }

            int pos = 90;
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.getSegmentColour());
                if (obj.getExtentionTotalLength().equals(fileList.get(fileList.size() - 1))){
                    g.fillArc(0, 0, prefferedSize.width, prefferedSize.height, pos, 450-pos);
                } else {
                    g.fillArc(0, 0, prefferedSize.width, prefferedSize.height, pos, (int)(obj.getExtentionTotalLength()/(totalSpace/360)));
                } // Segment of pie

                pos += (obj.getExtentionTotalLength()/totalSpace)*360;
            }

            g.setColor(Color.black); // Set colour for border
            for (int i = 1; i < 4; i++) {
                g.drawOval(0 - i, 0 - i, prefferedSize.width + i, prefferedSize.height); // Draw border for pie chart
            }

            change = false;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (true)
                ChartDraw(g);
        }
    }

    private class KeyG extends JComponent { // Stands for Key Graphics, this will be the key for the pie chart
        private boolean change = true;

        public KeyG(){
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    change = true;
                }

                @Override
                public void componentMoved(ComponentEvent e) {
                    super.componentMoved(e);
                    change = true;
                }

                @Override
                public void componentShown(ComponentEvent e) {
                    super.componentShown(e);
                    change = true;
                }
            });
        }

        private void keyDraw(Graphics g){
            int counter =0;
            for (NPieChartObj obj : fileList) {

                g.setColor(obj.getSegmentColour());
                g.fillRect(225, 40 + (25*counter), 10, 10); // Fills key square

                g.setColor(Color.black); // Set black for border
                g.drawRect(225, 40 + (25*counter), 10, 10); // Draw key border
                g.drawString(fileList.get(counter).getExtention() + "   space:  " + obj.getExtentionTotalLength()/1024 + "/kb", 250, (50 + (25*counter))); // Draw key information

                counter++;
            }

            change = false;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            if (change)
                keyDraw(g);
        }
    }

    public JComponent getPieChartComponent(){
        return chart;
    }
    public JComponent getKeyComponent(){
        return key;
    }

}
