package main;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloaderHttps extends Thread{

    private static final int BUFFER_SIZE = 4096;
    public static int times = 0;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";

    private main.File file1;

    /**
     * constructor of the DownloaderHttps class
     * @param file1 file
     */
    public DownloaderHttps(main.File file1){
        this.file1 = file1;
    }

    /**
     * method to download
     * @param file file
     * @throws IOException exception
     */
    public static void downloadFileHttps(main.File file) throws IOException {
        URL url = new URL(file.getURL());
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();

        int responseCode = httpsConn.getResponseCode();
        httpsConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

        if (responseCode == HttpsURLConnection.HTTP_OK) {

            String disposition = httpsConn.getHeaderField("Content-Disposition");

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    file.setName(disposition.substring(index + 10, disposition.length() - 1));
                }
            } else {
                // extracts file name from URL
                file.setName(file.getURL().substring(file.getURL().lastIndexOf("/") + 1, file.getURL().length()));
            }

            InputStream inputStream = httpsConn.getInputStream();
            String saveFilePath = SettingsWindow.saveDirectory + java.io.File.separator + file.getName();

            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            RunProgressBarHttps progressBar = new RunProgressBarHttps(file);
            progressBar.start();

            Thread threadDownloadSpeed = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!file.isDownloaded()){
                        file.getLabelSpeed().setText("Speed:    " + String.valueOf(file.getDownloadSpeed()) + "  MB");
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            threadDownloadSpeed.start();

            MainWindow.numToLimit++;

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                Long start = System.nanoTime();
                outputStream.write(buffer, 0, bytesRead);
                Long end = System.nanoTime();
                long timeTakes = (end - start);
                file.setDownloadSpeed(4 * 1000000 / timeTakes);
                times++;
            }

            outputStream.close();
            inputStream.close();

            file.isDownloadedTrue();
            file.setStatus(100);

            MainWindow.numToLimit--;

            System.out.println(ANSI_GREEN + file.getName() + "   Was Downloaded" + ANSI_RESET);
        } else {
            System.err.println("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpsConn.disconnect();
    }

    /**
     * Thread run
     */
    @Override
    public void run() {
        try {
            downloadFileHttps(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
