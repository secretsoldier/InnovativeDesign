/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package innovativedesign;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 *
 * @author 18074751
 */
public class InnovativeDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DesktopWindow desk = new DesktopWindow("Windows");
        int toolsMenu = desk.addMenu("Tools");
        JInternalFrame iframe = new JInternalFrame("Test");;
        JInternalFrame iframe2 = new JInternalFrame("Test2");

        iframe.setVisible(true);
        iframe.setResizable(true);
        iframe.setSize(200, 100);
        iframe.setClosable(true);
        iframe.setMaximizable(true);
        iframe2.setVisible(true);
        iframe2.setResizable(true);
        iframe2.setSize(200, 100);
        iframe2.setClosable(true);
        iframe2.setMaximizable(true);

        desk.addTool(toolsMenu, "Test", iframe);
        desk.addTool(toolsMenu, "Another Test", iframe2);


        desk.addAction(toolsMenu, "Exit", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = desk.getFrame();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        
        //Search findFile = new Search(new File("H:\\"));
        //findFile.addListener(new ISearchListener() {
        //    @Override
        //    public void SearchBegin(Search.SearchType type, String searchString){
        //        System.out.printf("Searching %s %s...\n", searchString, type.toString());
        //    }
        //    int i = 0;
        //    @Override
        //    public boolean SearchUpdate(ArrayList<File> results, boolean active){
        //        System.out.printf("\nSearch Results:\n");
        //        for (File file : results)
        //            System.out.printf("%s\n", file.getPath());
        //        /*if (i == 2){
        //            return false;
        //        }
        //        i++;*/
        //        return active;
        //    }
        //
        //    @Override
        //    public void SearchEnd(ArrayList<File> results){
        //        System.out.printf("\nSearch Complete\n%s files found.\n", results.size());
        //    }
        //});
        //findFile.byFile("main");
        //traverse(0);
    }
}
