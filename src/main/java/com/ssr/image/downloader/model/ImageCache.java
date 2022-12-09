package com.ssr.image.downloader.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.ssr.image.downloader.model.html.ImageSource;

public class ImageCache {

    private static final ImageCache imageCache = new ImageCache();
    private final Map<ImageSource, BufferedImage> urlToImage;

    private ImageCache() {
        this.urlToImage = new HashMap<>();
    }

    public static ImageCache getInstance() {
        return imageCache;
    }

    public void add(ImageSource source, BufferedImage image) {
        urlToImage.put(source, image);
    }

    public BufferedImage get(ImageSource source) {
        return urlToImage.get(source);
    }

}
