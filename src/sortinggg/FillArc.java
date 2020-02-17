/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sortinggg;

import java.util.Map;
import javax.swing.*;

/**
 *
 * @author 17007976
 */
public class FillArc {
  public static void main(Map test) {
    JFrame window = new JFrame();
    JComponent comp = new MyCanvas(test);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setBounds(30, 30, 400, 300);
    window.getContentPane().add(comp);
    window.setVisible(true);
    System.out.println("test : " + test);
    
  }
}