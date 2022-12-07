package com.ssr.image.downloader.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

public class DeterminateProgressBarCreator implements PropertyChangeListener {

    private final JProgressBar progressBar;

    public DeterminateProgressBarCreator(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            progressBar.setValue((int) evt.getNewValue());
        }
    }

}
