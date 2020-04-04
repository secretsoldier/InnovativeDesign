package FileExplorer;

import java.io.File;

public class DriveFileTableModel extends AbstractFileTableModel {
    public DriveFileTableModel(){
        for (File drive : File.listRoots()){
            addFile(drive);
        }
    }

    public static final String TYPE = "drive";
    @Override
    public String getType() {
        return TYPE;
    }
}
