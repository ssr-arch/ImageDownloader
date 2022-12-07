package com.ssr.image.downloader.ui.table;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ssr.image.downloader.delegator.InsertRowsDelegator;
import com.ssr.image.downloader.delegator.ReadRecordsDelegator;

public class ImageTable {

    private final CustomTableModel model;
    private final JTable table;

    public ImageTable() {
        this.model = new CustomTableModel();
        this.table = new JTable(model);
    }

    public InsertRowsDelegator getInsertRowsDelegator() {
        return new InsertRowsDelegator(model);
    }

    public ReadRecordsDelegator getReadRowsDelegator() {
        return new ReadRecordsDelegator(model);
    }

    public JComponent get() {
        return new JScrollPane(table);
    }

}
