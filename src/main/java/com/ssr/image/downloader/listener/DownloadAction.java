package com.ssr.image.downloader.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.stream.IntStream;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTable;

import com.ssr.image.downloader.model.ImageTableColumnConstants;
import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.worker.DownloadImagesWorker;

public class DownloadAction extends AbstractAction {

    private final JTable table;

    public DownloadAction(JTable table) {
        this.table = table;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (table.getSelectedRowCount() == 0) {
            JOptionPane.showMessageDialog(
                    null,
                    "not selected",
                    "failed to start download",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        var imageSources = IntStream.of(table.getSelectedRows())
                .mapToObj(i -> table.getValueAt(i, ImageTableColumnConstants.FILE_NAME.column()))
                .toArray(ImageSource[]::new);
        var dialog = new JDialog();
        var panel = new JPanel(new BorderLayout());
        var fileNameLabel = new JLabel("pending...");
        var progressBar = new JProgressBar();
        var cancelButton = new JButton("cancel");
        panel.add(fileNameLabel, BorderLayout.NORTH);
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(cancelButton, BorderLayout.SOUTH);
        dialog.setTitle("downloading...");
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setModal(true);
        dialog.setLocationRelativeTo(null);
        var worker = new DownloadImagesWorker(imageSources, fileNameLabel);
        worker.addPropertyChangeListener(new DeterminateProgressBarListener(progressBar));
        worker.addPropertyChangeListener(new WorkerCompletionWaiter(dialog));
        cancelButton.addActionListener(evt -> worker.cancel(true));
        worker.execute();
        // to block on the EDT using modal dialog
        dialog.setVisible(true);
    }

}
