package FileExplorer;

import java.io.File;

public class DefaultFileTableModel extends AbstractFileTableModel {
    private File root;

    public DefaultFileTableModel(File root){
        setRoot(root);
    }

    public final void setRoot(File root){
        assert root.isDirectory();

        this.root = root;

        new Thread(() -> {
            for (File file : this.root.listFiles()){
                this.addFile(file);
            }
        }).start();
    }

    public final File getRoot(){
        return this.root;
    }

}
