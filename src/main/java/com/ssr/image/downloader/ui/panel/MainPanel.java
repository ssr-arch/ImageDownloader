package com.ssr.image.downloader.ui.panel;

import java.awt.BorderLayout;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.listener.DeterminateProgressBarListener;
import com.ssr.image.downloader.listener.WorkerCompletionWaiter;
import com.ssr.image.downloader.model.ImageTableColumnConstants;
import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.ui.dialog.AddUrlDialog;
import com.ssr.image.downloader.ui.table.ImageTable;
import com.ssr.image.downloader.worker.DownloadImagesWorker;

public class MainPanel extends JPanel {

    private final JTable imageTable;
    private final JButton addUrlButton;
    private final JButton downloadButton;

    public MainPanel() {
        this.imageTable = new ImageTable();
        this.addUrlButton = new JButton("add url");
        this.downloadButton = new JButton("download");
        addUrlButton.addActionListener(e -> {
            new AddUrlDialog((DefaultTableModel) imageTable.getModel())
                    .setVisible(true);
        });
        downloadButton.addActionListener(e -> {
            if (imageTable.getSelectedRowCount() == 0) {
                JOptionPane.showMessageDialog(this,
                        "not selected",
                        "failed to start download",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            var imageSources = IntStream.of(imageTable.getSelectedRows())
                    .mapToObj(i -> imageTable.getValueAt(i, ImageTableColumnConstants.FILE_NAME.column()))
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
        });
        var footerPanel = new JPanel();
        footerPanel.add(addUrlButton);
        footerPanel.add(downloadButton);
        setLayout(new BorderLayout());
        add(new JScrollPane(imageTable), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

}
