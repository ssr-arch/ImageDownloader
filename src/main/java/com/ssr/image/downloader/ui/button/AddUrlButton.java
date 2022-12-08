package com.ssr.image.downloader.ui.button;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.ssr.image.downloader.delegate.InsertRowsDelegate;
import com.ssr.image.downloader.listener.AddUrlAction;

public class AddUrlButton {

    private final JButton addUrl;

    public AddUrlButton(InsertRowsDelegate insertRowsDelegator) {
        this.addUrl = new JButton();
        addUrl.setText("add url");
        addUrl.addActionListener(new AddUrlAction(insertRowsDelegator));
    }

    public JComponent get() {
        return addUrl;
    }

}
