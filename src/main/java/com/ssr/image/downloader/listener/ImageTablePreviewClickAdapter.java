package com.ssr.image.downloader.listener;

import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.event.MouseInputAdapter;

import com.ssr.image.downloader.model.ImageTableColumnConstants;
import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.ui.dialog.PreviewDialog;
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
        var imageSource = (ImageSource) table.getValueAt(
                table.rowAtPoint(point),
                ImageTableColumnConstants.FILE_NAME.column());
        Point tablePositionOnScreen = table.getLocationOnScreen();
        var clickedPosition = new Point(
                (int) (tablePositionOnScreen.getX() + point.getX() + 10),
                (int) (tablePositionOnScreen.getY() + point.getY()));
        var dialog = new PreviewDialog(clickedPosition);
        var worker = new ShowPreviewWorker(imageSource, dialog);
        worker.execute();
    }

}
