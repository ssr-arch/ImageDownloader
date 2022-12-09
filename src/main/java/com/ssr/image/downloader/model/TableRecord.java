package com.ssr.image.downloader.model;

import java.util.Vector;

import javax.swing.ImageIcon;

import com.ssr.image.downloader.model.html.ImageSource;

public class TableRecord {

    private final Boolean isChecked;
    private final ImageSource imageSource;
    private final ImageIcon previewIcon;

    /**
     * create instance
     * @param imageSource
     */
    public TableRecord(ImageSource imageSource) {
        this.isChecked = Boolean.FALSE;
        this.imageSource = imageSource;
        this.previewIcon = new ImageIcon(getClass().getClassLoader().getResource("images/preview.png"));
        previewIcon.setDescription(imageSource.getDownLoadUrl());
    }

    /**
     * from table 
     * @param vector
     */
    public TableRecord(Vector vector) {
        this.isChecked = (Boolean) vector.get(0);
        this.imageSource = (ImageSource) vector.get(1);
        this.previewIcon = (ImageIcon) vector.get(2);
    }

    @Override
    public String toString() {
        return imageSource.toString();
    }

    public Object[] getRowData() {
        return new Object[] { isChecked, imageSource, previewIcon };
    }

    public boolean isChecked() {
        return isChecked.booleanValue();
    }

    public ImageSource getSource() {
        return imageSource;
    }

}
