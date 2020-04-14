/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExplorer;

import java.awt.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 18074751
 */
public class FileTable extends JTable { // TODO Add mouse hover effects and improve appearance
    public FileTable(AbstractFileTableModel fileTableModel, Component parent){
        super(fileTableModel);
        this.setRowSelectionAllowed(true);
        this.setColumnSelectionAllowed(false);
        this.createDefaultColumnsFromModel();
        this.setFocusable(false);
        this.setRowHeight(20);
        this.setRowMargin(3);
        this.setShowVerticalLines(false);
        this.setShowHorizontalLines(false);
        this.setGridColor(Color.lightGray);
        this.tableHeader.setReorderingAllowed(false);
        this.tableHeader.setOpaque(false);
        this.tableHeader.setBackground(Color.WHITE);
        this.tableHeader.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.GRAY);
                g.drawLine(x, y + height - 3, x + width, y + height - 3);
            }
    
            @Override
            public Insets getBorderInsets(Component c, Insets insets) {
                insets.set(0,0,1,0);
                return super.getBorderInsets(c, insets);
            }
        });
        this.addMouseListener(new FileTableMouseActions(parent));
    }

    @Override
    public AbstractFileTableModel getModel() {
        return (AbstractFileTableModel)super.getModel();
    }
    
    private static class DefaultCellRenderer extends JLabel implements TableCellRenderer {
    
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected)
                this.setBackground(Color.blue);
            else
                this.setBackground(Color.WHITE);
            return this;
        }
    }

    private static class NameCellRenderer extends DefaultCellRenderer {
        private int hash = -1;
        private Map<String, Icon> iconMap = new Hashtable<>();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (hash != (hash = table.getModel().hashCode()))
                iconMap.clear();
            
            if (!iconMap.containsKey(((File)value).getName())) {
                FileSystemView fileSystemView = FileSystemView.getFileSystemView();
                new Thread(() -> {
                    iconMap.put(((File)value).getName(), fileSystemView.getSystemIcon(((File)value)));
                    table.repaint();
                }).start();
            }
            
            JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (table.getModel() instanceof DriveFileTableModel)
                c.setText(((File)value).getPath());
            else
                c.setText(((File)value).getName());
            c.setIcon(iconMap.get(((File)value).getName()));
            return c;
        }
    }

    private static class DateModifiedCellRenderer extends DefaultCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setText(new Date(Long.parseLong(String.valueOf(((File)value).lastModified()))).toLocaleString());
            return c;
        }
    }

    private static String humanReadableByteCountBin(long bytes) {
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1024L ? bytes + " B"
                : b <= 0xfffccccccccccccL >> 40 ? String.format("%.1f KB", bytes / 0x1p10)
                : b <= 0xfffccccccccccccL >> 30 ? String.format("%.1f MB", bytes / 0x1p20)
                : b <= 0xfffccccccccccccL >> 20 ? String.format("%.1f GB", bytes / 0x1p30)
                : b <= 0xfffccccccccccccL >> 10 ? String.format("%.1f TB", bytes / 0x1p40)
                : b <= 0xfffccccccccccccL ? String.format("%.1f PB", (bytes >> 10) / 0x1p40)
                : String.format("%.1f EB", (bytes >> 20) / 0x1p40);
    } // Taken from StackOverflow

    private static class SizeCellRenderer extends DefaultCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel c = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            c.setText(((File)value).isFile() ? humanReadableByteCountBin(((File)value).length()) : "");
            return c;
        }
    }

    private final TableCellRenderer[] renderers = {new NameCellRenderer(), new DateModifiedCellRenderer(), new SizeCellRenderer()};
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return renderers[column];
    }

    @Override
    public File getValueAt(int row, int column) {
        return (File)super.getValueAt(row, column);
    }

    public File getValueAt(int row) {
        return (File)super.getValueAt(row, 0);
    }
}