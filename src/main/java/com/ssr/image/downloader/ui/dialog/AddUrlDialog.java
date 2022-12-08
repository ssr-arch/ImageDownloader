package com.ssr.image.downloader.ui.dialog;

import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

import com.ssr.image.downloader.listener.ConfirmUrlAction;
import com.ssr.image.downloader.model.TableRecord;

public class AddUrlDialog {

    private final JDialog dialog;
    private final JTextField urlField;
    private final JButton okButton;

    public AddUrlDialog(Consumer<TableRecord[]> insertRecordsAction) {
        this.dialog = new JDialog();
        this.urlField = new JTextField();
        this.okButton = new JButton("ok");
        urlField.setPreferredSize(new DimensionUIResource(300, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        okButton.addActionListener(new ConfirmUrlAction(insertRecordsAction, this));
        var panel = new JPanel();
        panel.add(urlField);
        panel.add(okButton);
        dialog.setTitle("add url");
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
    }

    public String getUrl() {
        return urlField.getText();
    }

    public void show() {
        dialog.setVisible(true);
    }

    public void dispose() {
        dialog.dispose();
    }

}
