package com.ssr.image.downloader.ui.dialog;

import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.plaf.DimensionUIResource;

public class PreviewDialog {

    private final Point clickedAt;
    private final JDialog dialog;
    private final JLabel imageLabel;

    public PreviewDialog(Point clickedAt) {
        this.clickedAt = clickedAt;
        this.dialog = new JDialog();
        this.imageLabel = new JLabel();
    }

    public void setImage(BufferedImage image) {
        var dimension = new DimensionUIResource(image.getWidth(), image.getHeight());
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setPreferredSize(dimension);
    }

    public void show() {
        dialog.setTitle("preview");
        dialog.getContentPane().add(imageLabel);
        dialog.pack();
        dialog.setLocation(clickedAt);
        dialog.setVisible(true);
    }

}
