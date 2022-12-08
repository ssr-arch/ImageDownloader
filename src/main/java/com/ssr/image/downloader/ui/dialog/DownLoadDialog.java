package com.ssr.image.downloader.ui.dialog;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class DownLoadDialog {

    private final JDialog dialog;
    private final JProgressBar progressBar;
    private final JLabel fileName;
    private final JButton cancelButton;

    public DownLoadDialog() {
        this.dialog = new JDialog();
        this.fileName = new JLabel("filename");
        this.progressBar = new JProgressBar();
        this.cancelButton = new JButton("cancel");
        var panel = new JPanel(new BorderLayout());
        panel.add(fileName, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.NORTH);
        panel.add(cancelButton, BorderLayout.SOUTH);
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setModal(true);
        dialog.setTitle("downloading...");
        dialog.setLocationRelativeTo(null);
    }

    public Consumer<String> createSetFileNameAction() {
        return t -> fileName.setText(t);
    }

    public Consumer<PropertyChangeEvent> createDeterminateProgressBarAction() {
        return t -> {
            if (t.getPropertyName().equals("progress")) {
                progressBar.setValue((int) t.getNewValue());
            }
        };
    }

    public Consumer<PropertyChangeEvent> createWorkerCompletionWaiterAction() {
        return t -> {
            if (t.getPropertyName().equals("state")) {
                if (t.getNewValue().equals(SwingWorker.StateValue.DONE)) {
                    dialog.dispose();
                }
            }
        };
    }

    public Consumer<SwingWorker> createCancelAction() {
        return t -> {
            cancelButton.addActionListener(e -> t.cancel(true));
        };
    }

    public void show() {
        dialog.setVisible(true);
    }

}
