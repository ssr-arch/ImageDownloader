package com.ssr.image.downloader.ui.table;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.swing.JTable;

import com.ssr.image.downloader.listener.ImageTableHeaderClickAdapter;
import com.ssr.image.downloader.listener.ImageTablePreviewClickAdapter;
import com.ssr.image.downloader.model.TableRecord;

public class ImageTable extends JTable {

    private final ImageTableModel model;

    public ImageTable() {
        this.model = new ImageTableModel();
        setModel(model);
        getTableHeader().addMouseListener(new ImageTableHeaderClickAdapter(this));
        addMouseListener(new ImageTablePreviewClickAdapter(this));
    }

    @Override
    public int getSelectedRowCount() {
        return model.getSelectedRowCount();
    }

    @Override
    public void selectAll() {
        if (model != null) {
            model.setAllChecks(true);
        }
    }

    @Override
    public void clearSelection() {
        if (model != null) {
            model.setAllChecks(false);
        }
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

}
