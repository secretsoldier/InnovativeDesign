/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChartPack.PieChart;

import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;

public class MyCanvas extends JComponent {
    Map map;
    public MyCanvas(Map map){
    this.map = map;};

    public void paint(Graphics g) {
        String tab = String.format("%c", '\t');
         System.out.println("Print map 2");
         System.out.println(map);
        int r = 40;
        int v = 360 - r;
        ArrayList<Long> arl = new ArrayList<Long>();
        ArrayList<String> trl = new ArrayList<String>();
        /* g.setColor(Color.RED);
    //g.fillArc(y, x, w, h, inital, fill(R))
    g.fillArc(0, 0, 200, 200,90,r);
    Color CL2 = new Color(18, 10, 143);
     g.setColor(CL2);
    g.fillArc(0, 0, 200, 200,90+r,(p));
         */
        
        Scanner sc = new Scanner(System.in);
        System.out.println("vals = "+map.values());
        System.out.println("keys = "+map.keySet());
        arl.addAll(map.values());
        trl.addAll(map.keySet());
        double sum = 0;
        for (long d : arl) {
            sum += d;
        }
        System.out.println("Total Space: " + sum);
        g.drawString("Total Space:  " + (int)sum/1024 + "/kb", 200, 25);
        int pos = 90;
        int counter =0;
        for (long d : arl) {
            Color CL = new Color((int)(Math.random() * 256), (int)(Math.random() * 256), (int)(Math.random() * 256));
            g.setColor(CL);
            System.out.println(pos + ": pos");
            System.out.println((d/sum)*360);
            if (d == arl.get(arl.size() - 1)){  
                g.fillArc(10, 5, 200, 200, pos, (int)(d/(sum/360)));
            } else {
                g.fillArc(10, 5, 200, 200, pos, (int)(d/(sum/360)));
            }

            g.fillRect(225, 40 + (25*counter), 10, 10);
            g.setColor(Color.black);
            g.drawRect(225, 40 + (25*counter), 10, 10);
            pos+= (d/sum)*360;
            g.drawString(trl.get(counter)+"   space:  " + d/1024 + "/kb", 250, (50 + (25*counter)));
            counter++;
        }
        g.setColor(Color.black);
        for (int i = 0; i < 4; i++) {
            g.drawOval(10 - i, 5 - i, 201 + (i), 201 + (i));
        }
    }
}
