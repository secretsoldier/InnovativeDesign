/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesktopPack;

import javax.swing.*;

/**
 *
 * @author 18074751
 */
public class NLayered extends JLayeredPane {
    private DesktopManager desktopManager;
    
    public NLayered(){
        super();
    }

    public void LookAndFeel(String lookAndFeel){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (lookAndFeel.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JLayeredPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
