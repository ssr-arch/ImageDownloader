package com.ssr.image.downloader.model.html;

public class ImageSource {

    private final String source;
    private final String fileName;

    public ImageSource(String source) {
        // test
        this.source = source;
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

    @Override
    public int hashCode() {
        return source.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImageSource)) {
            return false;
        }
        return ((ImageSource) obj).source.equals(source);
    }

    public String getFormat() {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getFileName() {
        return fileName;
    }

    public String getDownLoadUrl() {
        return source;
    }

}
