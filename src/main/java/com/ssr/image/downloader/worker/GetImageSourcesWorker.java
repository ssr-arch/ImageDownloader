package com.ssr.image.downloader.worker;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.SwingWorker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ssr.image.downloader.model.ImageAbsolutePath;
import com.ssr.image.downloader.model.ImageSource;

public class GetImageSourcesWorker extends SwingWorker<List<ImageSource>, String> {

    private final URL url;

    public GetImageSourcesWorker(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    @Override
    protected List<ImageSource> doInBackground() throws Exception {
        Element body = Jsoup.connect(url.toString()).get().body();
        Elements elements = body.getElementsByTag("img");
        // base64,svgはスルー lazyloadはどうするか？
        var a = elements.stream()
                .filter(e -> e.attributes().get("src") != null)
                .map(e -> e.attributes().get("src"))
                .filter(s -> !s.startsWith("data"))
                .filter(s -> !s.contains(".svg"))
                .map(s -> new ImageAbsolutePath(url.toString(), s))
                .map(ImageSource::new)
                .toList();
        return a;
    }

}
