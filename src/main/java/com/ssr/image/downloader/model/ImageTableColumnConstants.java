package com.ssr.image.downloader.model;

public enum ImageTableColumnConstants {
    ALL("ALL", 0),
    FILE_NAME("FileName", 1),
    PREVIEW("Preview", 2);

    private final String value;
    private final int column;

    private ImageTableColumnConstants(String value, int column) {
        this.value = value;
        this.column = column;
    }

    public String value() {
        return value;
    }

    public int column() {
        return column;
    }

}
