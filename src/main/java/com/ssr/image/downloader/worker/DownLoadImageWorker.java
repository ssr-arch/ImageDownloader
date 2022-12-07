package com.ssr.image.downloader.worker;

import javax.swing.SwingWorker;

public class DownLoadImageWorker extends SwingWorker<String, String> {

    private final String[] sources;

    public DownLoadImageWorker(String[] sources) {
        this.sources = sources;
    }

    @Override
    protected String doInBackground() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
