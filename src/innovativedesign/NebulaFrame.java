package innovativedesign;

import CreateFolder.CreateFolder;
import DesktopPack.MenuBar;
import DesktopPack.NLayered;
import FileExplorer.DefaultFileTableModel;
import FileExplorer.FileExplorerFrame;
import FileExplorer.FileTablePanel;
import PieChartPack.PieChart;
import PieChartPack.PieChartC;
//import SearchPack.SearchInternalFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;

public class NebulaFrame {
    private final JFrame frame = new JFrame("Nebula GUI Test");{
        frame.setIconImage(Resources.getIcon("NebulaLogo.png").getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    } // Main Frame Properties
    private final NLayered layer = new NLayered();{
        frame.add(layer, BorderLayout.CENTER);
        layer.LookAndFeel("Windows");
        layer.setVisible(true);
    } // Layer Properties
    private final MenuBar menuBar = new MenuBar();{
        frame.setJMenuBar(menuBar);
    } // Menu Bar Properties

    // Menu Bar Header Constants //
    public final int FILE = menuBar.addMenu("File");  // 0
    public final int TOOLS = menuBar.addMenu("Tools"); // 1
    { // Default Menu Bar Items //
        menuBar.add(FILE, "Exit", (ActionEvent e) -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
        menuBar.add(1, "Create Folder", (ActionEvent e) -> CreateFolder.createFolder(frame));
    } // Default Menu Bar Items

    // Main Frame Methods //
    public void addInternalFrame(String name, int menuHeader, JInternalFrame iFrame){
        layer.add(iFrame, layer.DEFAULT_LAYER);
        menuBar.add(menuHeader, name, iFrame);
    }
    public void addTempInternalFrame(JInternalFrame iFrame){
        layer.add(iFrame, layer.DEFAULT_LAYER);
        iFrame.setVisible(true);
    }

    // Default Internal Frames Declaration //
    private final JInternalFrame[] defaultInternalFrames = {
            new JInternalFrame("Test"), // Test Frame: 0
            new JInternalFrame("Pie Chart: \"C://\""), // Pie Chart Frame: 1
            //new SearchInternalFrame() // Search Frame: 2
    };
    { // Internal Frames Properties //
        JInternalFrame  test     = defaultInternalFrames[0],
                        pieChart = defaultInternalFrames[1];
                        //search   = defaultInternalFrames[2];

        // Test Frame Properties //
        test.setVisible(true);
        test.setResizable(true);
        test.setSize(200, 100);
        test.setClosable(true);
        test.setMaximizable(true);
        test.setIconifiable(true);
        layer.add(test, layer.DEFAULT_LAYER);

        // Pie Chart Properties //
        PieChartC pi = PieChart.createPieChartJComponent(new File("/C:/"));
        pieChart.add(pi, BorderLayout.CENTER);
        pieChart.setSize(500, 250);
        pieChart.setResizable(true);
        pieChart.setClosable(true);
        pieChart.setFrameIcon(Resources.getIcon("PieChart.png"));
        addInternalFrame("Pie Chart - \"C:\\\"", TOOLS, pieChart);

        // Search Properties //
        //search.setClosable(true);
        //search.setFrameIcon(Resources.getIcon("SpaceMonitoring.png"));
        //addInternalFrame("Search", TOOLS, search);
    } // Default Internal Frame's Properties
    private final FileExplorerFrame fileExplorer = new FileExplorerFrame("File Explorer", new DefaultFileTableModel(new File("C:\\")));{
        fileExplorer.setClosable(true);
        fileExplorer.setResizable(true);
        fileExplorer.setIconifiable(true);
        fileExplorer.setMaximizable(true);
        fileExplorer.setSize(500, 500);
        addInternalFrame("File Explorer", TOOLS, fileExplorer);
    } // File Explorer Properties //
    
    public DefaultFileTableModel getExplorerModel(){
        return (DefaultFileTableModel) fileExplorer.getModel();
    }
    
    public FileTablePanel getExplorerPanel() {
        return fileExplorer.getTablePanel();
    }
}