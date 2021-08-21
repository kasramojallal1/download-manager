package main;

import javax.swing.*;

public class File implements java.io.Serializable{
    private String name;
    private String URL;
    private long downloadSpeed = 0;
    private boolean isDownloaded;
    private String timeAdded;
    private int status = 0;
    private float Size = 0;
    private long bytes = 0;
    private JProgressBar progressBar;
    private JPanel panel;
    private JLabel label;
    private JLabel labelSize;
    private JLabel labelSpeed;

    /**
     * constructor of the File class
     * @param name name
     * @param URL URL
     * @param timeAdded time added
     */
    public File(String name, String URL, String timeAdded) {
        this.name = name;
        this.URL = URL;
        this.timeAdded = timeAdded;
    }

    public String getURL() {
        return URL;
    }

    public String getTimeAdded() {
        return timeAdded;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public void isDownloadedTrue(){
        isDownloaded = true;
    }

    public void isDownloadedFalse(){
        isDownloaded = false;
    }

    public float getSize() {
        return Size;
    }

    public void setSize(float size) {
        Size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JLabel getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(JLabel labelSize) {
        this.labelSize = labelSize;
    }

    public JLabel getLabelSpeed() {
        return labelSpeed;
    }

    public void setLabelSpeed(JLabel labelSpeed) {
        this.labelSpeed = labelSpeed;
    }

    public long getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(long downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    public boolean getIsDownloaded(){
        return isDownloaded;
    }
}
