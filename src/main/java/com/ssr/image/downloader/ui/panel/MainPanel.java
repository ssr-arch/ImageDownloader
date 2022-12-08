package com.ssr.image.downloader.ui.panel;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import com.ssr.image.downloader.ui.button.AddUrlButton;
import com.ssr.image.downloader.ui.button.DownLoadButton;
import com.ssr.image.downloader.ui.table.ImageTable;

public class MainPanel {

    private final JPanel panel;

    public MainPanel() {
        this.panel = new JPanel(new BorderLayout());
        var table = new ImageTable();
        var footerPanel = new JPanel();
        var addUrlButton = new AddUrlButton(table.createInsertRecordAction());
        var downLoadButton = new DownLoadButton(table.createGetCheckedRecordsGetter());
        footerPanel.add(addUrlButton.get());
        footerPanel.add(downLoadButton.get());
        add(table.get(), BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public void add(JComponent component, String borderLayoutConstraint) {
        panel.add(component, borderLayoutConstraint);
    }

    public JComponent get() {
        return panel;
    }

}
