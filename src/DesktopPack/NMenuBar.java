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
public class NMenuBar extends JMenuBar {
    
    public NMenuBar(){
        super();
    } // Constructor
    
    public int addMenu(String name){ // This adds a menu to the Menu Bar (Headers). Look at usage in InnovativeDesign.java
        JMenu newMenu = new JMenu(name);
        newMenu.setVisible(true);
        this.add(newMenu);
        return this.getComponentCount() - 1; // Returns the Menu's ID that the developer can add with the add Method
    }
    public void add(int menu, String name, JInternalFrame iframe){ // Adds Items to declared Menu
        JMenuItem frameMenuItem = new JMenuItem(name); // Creates Menu Item with the parameter "name"
        frameMenuItem.setVisible(true); // Menu Item is made Visible
        frameMenuItem.addActionListener((ActionEvent e) -> {iframe.setVisible(true);}); // Menu Item gets given the Action to make the parameter "iframe" visible on click

        ((JMenu)this.getComponent(menu)).add(frameMenuItem); // This adds the Menu Item to the Menu Bar
        iframe.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE); // Makes it not visible on close
        iframe.setVisible(false); // Sets the parameter "iframe" not visible
    }
    public void add(int menu, String name, ActionListener action){ // Adds Items to declared Menus, look to first "add" for comments
        JMenuItem frameMenuItem = new JMenuItem(name);
        frameMenuItem.setVisible(true);
        frameMenuItem.addActionListener(action);
        ((JMenu)this.getComponent(menu)).add(frameMenuItem);
    }
    
}
