package SearchPack;

import FileExplorer.AbstractFileTableModel;

import java.io.File;

public class SearchFileTableModel extends AbstractFileTableModel implements ISearchListener {
    private boolean active = true;
    @Override
    public boolean SearchUpdate(File result, boolean active) {
        this.addFile(result);
        return this.active;
    }
    public void CancelSearch(){
        active = false;
    }

    public static final String TYPE = "search";
    @Override
    public String getType() {
        return TYPE;
    }
}
