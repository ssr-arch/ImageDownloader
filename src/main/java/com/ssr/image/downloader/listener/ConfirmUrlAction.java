package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.worker.GetImageSourcesWorker;

public class ConfirmUrlAction extends AbstractAction {

    private final DefaultTableModel tableModel;
    private final String url;
    private final JDialog dialog;

    public ConfirmUrlAction(DefaultTableModel tableModel, String url, JDialog dialog) {
        this.tableModel = tableModel;
        this.url = url;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<ImageSource> sources = new ArrayList<>();
        try {
            var getHtmlWorker = new GetImageSourcesWorker(url);
            getHtmlWorker.execute();
            sources = getHtmlWorker.get();
        } catch (MalformedURLException ex) {
            JOptionPane.showMessageDialog(null,
                    "invalid url",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "fatal ",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
        // discard all elements before add row
        tableModel.setRowCount(0);
        sources.stream()
                .map(TableRecord::new)
                .map(TableRecord::getRowData)
                .forEach(r -> tableModel.addRow(r));
        var message = String.format("add %s files", String.valueOf(tableModel.getRowCount()));
        JOptionPane.showMessageDialog(null,
                message,
                "add url",
                JOptionPane.INFORMATION_MESSAGE);
        dialog.dispose();
    }

}
