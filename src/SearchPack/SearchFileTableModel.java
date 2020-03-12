package SearchPack;

import FileExplorer.AbstractFileTableModel;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class SearchFileTableModel extends AbstractFileTableModel implements ISearchListener {
    private boolean active = true;
    private JComponent component;

    public SearchFileTableModel(JComponent component){
        this.component = component;
    }

    @Override
    public void SearchBegin(Search.SearchType type, String searchString) {
        System.out.printf("\"%s\" - %s\n", searchString, type.toString());
        component.setEnabled(false);
    }

    @Override
    public boolean SearchUpdate(File result, boolean active) {
        this.addFile(result);
        return this.active;
    }
    public void CancelSearch(){
        active = false;
    }

    @Override
    public void SearchEnd(ArrayList<File> results) {
        component.setEnabled(true);
    }

    public static final String TYPE = "search";
    @Override
    public String getType() {
        return TYPE;
    }
}
