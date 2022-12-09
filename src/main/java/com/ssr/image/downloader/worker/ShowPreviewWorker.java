package com.ssr.image.downloader.worker;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;

import com.ssr.image.downloader.model.ImageCache;
import com.ssr.image.downloader.model.ImageSource;
import com.ssr.image.downloader.ui.dialog.PreviewDialog;

public class ShowPreviewWorker extends SwingWorker<BufferedImage, String> {

    private final ImageSource source;
    private final PreviewDialog dialog;

    public ShowPreviewWorker(ImageSource source, PreviewDialog dialog) {
        this.source = source;
        this.dialog = dialog;
    }

    @Override
    protected BufferedImage doInBackground() throws Exception {
        var downloadUrl = source.getDownLoadUrl();
        var imageCache = ImageCache.getInstance();
        var cachedImage = imageCache.get(source);
        if (cachedImage != null) {
            return cachedImage;
        }
        var downloadImage = ImageIO.read(new ByteArrayInputStream(Jsoup
                .connect(downloadUrl)
                .ignoreContentType(true)
                .execute()
                .bodyAsBytes()));
        imageCache.add(source, downloadImage);
        return downloadImage;
    }

    @Override
    protected void done() {
        try {
            BufferedImage image = get();
            dialog.setImage(image);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
