/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExplorer;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author 18074751
 */
public class NFileTable extends JTable {
    public NFileTable(){
        super(new AbstractTableModel(){
            private String[] columns = {};
            
            
            
            @Override
            public int getRowCount() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public int getColumnCount() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Object getValueAt(int arg0, int arg1) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
            
            public String getColumnName(int col){
                return columns[col];
            }
        });
        this.rowSelectionAllowed = true;
        this.setFillsViewportHeight(true);
        
    }
}