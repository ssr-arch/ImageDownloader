package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import com.ssr.image.downloader.model.ImageSource;
import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.worker.GetImageSourcesWorker;

public class ConfirmUrlAction extends AbstractAction {

    private final Consumer<TableRecord[]> insertRowsDelegator;
    private final Supplier<String> urlGetter;

    public ConfirmUrlAction(Consumer<TableRecord[]> insertRecordsAction, Supplier<String> urlGetter) {
        this.insertRowsDelegator = insertRecordsAction;
        this.urlGetter = urlGetter;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        List<ImageSource> sources = new ArrayList<>();
        try {
            var getHtmlWorker = new GetImageSourcesWorker(urlGetter.get());
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
        insertRowsDelegator.accept(records);
        var message = String.format("add %s files", String.valueOf(records.length));
        JOptionPane.showMessageDialog(null,
                message,
                "add url",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
