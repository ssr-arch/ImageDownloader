package com.ssr.image.downloader.ui.dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.listener.ConfirmUrlAction;

public class AddUrlDialog extends JDialog {

    private final JTextField urlField;
    private final JButton okButton;

    public AddUrlDialog(DefaultTableModel tableModel) {
        this.urlField = new JTextField();
        this.okButton = new JButton("ok");
        urlField.setPreferredSize(new DimensionUIResource(300, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        okButton.addActionListener(new ConfirmUrlAction(tableModel, urlField, this));
        var panel = new JPanel();
        panel.add(urlField);
        panel.add(okButton);
        setTitle("add url");
        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public String getUrl() {
        return urlField.getText();
    }

}
