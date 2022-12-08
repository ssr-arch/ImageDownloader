package com.ssr.image.downloader.listener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.SwingWorker;

public class WorkerCompletionWaiter implements PropertyChangeListener {

    private final JDialog dialog;

    public WorkerCompletionWaiter(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            if (evt.getNewValue().equals(SwingWorker.StateValue.DONE)) {
                dialog.dispose();
            }
        }
    }

}
