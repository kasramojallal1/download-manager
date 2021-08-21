package main;

public class RunProgressBarHttp extends Thread {

    private File file;

    /**
     * constructor of the RunProgressBarHttp class
     * @param file file
     */
    public RunProgressBarHttp(File file){
        this.file = file;
    }

    /**
     * Thread run
     */
    @Override
    public void run() {
        long whole = file.getBytes() / 4096;
        while (DownloaderHttp.times < whole){
            file.getProgressBar().setValue((int) (DownloaderHttp.times * 100 / whole));
            System.out.println(file.getName() + "   is downloading");
        }
        file.getProgressBar().setValue(100);

    }


}
