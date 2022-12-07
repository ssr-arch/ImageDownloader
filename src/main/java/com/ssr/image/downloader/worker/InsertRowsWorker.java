package com.ssr.image.downloader.worker;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.TableRecord;

public class InsertRowsWorker extends SwingWorker<String, TableRecord> {

    private final TableRecord[] records;
    private final DefaultTableModel model;

    public InsertRowsWorker(TableRecord[] records, DefaultTableModel model) {
        this.records = records;
        this.model = model;
    }

    @Override
    protected String doInBackground() throws Exception {
        // clear records
        model.setRowCount(0);
        System.out.println("start process at the background");
        if (records.length == 0) {
            return "not found";
        }
        for (int i = 0; i < records.length; i++) {
            Thread.sleep(100);
            publish(records[i]);
            var percent = ((float) (i + 1) / (float) records.length) * 100;
            setProgress((int) percent);
        }
        return String.format("add: %s files", String.valueOf(records.length));
    }

    @Override
    protected void process(List<TableRecord> chunks) {
        model.addRow(chunks.get(0).getRowData());
    }

    @Override
    protected void done() {
        try {
            String message = get();
            JOptionPane.showMessageDialog(null, message, "add image", JOptionPane.INFORMATION_MESSAGE);
            setProgress(0);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error", "add image", JOptionPane.ERROR_MESSAGE);
        }
    }

}
