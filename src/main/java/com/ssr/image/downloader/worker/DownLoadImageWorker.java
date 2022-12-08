package com.ssr.image.downloader.worker;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.jsoup.Jsoup;

import com.ssr.image.downloader.model.DownloadDirectory;
import com.ssr.image.downloader.model.ImageSource;

public class DownLoadImageWorker extends SwingWorker<String, String> {

    private final List<ImageSource> sources;
    private final Consumer<String> setFileNameAction;

    public DownLoadImageWorker(List<ImageSource> sources, Consumer<String> setFileNameAction) {
        this.sources = sources;
        this.setFileNameAction = setFileNameAction;
    }

    @Override
    protected String doInBackground() throws Exception {
        var directory = new DownloadDirectory();
        if (!directory.create()) {
            throw new SecurityException("not permitted create directory");
        }
        for (int i = 0; i < sources.size(); i++) {
            Thread.sleep(1000);
            publish(sources.get(i).getFileName());
            var bufferedImage = ImageIO.read(new ByteArrayInputStream(
                    Jsoup.connect(sources.get(i).getDownLoadUrl())
                            .ignoreContentType(true)
                            .execute()
                            .bodyAsBytes()));
            directory.saveImage(bufferedImage, sources.get(i));
            var percent = ((float) (i + 1) / (float) sources.size()) * 100;
            setProgress((int) percent);
        }
        return String.format("downloaded %s files", String.valueOf(sources.size()));
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
