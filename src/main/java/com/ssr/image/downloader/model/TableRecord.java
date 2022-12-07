package com.ssr.image.downloader.model;

import java.util.Vector;

import javax.swing.ImageIcon;

public class TableRecord {

    private final Boolean checkBox;
    private final ImageSource imageSrc;
    private final ImageIcon previewIcon;

    public TableRecord(ImageSource imageSrc) {
        this.checkBox = Boolean.FALSE;
        this.imageSrc = imageSrc;
        this.previewIcon = new ImageIcon(getClass().getClassLoader().getResource("images/preview.png"));
    }

    public TableRecord(Vector vector) {
        this.checkBox = (Boolean) vector.get(0);
        this.imageSrc = (ImageSource) vector.get(1);
        this.previewIcon = (ImageIcon) vector.get(2);
    }

    public Object[] getRowData() {
        return new Object[] { checkBox, imageSrc, previewIcon };
    }

    @Override
    public String toString() {
        return imageSrc.toString();
    }

}
