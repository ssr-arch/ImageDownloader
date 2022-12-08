package com.ssr.image.downloader.delegate;

import java.util.function.Consumer;

import com.ssr.image.downloader.model.TableRecord;

public class InsertRowsDelegate {

    private final Consumer<TableRecord[]> action;

    public InsertRowsDelegate(Consumer<TableRecord[]> action) {
        this.action = action;
    }

    public void invoke(TableRecord[] records) {
        action.accept(records);
    }

}
