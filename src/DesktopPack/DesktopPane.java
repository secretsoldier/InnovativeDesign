/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesktopPack;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author 18074751
 */
public class DesktopPane extends JDesktopPane{
    
    protected JMenuBar MainMenuBar; //This will be the menu bar that user's will access the tools from
    private ArrayList<JMenu> menuList;
    
    public DesktopPane(){
        this.MainMenuBar = new JMenuBar();
        this.add(this.MainMenuBar, BorderLayout.PAGE_START);
    }
    
    public JMenuBar getMenuBar(){
        return this.MainMenuBar;
    }
    public int addMenu(String name){
        JMenu newMenu = new JMenu(name);
        this.menuList.add(newMenu);
        return this.menuList.size() - 1;
    }
    public void addTool(int menu, String name, JInternalFrame iframe){
        JMenuItem frameMenuItem = new JMenuItem(name);
        frameMenuItem.addActionListener((ActionEvent e) -> {iframe.setVisible(true);});
        this.menuList.get(menu).add(frameMenuItem);
        this.add(iframe);
    }
    public void addAction(int menu, String name, ActionListener action){
        JMenuItem frameMenuItem = new JMenuItem(name);
        frameMenuItem.addActionListener(action);
        this.menuList.get(menu).add(frameMenuItem);
    }
    
}
