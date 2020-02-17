/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesktopPack;


import innovativedesign.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.*;
import sortinggg.NPieChart;
import sortinggg.NPieChartC;

/**
 *
 * @author 18074751
 */
public class TestMain {
    public static void main(String[] args) {
        NLayeredFrame();
    }

    static void NLayeredFrame(){
        JFrame frame = new JFrame("Nebula GUI Test");
        NLayered layer = new NLayered();
        NMenuBar menuBar = new NMenuBar();

        frame.add(layer, BorderLayout.CENTER);
        layer.LookAndFeel("Windows");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        ImageIcon img = new ImageIcon("H:\\NetBeansProjects\\InnovativeDesign\\src\\DesktopPack\\NebulaLogo.ico");
        frame.setIconImage(img.getImage());

        menuBar.addMenu("File");  // 0
        menuBar.addMenu("Tools"); // 1
        menuBar.add(0, "Exit", (ActionEvent e) -> {frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));});

        JInternalFrame searchIFrame = new SearchIFrame();{
        menuBar.add(1, "Search", searchIFrame);
        layer.add(searchIFrame, JLayeredPane.DEFAULT_LAYER);}

        JInternalFrame iframe = new JInternalFrame("Test");{
        iframe.setVisible(true);
        iframe.setResizable(true);
        iframe.setSize(200, 100);
        iframe.setClosable(true);
        iframe.setMaximizable(true);
        iframe.setIconifiable(true);
        layer.add(iframe, JLayeredPane.DEFAULT_LAYER);}

        JInternalFrame pieChartIFrame = new JInternalFrame("Pie Chart: \"H://\"");
        NPieChartC pi = NPieChart.createPieChartJComponent(new File("/H:/"));
        pieChartIFrame.add(pi, BorderLayout.CENTER);
        pieChartIFrame.pack();
        pieChartIFrame.reshape(pieChartIFrame.getX(), pieChartIFrame.getY(), pieChartIFrame.getWidth() + 75, pieChartIFrame.getHeight()+50);
        pieChartIFrame.setResizable(true);
        pieChartIFrame.setClosable(true);
        menuBar.add(1, "Pie Chart", pieChartIFrame);
        layer.add(pieChartIFrame, JLayeredPane.DEFAULT_LAYER);

        //SearchFrame searchFrame = new SearchFrame();
        //searchFrame.pack();
        //searchFrame.setVisible(true);
        //layer.add(searchFrame, JLayeredPane.DEFAULT_LAYER);

        layer.setVisible(true);
        //layer.setSize(500, 500);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }

    static void DesktopFrame(){
        JFrame frame = new JFrame("Test");
        DesktopPane pane = new DesktopPane();
        frame.add(pane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("H://NetBeansProjects//InnovativeDesign//src//DesktopPack//NebulaLogo.ico");
        frame.setIconImage(img.getImage());
        frame.setSize(700, 1200);
        frame.setVisible(true);
    }

}
