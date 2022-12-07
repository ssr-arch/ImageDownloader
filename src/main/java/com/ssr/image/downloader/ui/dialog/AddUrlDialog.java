package com.ssr.image.downloader.ui.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.DimensionUIResource;

import com.ssr.image.downloader.delegator.InsertRowsDelegator;
import com.ssr.image.downloader.listener.ConfirmUrlAction;

public class AddUrlDialog {

    private final JDialog dialog;

    public AddUrlDialog(InsertRowsDelegator insertRowsDelegator) {
        this.dialog = new JDialog();
        var panel = new JPanel();
        var inputField = new JTextField();
        var okButton = new JButton("ok");
        var cancelButton = new JButton("cancel");
        inputField.setPreferredSize(new DimensionUIResource(200, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        okButton.addActionListener(new ConfirmUrlAction(insertRowsDelegator, inputField));
        cancelButton.setPreferredSize(new DimensionUIResource(80, 20));
        cancelButton.addActionListener(e -> dialog.dispose());
        panel.add(inputField);
        panel.add(okButton);
        panel.add(cancelButton);
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
