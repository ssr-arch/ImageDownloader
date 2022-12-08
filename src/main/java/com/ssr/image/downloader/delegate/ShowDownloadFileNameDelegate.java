package com.ssr.image.downloader.delegate;

import java.util.function.Consumer;

public class ShowDownloadFileNameDelegate {

    private final Consumer<String> action;

    public ShowDownloadFileNameDelegate(Consumer<String> action) {
        this.action = action;
    }

    public void invoke(String fileName) {
        action.accept(fileName);
    }

}
