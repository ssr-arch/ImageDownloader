package com.ssr.image.downloader.ui.table;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.junit.jupiter.api.Test;

public class ImageTableTest {

    @Test
    void addCustomRow() {
        var frame = new JFrame();
        var model = new ImageTableModel();
        var table = new JTable(model);
        frame.add(new JScrollPane(table));
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println();
    }
}
