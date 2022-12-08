package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ssr.image.downloader.delegate.InsertRowsDelegate;
import com.ssr.image.downloader.model.ImageSource;
import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.worker.GetImageSourcesWorker;

public class ConfirmUrlAction extends AbstractAction {

    private final InsertRowsDelegate insertRowsDelegator;
    private final JTextField urlField;

    public ConfirmUrlAction(InsertRowsDelegate insertRowsDelegator, JTextField textField) {
        this.insertRowsDelegator = insertRowsDelegator;
        this.urlField = textField;
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
        var records = sources.stream()
                .map(TableRecord::new)
                .toArray(TableRecord[]::new);
        insertRowsDelegator.invoke(records);
        var message = String.format("add %s files", String.valueOf(records.length));
        JOptionPane.showMessageDialog(null,
                message,
                "add url",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
