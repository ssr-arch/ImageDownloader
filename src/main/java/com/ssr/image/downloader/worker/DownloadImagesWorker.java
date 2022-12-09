package com.ssr.image.downloader.worker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import com.ssr.image.downloader.model.DownloadDirectory;
import com.ssr.image.downloader.model.ImageCache;
import com.ssr.image.downloader.model.ImageSource;

public class DownloadImagesWorker extends SwingWorker<String, String> {

    private final ImageSource[] sources;
    private final Consumer<String> setFileNameAction;

    public DownloadImagesWorker(ImageSource[] sources, Consumer<String> setFileNameAction) {
        this.sources = sources;
        this.setFileNameAction = setFileNameAction;
    }

    @Override
    protected String doInBackground() throws Exception {
        var directory = new DownloadDirectory();
        if (!directory.create()) {
            throw new SecurityException("not permitted create directory");
        }
        var imageCache = ImageCache.getInstance();
        for (int i = 0; i < sources.length; i++) {
            var downloadUrl = sources[i].getDownLoadUrl();
            var cachedImage = imageCache.get(sources[i]);
            if (cachedImage != null) {
                directory.saveImage(cachedImage, sources[i]);
                continue;
            }
            setProgress(0);
            publish(sources[i].getFileName());
            var url = new URL(downloadUrl);
            var connection = url.openConnection();
            final var contentLength = connection.getContentLengthLong();
            var outputStream = new ByteArrayOutputStream((int) contentLength);
            try (var inputStream = url.openStream()) {
                var readBytes = 0;
                while ((readBytes = inputStream.read()) != -1) {
                    outputStream.write(readBytes);
                    var percent = ((float) outputStream.size() / (float) contentLength) * 100;
                    setProgress((int) percent);
                }
            }
            var downloadedImage = ImageIO.read(new ByteArrayInputStream(outputStream.toByteArray()));
            imageCache.add(sources[i], downloadedImage);
            directory.saveImage(downloadedImage, sources[i]);
            Thread.sleep(1000);
        }
        return String.format("downloaded %s files", String.valueOf(sources.length));

    }

    @Override
    protected void process(List<String> chunks) {
        setFileNameAction.accept(chunks.get(0));
    }

    @Override
    protected void done() {
        try {
            String message = get();
            JOptionPane.showMessageDialog(null,
                    message,
                    "download complete",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (CancellationException e) {
            JOptionPane.showMessageDialog(null,
                    "download cancelled",
                    "download cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "failed download",
                    "failed download",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
