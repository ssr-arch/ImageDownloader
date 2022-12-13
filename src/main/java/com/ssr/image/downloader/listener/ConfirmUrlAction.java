package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.ui.dialog.AddUrlDialog;
import com.ssr.image.downloader.worker.GetImageSourcesWorker;

public class ConfirmUrlAction extends AbstractAction {

    private final DefaultTableModel tableModel;
    private final JTextField urlField;
    private final JDialog dialog;

    public ConfirmUrlAction(DefaultTableModel tableModel, JTextField urlField, AddUrlDialog dialog) {
        this.tableModel = tableModel;
        this.urlField = urlField;
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<ImageSource> sources = new ArrayList<>();
        try {
            var getHtmlWorker = new GetImageSourcesWorker(urlField.getText());
            getHtmlWorker.execute();
            sources = getHtmlWorker.get();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
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
