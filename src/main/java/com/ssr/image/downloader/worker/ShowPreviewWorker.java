package com.ssr.image.downloader.worker;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import javax.swing.plaf.DimensionUIResource;

import org.jsoup.Jsoup;

import com.ssr.image.downloader.model.ImageCache;

public class ShowPreviewWorker extends SwingWorker<BufferedImage, String> {

    private final JLabel iconLabel;
    private String url;
    private JDialog dialog;

    public ShowPreviewWorker(JLabel iconLabel, String url, JDialog dialog) {
        this.iconLabel = iconLabel;
        this.url = url;
        this.dialog = dialog;
    }

    @Override
    protected BufferedImage doInBackground() throws Exception {
        var imageCache = ImageCache.getInstance();
        var cachedImage = imageCache.get(url);
        if (cachedImage != null) {
            return cachedImage;
        }
        var downloadImage = ImageIO.read(new ByteArrayInputStream(Jsoup
                .connect(url)
                .ignoreContentType(true)
                .execute()
                .bodyAsBytes()));
        imageCache.add(url, downloadImage);
        return downloadImage;
    }

    @Override
    protected void done() {
        try {
            BufferedImage image = get();
            var dimension = new DimensionUIResource(image.getWidth(), image.getHeight());
            iconLabel.setIcon(new ImageIcon(image));
            iconLabel.setPreferredSize(dimension);
            dialog.setTitle("preview");
            dialog.getContentPane().add(iconLabel);
            dialog.pack();
            dialog.setLocationRelativeTo(null);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
