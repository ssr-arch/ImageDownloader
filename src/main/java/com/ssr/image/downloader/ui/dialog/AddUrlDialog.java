package com.ssr.image.downloader.ui.dialog;

import java.util.function.Supplier;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;

public class AddUrlDialog {

    private final JDialog dialog;
    private final JTextField urlField;
    private final JButton okButton;

    public AddUrlDialog() {
        this.dialog = new JDialog();
        this.urlField = new JTextField();
        this.okButton = new JButton("ok");
        urlField.setPreferredSize(new DimensionUIResource(300, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        var panel = new JPanel();
        panel.add(urlField);
        panel.add(okButton);
        dialog.setTitle("add url");
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
    }

    public void show() {
        dialog.setVisible(true);
    }

    public void addOkButtonAction(Action action) {
        okButton.addActionListener(action);
    }

    public Supplier<String> createUrlGetter() {
        return () -> urlField.getText();
    }

}
