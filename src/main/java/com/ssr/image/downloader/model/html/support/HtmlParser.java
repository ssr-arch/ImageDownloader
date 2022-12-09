package com.ssr.image.downloader.model.html.support;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class HtmlParser {

    private final String html;

    public HtmlParser(String html) {
        this.html = html;
    }

    public List<String> getImageSources() {
        var pattern = Pattern.compile("\\\"[^\\s]*\\.(png|jpg)\\\"");
        List<String> sources = new ArrayList<>();
        for (var line : html.split("\n")) {
            var matcher = pattern.matcher(line);
            while (matcher.find()) {
                sources.add(matcher.group());
            }
        }
        return sources;
    }

}
