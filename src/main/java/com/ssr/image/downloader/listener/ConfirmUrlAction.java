package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JTextField;

import com.ssr.image.downloader.delegator.InsertRowsDelegator;
import com.ssr.image.downloader.model.ImageSource;
import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.worker.GetImageSourcesWorker;

public class ConfirmUrlAction extends AbstractAction {

    private final InsertRowsDelegator insertRowsDelegator;
    private final JTextField urlField;

    public ConfirmUrlAction(InsertRowsDelegator insertRowsDelegator, JTextField textField) {
        this.insertRowsDelegator = insertRowsDelegator;
        this.urlField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var getHtmlWorker = new GetImageSourcesWorker(urlField.getText());
        getHtmlWorker.execute();
        List<ImageSource> sources = new ArrayList<>();
        try {
            sources = getHtmlWorker.get();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
        var records = sources.stream()
                .map(TableRecord::new)
                .toArray(TableRecord[]::new);
        insertRowsDelegator.add(records);
    }

}
