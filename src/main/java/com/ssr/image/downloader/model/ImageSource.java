package com.ssr.image.downloader.model;

public class ImageSource {

    private final String source;
    private final String fileName;

    public ImageSource(String accessUrl, String source) {
        this.source = null;
        throw new RuntimeException();
    }

    public ImageSource(ImageAbsolutePath absolutePath) {
        // test
        this.source = absolutePath.toString();
        var path = absolutePath.toString();
        var fileName = path.substring(path.lastIndexOf("/") + 1);
        if (!fileName.contains(".")) {
            fileName = String.join(".", fileName, "jpg");
        }
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return source;
    }

    public String getFormat() {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getName() {
        return fileName;
    }

}
