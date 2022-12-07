package com.ssr.image.downloader.delegator;

import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.TableRecord;
import com.ssr.image.downloader.worker.InsertRowsWorker;

public class InsertRowsDelegator {

    private final DefaultTableModel model;

    public InsertRowsDelegator(DefaultTableModel model) {
        this.model = model;
    }

    public void add(TableRecord[] records) {
        var worker = new InsertRowsWorker(records, model);
        worker.execute();
    }

}
