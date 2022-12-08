package com.ssr.image.downloader.ui.button;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import com.ssr.image.downloader.delegate.ReadCheckedRecordsDelegate;
import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.ui.dialog.DownLoadDialog;
import com.ssr.image.downloader.worker.DownLoadImageWorker;

public class DownLoadButton {

    private final JButton button;

    public DownLoadButton(ReadCheckedRecordsDelegate readCheckedRowsDelegator) {
        this.button = new JButton("download");
        button.addActionListener(e -> {
            var sources = readCheckedRowsDelegator.invoke()
                    .stream()
                    .map(TableRecord::getSource)
                    .toList();
            if (sources.size() == 0) {
                JOptionPane.showMessageDialog(null,
                        "not selected",
                        "download",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            var dialog = new DownLoadDialog();
            var worker = new DownLoadImageWorker(sources, dialog.getShowDownloadFileNameDelegate());
            worker.addPropertyChangeListener(dialog.getProgressBarCreator());
            worker.addPropertyChangeListener(dialog.getWorkerCompletionWaiter());
            worker.execute();
            dialog.show();
        });
    }

    public JComponent get() {
        return button;
    }
}
