package com.ssr.image.downloader.ui.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;

import com.ssr.image.downloader.delegate.InsertRowsDelegate;
import com.ssr.image.downloader.listener.ConfirmUrlAction;

public class AddUrlDialog {

    private final JDialog dialog;

    public AddUrlDialog(InsertRowsDelegate insertRowsDelegator) {
        this.dialog = new JDialog();
        var panel = new JPanel();
        var inputField = new JTextField();
        var okButton = new JButton("ok");
        inputField.setPreferredSize(new DimensionUIResource(200, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        okButton.addActionListener(new ConfirmUrlAction(insertRowsDelegator, inputField));
        panel.add(inputField);
        panel.add(okButton);
        dialog.setTitle("add url");
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public void show() {
        dialog.setVisible(true);
    }

}
