package com.ssr.image.downloader;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.ssr.image.downloader.ui.MainPanel;

public class ImageDownloaderApplication {
    public static void main(String[] args) {
        var frame = new JFrame("image-downloader");
        frame.setLayout(new BorderLayout());
        frame.add(new MainPanel(), BorderLayout.CENTER);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
