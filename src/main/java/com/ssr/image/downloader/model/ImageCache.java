package com.ssr.image.downloader.model;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

    private static final ImageCache imageCache = new ImageCache();
    private final Map<String, BufferedImage> urlToImage;

    private ImageCache() {
        this.urlToImage = new HashMap<>();
    }

    public static ImageCache getInstance() {
        return imageCache;
    }

    public void add(String url, BufferedImage image) {
        urlToImage.put(url, image);
    }

    public BufferedImage get(String url) {
        return urlToImage.get(url);
    }

}
