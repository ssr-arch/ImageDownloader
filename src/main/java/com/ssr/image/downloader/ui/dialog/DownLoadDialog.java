package com.ssr.image.downloader.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class DownLoadDialog extends JDialog {

    private final JProgressBar progressBar;
    private final JLabel fileName;
    private final JButton cancelButton;

    public DownLoadDialog() {
        this.fileName = new JLabel("filename");
        this.progressBar = new JProgressBar();
        this.cancelButton = new JButton("cancel");
        var panel = new JPanel(new BorderLayout());
        panel.add(fileName, BorderLayout.CENTER);
        panel.add(progressBar, BorderLayout.NORTH);
        panel.add(cancelButton, BorderLayout.SOUTH);
        getContentPane().add(panel);
        pack();
        setModal(true);
        setTitle("downloading...");
        setLocationRelativeTo(null);
    }

}
