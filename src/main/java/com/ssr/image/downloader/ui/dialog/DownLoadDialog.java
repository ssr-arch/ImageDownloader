package com.ssr.image.downloader.ui.dialog;

import java.awt.BorderLayout;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.ssr.image.downloader.delegate.ShowDownloadFileNameDelegate;
import com.ssr.image.downloader.listener.DeterminateProgressBarCreator;
import com.ssr.image.downloader.listener.WorkerCompletionWaiter;

public class DownLoadDialog {

    private final JDialog dialog;
    private final JProgressBar progressBar;
    private final JLabel fileName;
    private final JButton cancelButton;

    public DownLoadDialog() {
        this.dialog = new JDialog();
        this.progressBar = new JProgressBar();
        this.fileName = new JLabel("filename");
        this.cancelButton = new JButton("cancel");
        var panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.NORTH);
        panel.add(fileName, BorderLayout.CENTER);
        panel.add(cancelButton, BorderLayout.SOUTH);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setModal(true);
        dialog.setTitle("downloading...");
        dialog.setLocationRelativeTo(null);
    }

    public ShowDownloadFileNameDelegate getShowDownloadFileNameDelegate() {
        return new ShowDownloadFileNameDelegate(s -> fileName.setText(s));
    }

    public PropertyChangeListener getProgressBarCreator() {
        return new DeterminateProgressBarCreator(progressBar);
    }

    public WorkerCompletionWaiter getWorkerCompletionWaiter() {
        return new WorkerCompletionWaiter(dialog);
    }

    public void show() {
        dialog.setVisible(true);
    }

}
