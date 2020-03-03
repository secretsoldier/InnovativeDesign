/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesktopPack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author 18074751
 */
public class MenuBar extends JMenuBar {
    
    public MenuBar(){
        super();
    }
    
    public int addMenu(String name){
        JMenu newMenu = new JMenu(name);
        newMenu.setVisible(true);
        this.add(newMenu);
        return this.getComponentCount() - 1;
    }
    public void add(int menu, String name, JInternalFrame iframe){
        JMenuItem frameMenuItem = new JMenuItem(name);
        frameMenuItem.setVisible(true);
        frameMenuItem.addActionListener((ActionEvent e) -> {iframe.setVisible(true);});
        ((JMenu)this.getComponent(menu)).add(frameMenuItem);
        iframe.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
        iframe.setVisible(false);
    }
    public void add(int menu, String name, ActionListener action){
        JMenuItem frameMenuItem = new JMenuItem(name);
        frameMenuItem.setVisible(true);
        frameMenuItem.addActionListener(action);
        ((JMenu)this.getComponent(menu)).add(frameMenuItem);
    }
    
}
