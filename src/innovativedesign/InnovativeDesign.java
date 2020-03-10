/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;

import DesktopPack.NLayered;
import DesktopPack.NMenuBar;
import SearchPack.SearchIFrame;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import PieChartPack.NPieChart;
import PieChartPack.NPieChartC;
import createfolder.CreateFolder;

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
        NMenuBar menuBar = new NMenuBar();
        frame.setIconImage(Resources.getIcon("NebulaLogo.png").getImage());

        frame.add(layer, BorderLayout.CENTER);
        layer.LookAndFeel("Windows"); // This controls how the GUI looks
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets how the window will close
        frame.setJMenuBar(menuBar);

        menuBar.addMenu("File");  // 0
        menuBar.addMenu("Tools"); // 1
        menuBar.add(0, "Exit", (ActionEvent e) -> {frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));});
        
        menuBar.add(1, "Create Folder", (ActionEvent e) -> {CreateFolder.createFolder(frame);});
        
        JInternalFrame searchIFrame = new SearchIFrame();{ // This initlises Search Frame
        menuBar.add(1, "Search", searchIFrame); // This adds it to the Menu Bar
        layer.add(searchIFrame, JLayeredPane.DEFAULT_LAYER);} // This adds it to the Virtual Desktop
        searchIFrame.setFrameIcon(Resources.getIcon("SpaceMonitoring.png")); // This sets the Frame's Icon

        JInternalFrame iframe = new JInternalFrame("Test");{
            iframe.setVisible(true);
            iframe.setResizable(true);
            iframe.setSize(200, 100);
            iframe.setClosable(true);
            iframe.setMaximizable(true);
            iframe.setIconifiable(true);
            layer.add(iframe, JLayeredPane.DEFAULT_LAYER);
        }

        JInternalFrame pieChartIFrame = new JInternalFrame("Pie Chart: \"H://\"");
        NPieChartC pi = NPieChart.createPieChartJComponent(new File("/H:/"));
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
