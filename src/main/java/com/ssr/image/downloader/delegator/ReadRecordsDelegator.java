package com.ssr.image.downloader.delegator;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.TableRecord;

public class ReadRecordsDelegator {

    private final DefaultTableModel model;

    public ReadRecordsDelegator(DefaultTableModel model) {
        this.model = model;
    }

    public List<TableRecord> read() {
        return model.getDataVector()
                .stream()
                .map(TableRecord::new)
                .toList();
    }

}
