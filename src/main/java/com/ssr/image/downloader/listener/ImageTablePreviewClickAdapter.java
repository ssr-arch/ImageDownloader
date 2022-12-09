package com.ssr.image.downloader.listener;

import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

import com.ssr.image.downloader.model.ImageTableColumnConstants;
import com.ssr.image.downloader.worker.ShowPreviewWorker;

public class ImageTablePreviewClickAdapter extends MouseInputAdapter {

    private final JTable table;

    public ImageTablePreviewClickAdapter(JTable table) {
        this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        var point = e.getPoint();
        if (table.columnAtPoint(point) != ImageTableColumnConstants.PREVIEW.column()) {
            return;
        }
        var row = table.rowAtPoint(point);
        var column = table.columnAtPoint(point);
        var previewIcon = (ImageIcon) table.getValueAt(row, column);
        var icon = new JLabel();
        var worker = new ShowPreviewWorker(icon, previewIcon.getDescription(), new JDialog());
        worker.execute();
    }

}
