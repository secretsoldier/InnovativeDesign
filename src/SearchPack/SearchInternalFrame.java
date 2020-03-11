package SearchPack;

import FileExplorer.FileExplorerFrame;
import innovativedesign.InnovativeDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class SearchInternalFrame extends JInternalFrame { // TODO fix weird bug that stops the frame from showing
    private final JTextField searchBox = new JTextField();
    private final JButton searchButton = new JButton();{ // TODO Have the cancel button replace the search button when searching
        searchButton.setText("Search");
    }
    private final JCheckBox[] searchProperties = {
            new JCheckBox(), // Search in directories property: 0
            new JCheckBox(), // Search for only files: 1
            new JCheckBox()  // Search for only directories: 2
    };{
        JCheckBox   inDirectories = searchProperties[0],
                    fileSearch = searchProperties[1],
                    directorySearch = searchProperties[2];

        inDirectories.setText("All Subfolders");
        inDirectories.setSelected(true);

        fileSearch.setText("Files");
        fileSearch.setSelected(true);

        directorySearch.setText("Directories");
        directorySearch.setSelected(true);
    }
    private final ButtonGroup typeProperties = new ButtonGroup(){
        private final ArrayList<ButtonModel> selectedLst = new ArrayList<>();
        private int selected = 0;

        @Override
        public void add(AbstractButton b) {
            boolean active = false;
            if (b.isSelected()){
                selected++;
                selectedLst.add(b.getModel());
                active = true;
            }
            super.add(b);
            b.setSelected(active);
        }

        @Override
        public void remove(AbstractButton b) {
            if (b.isSelected()){
                selected--;
                selectedLst.remove(b.getModel());
            }
            super.remove(b);
        }

        @Override
        public void setSelected(ButtonModel m, boolean b) {
            if (selected > 1 || b){
                if (b){
                    selectedLst.add(m);
                    selected++;
                } else {
                    selectedLst.remove(m);
                    selected--;
                }
            }
        }

        @Override
        public boolean isSelected(ButtonModel m) {
            return selectedLst.contains(m);
        }
    };{
        typeProperties.add(searchProperties[1]);
        typeProperties.add(searchProperties[2]);
    }
    private final JSeparator separator = new JSeparator();

    {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(searchBox)
                                                .addComponent(searchButton))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(searchProperties[0])
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(searchProperties[1])
                                                                .addComponent(searchProperties[2])))
                                                .addGap(0, 180, Short.MAX_VALUE))
        )));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED, 5, 5)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchProperties[0])
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchProperties[1])
                                        .addComponent(searchProperties[2]))
        ));
        pack();

        searchButton.addActionListener((ActionEvent e) ->{
            SearchFileTableModel searchResultModel = new SearchFileTableModel();
            InnovativeDesign.Main.addTempInternalFrame(new FileExplorerFrame("Search Results", searchResultModel));
            Search search = new Search(InnovativeDesign.Main.getExplorerModel().getCurrentRoot());
            search.addListener(searchResultModel);
            if (searchProperties[1].isSelected() && searchProperties[2].isSelected()){ // Search for both files and directories
                search.byBoth(searchBox.getText().trim(), searchProperties[0].isSelected());
            } else if (searchProperties[1].isSelected()){ // Search for just files
                search.byFile(searchBox.getText().trim(), searchProperties[0].isSelected());
            } else { // Search for just directories
                search.byDirectory(searchBox.getText().trim(), searchProperties[0].isSelected());
            }
        });
    }

    @Override
    public void setSize(Dimension d) {

    }

    @Override
    public void setSize(int width, int height) {

    }
}
