package com.ssr.image.downloader.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ProgressMonitor;

public class DeterminateProgressMonitorListener implements PropertyChangeListener {

    private final ProgressMonitor monitor;

    public DeterminateProgressMonitorListener(ProgressMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("progress")) {
            monitor.setProgress((int) evt.getNewValue());
        }

    }

}
