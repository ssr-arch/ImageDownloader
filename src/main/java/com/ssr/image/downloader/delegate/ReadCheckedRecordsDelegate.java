package com.ssr.image.downloader.delegate;

import java.util.List;
import java.util.function.Supplier;

import com.ssr.image.downloader.model.TableRecord;

public class ReadCheckedRecordsDelegate {

    private final Supplier<List<TableRecord>> get;

    public ReadCheckedRecordsDelegate(Supplier<List<TableRecord>> get) {
        this.get = get;
    }

    public List<TableRecord> invoke() {
        return get.get();
    }

}
