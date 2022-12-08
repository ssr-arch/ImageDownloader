package com.ssr.image.downloader.worker;

import java.io.ByteArrayInputStream;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;

import com.ssr.image.downloader.delegate.ShowDownloadFileNameDelegate;
import com.ssr.image.downloader.model.ImageSource;

public class DownLoadImageWorker extends SwingWorker<String, String> {

    private final List<ImageSource> sources;
    private final ShowDownloadFileNameDelegate showDownloadFileNameDelegator;
    private final LocalDateTime workedAt;

    public DownLoadImageWorker(List<ImageSource> sources, ShowDownloadFileNameDelegate showDownloadFileNameDelegator) {
        this.sources = sources;
        this.showDownloadFileNameDelegator = showDownloadFileNameDelegator;
        this.workedAt = LocalDateTime.now();
    }

    @Override
    protected String doInBackground() throws Exception {
        for (int i = 0; i < sources.size(); i++) {
            Thread.sleep(1000);
            publish(sources.get(i).getFileName());
            var downloadDirectory = Path.of(System.getProperty("user.home"), "Downloads");
            var dateDirectory = workedAt.format(DateTimeFormatter.ofPattern("yyyyMMdd_hhmmss"));
            var saveDirectory = downloadDirectory.resolve("image-downloader").resolve(dateDirectory);
            saveDirectory.toFile().mkdirs();
            var bufferedImage = ImageIO.read(new ByteArrayInputStream(
                    Jsoup.connect(sources.get(i).getDownLoadUrl())
                            .ignoreContentType(true)
                            .execute()
                            .bodyAsBytes()));
            var saveFile = saveDirectory.resolve(sources.get(i).getFileName()).toFile();
            ImageIO.write(bufferedImage, sources.get(i).getFormat(), saveFile);
            var percent = ((float) (i + 1) / (float) sources.size()) * 100;
            setProgress((int) percent);
        }
        return String.format("downloaded %s files", String.valueOf(sources.size()));
    }

    @Override
    protected void process(List<String> chunks) {
        showDownloadFileNameDelegator.invoke(chunks.get(0));
    }

    @Override
    protected void done() {
        try {
            String message = get();
            JOptionPane.showMessageDialog(null,
                    message,
                    "download complete",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "failed download",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
