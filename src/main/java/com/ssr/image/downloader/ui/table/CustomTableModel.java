package com.ssr.image.downloader.ui.table;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import com.ssr.image.downloader.model.ImageSource;

public class CustomTableModel extends DefaultTableModel {

    public CustomTableModel() {
        addColumn("check");
        addColumn("src");
        addColumn("preview");
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

}
