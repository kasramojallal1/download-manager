package main;

public class RunProgressBarHttps extends Thread{

    private File file;

    /**
     * constructor of the RunProgressBarHttps class
     * @param file file
     */
    public RunProgressBarHttps(File file){
        this.file = file;
    }

    /**
     * Thread run
     */
    @Override
    public void run() {
        long whole = file.getBytes() / 4096;
        while (DownloaderHttps.times < whole){
            file.getProgressBar().setValue((int) (DownloaderHttps.times * 100 / whole));
            System.out.println(file.getName() + "   is downloading");
        }
        file.getProgressBar().setValue(100);

    }
}
