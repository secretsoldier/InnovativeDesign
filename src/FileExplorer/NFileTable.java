/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileExplorer;

import java.awt.Component;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author 18074751
 */
public class NFileTable extends JTable {
    public NFileTable(AbstractFileTableModel fileTableModel){
        super(fileTableModel);
        this.setFillsViewportHeight(true);
        this.setRowSelectionAllowed(true);
        this.createDefaultColumnsFromModel();
    }

    @Override
    public AbstractFileTableModel getModel() {
        return (AbstractFileTableModel)super.getModel();
    }

    /*
    @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON2 && e.getClickCount() == 2) {
                System.out.printf(this.getText() + " double clicked.");
            }
        }
    */

    private static class NameCellRenderer implements TableCellRenderer {
        private FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        private Map<String, Icon> iconMap = new HashMap<>();

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            new Thread(() -> {
                if (!iconMap.containsKey(((File)value).getName())) {
                    iconMap.put(((File) value).getName(), fileSystemView.getSystemIcon(((File) value)));
                    table.repaint();
                }
            }).start();
            return new JLabel() {
                @Override
                public String getText() {
                    return ((File)value).getName();
                }

                @Override
                public Icon getIcon() {
                    return iconMap.get(((File)value).getName());
                }
            };
        }

        public final void clearIconMap(){
            iconMap.clear();
        }
    }

    private static class DateModifiedCellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JLabel() {
                @Override
                public String getText() {
                    Date date = new Date(Long.parseLong(String.valueOf(((File)value).lastModified())));
                    return date.toGMTString();
                }
            };
        }
    }

    public static String humanReadableByteCountBin(long bytes) {
        long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        return b < 1024L ? bytes + " B"
                : b <= 0xfffccccccccccccL >> 40 ? String.format("%.1f KB", bytes / 0x1p10)
                : b <= 0xfffccccccccccccL >> 30 ? String.format("%.1f MB", bytes / 0x1p20)
                : b <= 0xfffccccccccccccL >> 20 ? String.format("%.1f GB", bytes / 0x1p30)
                : b <= 0xfffccccccccccccL >> 10 ? String.format("%.1f TB", bytes / 0x1p40)
                : b <= 0xfffccccccccccccL ? String.format("%.1f PB", (bytes >> 10) / 0x1p40)
                : String.format("%.1f EB", (bytes >> 20) / 0x1p40);
    } // Taken from StackOverflow

    private static class SizeCellRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return new JLabel(){
                @Override
                public String getText() {
                    return ((File)value).isFile() ? humanReadableByteCountBin(((File)value).length()) : "";
                }
            };
        }
    }

    private final TableCellRenderer[] renderers = {new NameCellRenderer(), new DateModifiedCellRenderer(), new SizeCellRenderer()};
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        return renderers[column];
    }
}