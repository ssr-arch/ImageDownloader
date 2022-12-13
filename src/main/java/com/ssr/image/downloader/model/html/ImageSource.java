package com.ssr.image.downloader.model.html;

import com.ssr.image.downloader.model.ImageAbsolutePath;

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

    @Override
    public int hashCode() {
        return absolutePath.toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImageSource)) {
            return false;
        }
        return ((ImageSource) obj).absolutePath.equals(this);
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
