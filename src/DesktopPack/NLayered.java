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
    private DesktopManager desktopManager; // TODO Allow frames to Minimise
    public final int DEFAULT_LAYER = JLayeredPane.DEFAULT_LAYER;
    public final int POPUP = JLayeredPane.POPUP_LAYER;
    
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
            java.util.logging.Logger.getLogger(NLayered.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

}
