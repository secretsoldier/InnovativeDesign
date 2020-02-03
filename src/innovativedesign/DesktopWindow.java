/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author 18074751
 */
class NDesktopPane extends JDesktopPane {
    public NDesktopPane() {
        this.setVisible(true);
    }
}
class NMenuBar extends JMenuBar {
    private NFrame frame;
    public NMenuBar(NFrame frame){
        this.setVisible(true);
        this.frame = frame;
    }
    public NFrame getFrame(){
        return this.frame;
    }
}
class NFrame extends JFrame {
    private NDesktopPane pane;
    private NMenuBar bar;
    public NFrame(String name, int w, int h){
        super(name);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(w, h);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void setDesktopPane(NDesktopPane pane){
        this.add(pane, BorderLayout.CENTER);
        this.pane = pane;
    }
    public NDesktopPane getNDesktopPane(){
        return this.pane;
    }
    public void setMenuBar(NMenuBar bar){
        this.add(bar, BorderLayout.PAGE_START);
        this.bar = bar;
    }
    public NMenuBar getNMenuBar(){
        return this.bar;
    }
}
class NInternalFrame extends JInternalFrame {
    public NInternalFrame(String name, int w, int h){
        super(name);
        this.setVisible(true);
        this.setResizable(true);
        this.setSize(w, h);
        this.setClosable(true);
        this.setMaximizable(true);
    }
}
class NMenuFile extends JMenu implements ActionListener {
    private NFrame frame = null;
    public NMenuFile(NMenuBar bar){
        super("File");
        this.frame = bar.getFrame();
        JMenuItem newWindow = new JMenuItem("New Window");
        newWindow.addActionListener(this);
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        this.add(newWindow);
        this.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JMenuItem item = (JMenuItem)arg0.getSource();
        if (item.getText().equals("New Window")){
            frame.getNDesktopPane().add(new NInternalFrame("Frame", 200, 100));
        } else if (item.getText().equals("Exit")){
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
public class DesktopWindow {
    private NFrame window;
    private NDesktopPane pane;
    private NMenuBar bar;

    public JFrame getFrame(){
        return this.window;
    }
    public JDesktopPane getDesktopPane(){
        return this.pane;
    }
    public JMenuBar getMenuBar(){
        return this.bar;
    }

    private ArrayList<JMenu> menuBar = new ArrayList<>();
    public DesktopWindow(String lookAndFeel){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesktopWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        this.window = new NFrame("Nebula", 600, 500);
        this.pane = new NDesktopPane();
        this.window.setDesktopPane(pane);
        this.bar = new NMenuBar(window);
        this.window.add(this.bar, BorderLayout.PAGE_START);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.pack();
    }

    public void init(String windowName){
        this.window = new NFrame("Nebula", 600, 500);
        this.pane = new NDesktopPane();
        this.bar = new NMenuBar(window);
        
        /*this.bar.add(new NMenuFile(this.bar));
        this.pane.add(new NInternalFrame("Frame 1", 200, 100));
        this.window.setDesktopPane(pane);
        this.window.add(this.bar, BorderLayout.PAGE_START);*/
    }
    public int addMenu(String name){
        JMenu menu = new JMenu(name);
        this.bar.add(menu);
        menuBar.add(menu);
        return menuBar.size() - 1;
    }
    public boolean addTool(int menu, String toolName, JInternalFrame iframe){
        JMenuItem tool = new JMenuItem(toolName);
        tool.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pane.add(iframe);
            }
        });
        menuBar.get(menu).add(tool);
        return true;
    }
    public boolean addAction(int menu, String actionName, ActionListener e){
        JMenuItem action = new JMenuItem(actionName);
        action.addActionListener(e);
        menuBar.get(menu).add(action);
        return true;
    }
}
