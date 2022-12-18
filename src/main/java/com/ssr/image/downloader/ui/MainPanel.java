package com.ssr.image.downloader.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ssr.image.downloader.listener.DownloadAction;
import com.ssr.image.downloader.listener.OpenAddUrlDialogAction;

public class MainPanel extends JPanel {

    private final JTable imageTable;
    private final JButton addUrlButton;
    private final JButton downloadButton;

    public MainPanel() {
        this.imageTable = new ImageTable();
        this.addUrlButton = new JButton("add url");
        this.downloadButton = new JButton("download");
        addUrlButton.addActionListener(new OpenAddUrlDialogAction(imageTable.getModel()));
        downloadButton.addActionListener(new DownloadAction(imageTable));
        var footerPanel = new JPanel();
        footerPanel.add(addUrlButton);
        footerPanel.add(downloadButton);
        setLayout(new BorderLayout());
        add(new JScrollPane(imageTable), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

}
