package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.ssr.image.downloader.delegate.InsertRowsDelegate;
import com.ssr.image.downloader.ui.dialog.AddUrlDialog;

public class AddUrlAction extends AbstractAction {

    private final InsertRowsDelegate insertRowsDelegator;

    public AddUrlAction(InsertRowsDelegate delegator) {
        this.insertRowsDelegator = delegator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var dialog = new AddUrlDialog(insertRowsDelegator);
        dialog.show();
    }

}
