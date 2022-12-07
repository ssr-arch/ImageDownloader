package com.ssr.image.downloader.worker;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ssr.image.downloader.model.ImageAbsolutePath;
import com.ssr.image.downloader.model.ImageSource;

public class GetImageSourcesWorker extends SwingWorker<List<ImageSource>, String> {

    private final String url;

    public GetImageSourcesWorker(String url) {
        this.url = url;
    }

    @Override
    protected List<ImageSource> doInBackground() throws Exception {
        if (url == null || url.length() == 0) {
            return new ArrayList<>();
        }
        Element body = Jsoup.connect(url).get().body();
        Elements elements = body.getElementsByTag("img");
        // base64形式はスルー
        var a = elements.stream()
                .filter(e -> e.attributes().get("src") != null)
                .map(e -> e.attributes().get("src"))
                .filter(s -> !s.startsWith("data"))
                .map(s -> new ImageAbsolutePath(url, s))
                .map(ImageSource::new)
                .toList();
        return a;
    }

}
