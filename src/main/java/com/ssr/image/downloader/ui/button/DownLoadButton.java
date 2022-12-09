package com.ssr.image.downloader.ui.button;

import java.util.List;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.ssr.image.downloader.model.ImageSource;
import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.ui.dialog.DownLoadDialog;
import com.ssr.image.downloader.worker.DownloadImagesWorker;

public class DownLoadButton {

    private final JButton button;

    public DownLoadButton(Supplier<List<TableRecord>> checkedRecordsGetter) {
        this.button = new JButton("download");
        button.addActionListener(e -> {
            var sources = checkedRecordsGetter.get()
                    .stream()
                    .map(TableRecord::getSource)
                    .toArray(ImageSource[]::new);
            if (sources.length == 0) {
                JOptionPane.showMessageDialog(null,
                        "not selected",
                        "download",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            var dialog = new DownLoadDialog();
            var worker = new DownloadImagesWorker(sources, dialog.createSetFileNameAction());
            worker.addPropertyChangeListener(evt -> dialog.createDeterminateProgressBarAction().accept(evt));
            worker.addPropertyChangeListener(evt -> dialog.createWorkerCompletionWaiterAction().accept(evt));
            dialog.createCancelAction().accept(worker);
            worker.execute();
            dialog.show();
        });
    }

    public JComponent get() {
        return button;
    }
}
