package com.ssr.image.downloader.model.html.support;

public class ImageSource2 {

    private String source;

    public ImageSource2(String source) {
        this.source = source;
    }

    public String getFileName() {
        throw new RuntimeException();
    }

    public String getFormat() {
        throw new RuntimeException();
    }

    public boolean hasProtocol() {
        return (source.startsWith("http") | (source.startsWith("https")));
    }

}
