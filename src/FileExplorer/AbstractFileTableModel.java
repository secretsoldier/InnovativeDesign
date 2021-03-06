package FileExplorer;

import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.util.ArrayList;

public abstract class AbstractFileTableModel extends AbstractTableModel {
    protected ArrayList<File> fileList = new ArrayList<>();

    @Override
    public final int getRowCount() {
        return fileList.size();
    }

    @Override
    public final int getColumnCount() {
        return 3;
    }

    private final String[] columnNames = {"Name", "Last Modified", "Size"};
    @Override
    public final String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public final Object getValueAt(int rowIndex, int columnIndex) {
        return fileList.get(rowIndex);
    }

    public File getFile(int rowIndex){
        return fileList.get(rowIndex);
    }

    public final void size(){
        fileList.size();
    }

    public void fireTableRowsInserted(int row) {
        super.fireTableRowsInserted(row, row);
    }

    public void fireTableRowsDeleted(int row) {
        super.fireTableRowsDeleted(row, row);
    }

    public void addFile(File file){
        fileList.add(file);
        fireTableRowsInserted(fileList.size());
    }

    public void removeFile(int fileIndex){
        fileList.remove(fileIndex);
        fireTableRowsDeleted(fileIndex);
    }

    public final void emptyModel(){
        fireTableRowsDeleted(1, fileList.size());
        fileList.clear();
    }
}
