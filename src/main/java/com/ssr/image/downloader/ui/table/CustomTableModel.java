package com.ssr.image.downloader.ui.table;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.ImageSource;

public class CustomTableModel extends DefaultTableModel {

    public CustomTableModel() {
        addColumn("ALL");
        addColumn("FileName");
        addColumn("Preview");
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Boolean.class;
            case 1:
                return ImageSource.class;
            case 2:
                return ImageIcon.class;
        }
        throw new RuntimeException();
    }

    public int getSelectedRowCount() {
        return getDataVector()
                .stream()
                .map(v -> (Boolean) v.elementAt(0))
                .filter(b -> b)
                .toList()
                .size();
    }

    public void setAllChecks(boolean value) {
        var rowCount = getRowCount();
        for (int i = 0; i < rowCount; i++) {
            setValueAt((Boolean) value, i, 0);
        }
    }

}
