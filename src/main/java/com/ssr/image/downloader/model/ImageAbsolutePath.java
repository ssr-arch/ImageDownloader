package com.ssr.image.downloader.model;

import java.net.MalformedURLException;
import java.net.URL;

public class ImageAbsolutePath {

    private final String path;

    public ImageAbsolutePath(String access, String source) {
        this.path = create(access, source);
    }

    @Override
    public String toString() {
        return path;
    }

    // test
    private String create(String access, String source) {
        if (isAbsolutePath(source)) {
            return removeQuery(source);
        }
        if (isBase64(source)) {
            return "not supported base64";
        }
        try {
            var accessUrl = new URL(access);
            var rootPath = getRootPath(accessUrl);
            var trimed = trimFirstSlash(source);
            return String.join("/", rootPath, trimed);
        } catch (MalformedURLException e) {
            return "failed fetch";
        }
    }

    private boolean isBase64(String sourceUrl) {
        return sourceUrl.toString().startsWith("data:image");
    }

    private boolean isAbsolutePath(String path) {
        return (path.startsWith("http") || path.startsWith("https"));
    }

    private String removeQuery(String path) {
        try {
            var query = new URL(path).getQuery();
            if (query == null) {
                return path;
            }
            var querySyntax = String.join("", "?", query);
            return path.replace(querySyntax, "");
        } catch (MalformedURLException e) {
            return "failed fetch";
        }
    }

    private String getRootPath(URL url) {
        var protocol = url.getProtocol();
        var host = url.getHost();
        return String.join("://", protocol, host);
    }

    private String trimFirstSlash(String sourceUrl) {
        if (!sourceUrl.startsWith("/")) {
            return sourceUrl;
        }
        return sourceUrl.substring(1);
    }

}
