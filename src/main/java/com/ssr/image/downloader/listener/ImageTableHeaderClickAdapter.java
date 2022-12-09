package com.ssr.image.downloader.listener;

import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

import com.ssr.image.downloader.model.ImageTableColumnConstants;

public class ImageTableHeaderClickAdapter extends MouseInputAdapter {

    private final JTable table;

    public ImageTableHeaderClickAdapter(JTable table) {
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var column = table.columnAtPoint(e.getPoint());
        if (column != ImageTableColumnConstants.ALL.column()) {
            return;
        }
        var rowCount = table.getRowCount();
        var selectedRowCount = table.getSelectedRowCount();
        if (rowCount == selectedRowCount) {
            table.clearSelection();
            return;
        }
        table.selectAll();
    }

}
