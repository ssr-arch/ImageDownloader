package com.ssr.image.downloader.model;

public class ImageSource {

    private final ImageAbsolutePath absolutePath;
    private final String source;
    private final String fileName;

    public ImageSource(ImageAbsolutePath absolutePath) {
        // test
        this.absolutePath = absolutePath;
        this.source = absolutePath.toString();
        var fileName = source.substring(source.lastIndexOf("/") + 1);
        if (!fileName.contains(".")) {
            fileName = String.join(".", fileName, "jpg");
        }
        this.fileName = fileName;
    }

    /* 
     * display to renderer
     */
    @Override
    public String toString() {
        return fileName;
    }

    public String getFormat() {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownLoadUrl() {
        return absolutePath.toString();
    }

}
