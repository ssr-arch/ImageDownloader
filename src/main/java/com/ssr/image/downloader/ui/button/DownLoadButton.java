package com.ssr.image.downloader.ui.button;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.ssr.image.downloader.delegator.ReadRecordsDelegator;

public class DownLoadButton {

    private final JButton button;

    public DownLoadButton(ReadRecordsDelegator readRowsDelegator) {
        this.button = new JButton("download");
        button.addActionListener(e -> {
            var tableRecords = readRowsDelegator.read();
            tableRecords.forEach(System.out::println);
        });
    }

    public JComponent get() {
        return button;
    }
}
