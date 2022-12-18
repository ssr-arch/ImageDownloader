package com.ssr.image.downloader.ui;

import javax.swing.JTable;

import com.ssr.image.downloader.listener.ImageTableHeaderClickAdapter;
import com.ssr.image.downloader.listener.ImageTablePreviewClickAdapter;

public class ImageTable extends JTable {

    private final ImageTableModel tableModel;

    public ImageTable() {
        this.tableModel = new ImageTableModel();
        setModel(tableModel);
        getTableHeader().addMouseListener(new ImageTableHeaderClickAdapter(this));
        addMouseListener(new ImageTablePreviewClickAdapter(this));
    }

    @Override
    public void selectAll() {
        if (tableModel != null) {
            var rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                setValueAt(Boolean.TRUE, i, 0);
            }
        }
    }

    @Override
    public void clearSelection() {
        if (tableModel != null) {
            var rowCount = tableModel.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                setValueAt(Boolean.FALSE, i, 0);
            }
        }
    }

    @Override
    public int getSelectedRowCount() {
        return tableModel.getDataVector()
                .stream()
                .map(v -> (Boolean) v.elementAt(0))
                .filter(b -> b)
                .toList()
                .size();
    }

    @Override
    public int[] getSelectedRows() {
        var vector = tableModel.getDataVector();
        return vector
                .stream()
                .filter(v -> (Boolean) v.elementAt(0))
                .mapToInt(v -> vector.indexOf(v))
                .toArray();
    }

}
