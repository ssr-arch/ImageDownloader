package com.ssr.image.downloader.model;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;

import com.ssr.image.downloader.model.html.ImageSource;

public class DownloadDirectory {

    private final Path directory;

    public DownloadDirectory() {
        var downLoadHome = Path.of(System.getProperty("user.home"), "Downloads");
        var timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        this.directory = downLoadHome.resolve("image-downloader").resolve(timeStamp);
    }

    public boolean create() {
        return directory.toFile().mkdirs();
    }

    public void saveImage(RenderedImage im, ImageSource source) throws IOException {
        var file = directory.resolve(source.getFileName()).toFile();
        ImageIO.write(im, source.getFormat(), file);
    }

}
