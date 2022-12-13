package com.ssr.image.downloader.model;

import javax.swing.ImageIcon;

import com.ssr.image.downloader.model.html.ImageSource;

public class TableRecord {

    private final Boolean isChecked;
    private final ImageSource imageSource;
    private final ImageIcon previewIcon;

    public TableRecord(ImageSource imageSource) {
        this.isChecked = Boolean.FALSE;
        this.imageSource = imageSource;
        this.previewIcon = new ImageIcon(getClass().getClassLoader().getResource("images/preview.png"));
    }

    // for cell renderer
    @Override
    public String toString() {
        return imageSource.toString();
    }

    public Object[] getRowData() {
        return new Object[] { isChecked, imageSource, previewIcon };
    }

}
