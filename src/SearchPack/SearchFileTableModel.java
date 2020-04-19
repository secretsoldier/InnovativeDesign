package SearchPack;

import FileExplorer.AbstractFileTableModel;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

public class SearchFileTableModel extends AbstractFileTableModel implements ISearchListener {
    private boolean active = true;
    private Runnable[] events;
    
    public SearchFileTableModel(Runnable pre, Runnable post){
        events = new Runnable[]{
                pre,
                post
        };
    }
    public SearchFileTableModel(Runnable pre) {
        this(pre, null);
    }
    public SearchFileTableModel() {
        this(null, null);
    }

    @Override
    public void SearchBegin(Search.SearchType type, String searchString, File directory, boolean subfolders) {
        System.out.printf("\"%s\" in \"%s\", %s (Subfolders - %s)\n", searchString, directory.getPath(), type, subfolders);
        if (events[0] != null)
            events[0].run();
    }

    @Override
    public boolean SearchUpdate(File result, boolean active) {
        //System.out.printf("\t%s\n", result.getName());
        this.addFile(result);
        return this.active;
    }
    public void CancelSearch(){
        active = false;
    }

    @Override
    public void SearchEnd(ArrayList<File> results) {
        System.out.println("Search finished");
        if (events[1] != null)
            events[1].run();
    }
}
