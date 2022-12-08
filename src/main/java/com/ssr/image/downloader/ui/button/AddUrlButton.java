package com.ssr.image.downloader.ui.button;

import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JComponent;

import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.ui.dialog.AddUrlDialog;

public class AddUrlButton {

    private final JButton addUrl;

    public AddUrlButton(Consumer<TableRecord[]> insertRecordsAction) {
        this.addUrl = new JButton();
        addUrl.setText("add url");
        addUrl.addActionListener(e -> {
            var dialog = new AddUrlDialog(insertRecordsAction);
            dialog.show();
        });
    }

    public JComponent get() {
        return addUrl;
    }

}
