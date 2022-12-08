package com.ssr.image.downloader.ui.table;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.ssr.image.downloader.model.TableRecord;

public class ImageTable {

    private final CustomTableModel model;
    private final JTable table;

    public ImageTable() {
        this.model = new CustomTableModel();
        this.table = new JTable(model);
    }

    public Consumer<TableRecord[]> createInsertRecordAction() {
        return t -> {
            // discard all elements before inserting elements
            model.setRowCount(0);
            Arrays.asList(t).forEach(r -> model.addRow(r.getRowData()));
        };
    }

    public Supplier<List<TableRecord>> createGetCheckedRecordsGetter() {
        return () -> model.getDataVector()
                .stream()
                .map(TableRecord::new)
                .filter(TableRecord::isChecked)
                .toList();
    }

    public JComponent get() {
        return new JScrollPane(table);
    }

}
