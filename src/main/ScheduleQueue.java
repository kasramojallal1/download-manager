package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScheduleQueue {

    public static long startAfter = 0;
    public static JFrame scheduleFrame;
    public static long timeStarted = 0;
    public static long timeEnded = 0;

    /**
     * constructor of the ScheduleWindow class
     */
    public ScheduleQueue(){

        scheduleFrame = new JFrame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label2 = new JLabel(MainWindow.getText("Start after: ", ":شروع کن بعد از"));
        label2.setBounds(20,30,100,20);
        panel.add(label2);

        JTextField textField2 = new JTextField(MainWindow.getText("Seconds", "ثانیه"));
        textField2.setToolTipText("Text Field");
        textField2.setBounds(130,30,200,20);
        panel.add(textField2);

        JButton button2 = new JButton(MainWindow.getText("Set", "ثبت"));
        button2.setToolTipText("Set");
        button2.setBounds(360,30,100,20);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Integer.parseInt(textField2.getText());
                    startAfter = Integer.parseInt(textField2.getText());
                    textField2.setText("");
                }catch (NumberFormatException e1){
                    System.err.println("Please enter a number");
                }

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if(startAfter != 0){
                                timeStarted = System.currentTimeMillis();
                                Thread.sleep(startAfter * 1000);
                                for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
                                    if(QueueWindow.queueFiles.get(i).getURL().charAt(4) == 's' && !QueueWindow.queueFiles.get(i).isDownloaded()){
                                        DownloaderHttps downloaderHttps = new DownloaderHttps(QueueWindow.queueFiles.get(i));
                                        downloaderHttps.start();
                                    }else if(QueueWindow.queueFiles.get(i).getURL().charAt(4) != 's' && !QueueWindow.queueFiles.get(i).isDownloaded()){
                                        DownloaderHttp downloaderHttp = new DownloaderHttp(QueueWindow.queueFiles.get(i));
                                        downloaderHttp.start();
                                    }
                                }
                            }
                        } catch (InterruptedException e1) {
                            System.err.println("Thread was canceled");
                        }
                    }
                });
                scheduleFrame.dispose();
                thread.start();
            }
        });
        panel.add(button2);


        scheduleFrame.add(panel);

        setFrame(scheduleFrame);
    }

    /**
     * set frame
     * @param scheduleFrame frame
     */
    private void setFrame(JFrame scheduleFrame) {
        scheduleFrame.setTitle(MainWindow.getText("Schedules", "زمان بندی"));
        scheduleFrame.setSize(500,100);
        scheduleFrame.setResizable(false);
        scheduleFrame.setLocationRelativeTo(null);
        scheduleFrame.setVisible(true);
    }
}
