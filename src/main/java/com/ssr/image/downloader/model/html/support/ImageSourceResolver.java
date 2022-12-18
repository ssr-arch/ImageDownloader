package com.ssr.image.downloader.model.html.support;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageSourceResolver {

    private final String accessUrl;

    public ImageSourceResolver(String url) {
        this.accessUrl = url;
    }

    public String resolve(String source) throws MalformedURLException {
        source = source.replace("\"", "");
        var url = new URL(accessUrl);
        if (source.startsWith("http") || source.startsWith("https")) {
            return source;
        }
        if (source.startsWith("//")) {
            return url.getProtocol() + source;
        }
        return new StringBuilder()
                .append(url.getProtocol())
                .append("://")
                .append(url.getHost())
                .append("/")
                .append(source)
                .toString();
    }

}
