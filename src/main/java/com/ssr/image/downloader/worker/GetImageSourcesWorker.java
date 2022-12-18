package com.ssr.image.downloader.worker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.SwingWorker;

import com.ssr.image.downloader.model.html.ImageSource;
import com.ssr.image.downloader.model.html.support.ImageSourceResolver;

public class GetImageSourcesWorker extends SwingWorker<List<ImageSource>, String> {

    private final URL url;

    public GetImageSourcesWorker(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    @Override
    protected List<ImageSource> doInBackground() throws Exception {
        var resolver = new ImageSourceResolver(url.toString());
        var html = new String(url.openStream().readAllBytes());
        var lines = html.split("\n");
        var pattern = Pattern.compile("\\\"[^\\s]*\\.(png|jpg)\\\"");
        List<ImageSource> sources = new ArrayList<>();
        for (var line : lines) {
            var matcher = pattern.matcher(line);
            while (matcher.find()) {
                sources.add(new ImageSource(resolver.resolve(matcher.group())));
            }
        }
        return sources;
    }

}
