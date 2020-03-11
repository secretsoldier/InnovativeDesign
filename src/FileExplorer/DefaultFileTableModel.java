package FileExplorer;

import java.io.File;
import java.util.Objects;

public class DefaultFileTableModel extends AbstractFileTableModel {
    public static final String TYPE = "default";
    @Override
    public String getType() {
        return TYPE;
    }

    private class RootHistory {
        public File data;
        public RootHistory  last = null,
                            next = null;
        public RootHistory(File data){
            this.data = data;
        }
        public RootHistory(File data, RootHistory last){
            this.data = data;
            this.last = last;
            last.next = this;
        }
    }
    private RootHistory root;

    public DefaultFileTableModel(File root){
        addRoot(root);
    }

    private void loadRoot(){
        emptyModel();
        new Thread(() -> {
            for (File file : Objects.requireNonNull(this.root.data.listFiles())){
                this.addFile(file);
            }
        }).start();
    }

    public void addRoot(File root){
        assert root.isDirectory();

        this.root = this.root == null ? new RootHistory(root) : new RootHistory(root, this.root);

        loadRoot();
    }

    public File getCurrentRoot(){
        return this.root.data;
    }
    public void setLast(){
        if (root.last != null){
            root = root.last;
            loadRoot();
        }
    }
    public void setNext(){
        if (root.next != null){
            root = root.next;
            loadRoot();
        }
    }
    public void refreshRoot(){
        loadRoot();
    }
}
