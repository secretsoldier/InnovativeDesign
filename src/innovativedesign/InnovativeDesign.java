/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;

import DesktopPack.NLayered;
import DesktopPack.MenuBar;
import SearchPack.SearchIFrame;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import PieChartPack.PieChart;
import PieChartPack.PieChartC;

/**
 *
 * @author 18074751
 */
public class InnovativeDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Nebula GUI Test");
        NLayered layer = new NLayered();
        MenuBar menuBar = new MenuBar();
        frame.setIconImage(Resources.getIcon("NebulaLogo.png").getImage());

        frame.add(layer, BorderLayout.CENTER);
        layer.LookAndFeel("Windows");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);

        menuBar.addMenu("File");  // 0
        menuBar.addMenu("Tools"); // 1
        menuBar.add(0, "Exit", (ActionEvent e) -> {frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));});

        JInternalFrame searchIFrame = new SearchIFrame();{
        menuBar.add(1, "Search", searchIFrame);
        layer.add(searchIFrame, JLayeredPane.DEFAULT_LAYER);}
        searchIFrame.setFrameIcon(Resources.getIcon("SpaceMonitoring.png"));

        JInternalFrame iframe = new JInternalFrame("Test");{
        iframe.setVisible(true);
        iframe.setResizable(true);
        iframe.setSize(200, 100);
        iframe.setClosable(true);
        iframe.setMaximizable(true);
        iframe.setIconifiable(true);
        layer.add(iframe, JLayeredPane.DEFAULT_LAYER);}

        JInternalFrame pieChartIFrame = new JInternalFrame("Pie Chart: \"H://\"");
        PieChartC pi = PieChart.createPieChartJComponent(new File("/H:/"));
        pieChartIFrame.add(pi, BorderLayout.CENTER);
        pieChartIFrame.setSize(500, 250);
        pieChartIFrame.setResizable(true);
        pieChartIFrame.setClosable(true);
        menuBar.add(1, "Pie Chart", pieChartIFrame);
        layer.add(pieChartIFrame, JLayeredPane.DEFAULT_LAYER);
        pieChartIFrame.setFrameIcon(Resources.getIcon("PieChart.png"));

        layer.setVisible(true);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }


}
