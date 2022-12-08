package com.ssr.image.downloader.ui.table;

import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ssr.image.downloader.delegate.InsertRowsDelegate;
import com.ssr.image.downloader.delegate.ReadCheckedRecordsDelegate;
import com.ssr.image.downloader.model.TableRecord;

public class ImageTable {

    private final CustomTableModel model;
    private final JTable table;

    public ImageTable() {
        this.model = new CustomTableModel();
        this.table = new JTable(model);
    }

    public InsertRowsDelegate getInsertRowsDelegator() {
        return new InsertRowsDelegate(records -> {
            // discard all elements before inserting elements
            model.setRowCount(0);
            Arrays.asList(records).forEach(r -> model.addRow(r.getRowData()));
        });
    }

    public ReadCheckedRecordsDelegate getReadRowsDelegator() {
        return new ReadCheckedRecordsDelegate(() -> {
            return model.getDataVector()
                    .stream()
                    .map(TableRecord::new)
                    .filter(TableRecord::isChecked)
                    .toList();
        });
    }

    public JComponent get() {
        return new JScrollPane(table);
    }

}
