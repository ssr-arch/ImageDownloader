package com.ssr.image.downloader.listener;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class OpenAddUrlDialogAction extends AbstractAction {

    private final JDialog dialog;
    private final TableModel tableModel;

    public OpenAddUrlDialogAction(TableModel tableModel) {
        this.tableModel = tableModel;
        this.dialog = new JDialog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var urlField = new JTextField();
        var okButton = new JButton("ok");
        var panel = new JPanel();
        urlField.setPreferredSize(new DimensionUIResource(300, 20));
        okButton.setPreferredSize(new DimensionUIResource(80, 20));
        okButton.addActionListener(
                new ConfirmUrlAction(
                        (DefaultTableModel) tableModel,
                        urlField.getText(),
                        dialog));
        panel.add(urlField);
        panel.add(okButton);
        dialog.setTitle("add url");
        dialog.getContentPane().add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

}
