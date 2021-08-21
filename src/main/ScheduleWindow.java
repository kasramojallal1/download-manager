package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScheduleWindow {

    public static StringBuilder timeToStart = new StringBuilder();
    public static int startAfter;

    private File file;

    /**
     * constructor of the ScheduleWindow class
     */
    public ScheduleWindow(File file){

        this.file = file;

        JFrame scheduleFrame = new JFrame();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());

        JPanel panel = new JPanel();
        panel.setLayout(null);

        part1(scheduleFrame, timeStamp, panel);
        part2(scheduleFrame, panel);

        scheduleFrame.add(panel);

        setFrame(scheduleFrame);

    }

    /**
     * method to set frame details
     * @param scheduleFrame frame
     */
    private void setFrame(JFrame scheduleFrame) {
        scheduleFrame.setTitle(MainWindow.getText("Schedules", "زمان بندی"));
        scheduleFrame.setSize(500,200);
        scheduleFrame.setResizable(false);
        scheduleFrame.setLocationRelativeTo(null);
        scheduleFrame.setVisible(true);
    }

    /**
     * method for setting up first part of the panel
     * @param scheduleFrame frame
     * @param timeStamp Systems time
     * @param panel JPanel
     */
    private void part1(JFrame scheduleFrame, String timeStamp, JPanel panel) {
        JLabel label1 = new JLabel(MainWindow.getText("When to start: ", ":زمان آغاز"));
        label1.setBounds(20,30,100,20);
        panel.add(label1);

        JTextField textField1 = new JTextField(timeStamp);
        textField1.setToolTipText("Text Field");
        textField1.setBounds(130,30,200,20);
        panel.add(textField1);

        JButton button1 = new JButton(MainWindow.getText("Set", "ثبت"));
        button1.setToolTipText("Set");
        button1.setBounds(360,30,100,20);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeToStart.append(textField1.getText());
                scheduleFrame.dispose();

                String beginning = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());

                int begin = 0;
                begin += beginning.charAt(18) - 48;
                begin += (beginning.charAt(17) - 48) * 10;
                begin += (beginning.charAt(15) - 48) * 60;
                begin += (beginning.charAt(14) - 48) * 10 * 60;

                int end = 0;
                end += timeToStart.charAt(18) - 48;
                end += (timeToStart.charAt(17) - 48) * 10;
                end += (timeToStart.charAt(15) - 48) * 60;
                end += (timeToStart.charAt(14) - 48) * 10 * 60;

                int finalEnd = end;
                int finalBegin = begin;
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep((finalEnd - finalBegin) * 1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        if(file.getURL().charAt(4) == 's'){
                            DownloaderHttps downloaderHttps = new DownloaderHttps(file);
                            downloaderHttps.start();
                        }else{
                            DownloaderHttp downloaderHttp = new DownloaderHttp(file);
                            downloaderHttp.start();
                        }
                    }
                });
                thread1.start();
            }
        });
        panel.add(button1);
    }

    /**
     * method for setting up second part of the panel
     * @param scheduleFrame frame
     * @param panel JPanel
     */
    private void part2(JFrame scheduleFrame, JPanel panel) {
        JLabel label2 = new JLabel(MainWindow.getText("Start after: ", ":شروع کن بعد از"));
        label2.setBounds(20,80,100,20);
        panel.add(label2);

        JTextField textField2 = new JTextField(MainWindow.getText("Seconds", "ثانیه"));
        textField2.setToolTipText("Text Field");
        textField2.setBounds(130,80,200,20);
        panel.add(textField2);

        JButton button2 = new JButton(MainWindow.getText("Set", "ثبت"));
        button2.setToolTipText("Set");
        button2.setBounds(360,80,100,20);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Integer.parseInt(textField2.getText());
                    startAfter = Integer.parseInt(textField2.getText());
                    textField2.setText("");
                    scheduleFrame.dispose();
                }catch (NumberFormatException e1){
                    System.err.println("Please enter a number");
                }

                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(startAfter * 1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        try{
                            if(file.getURL().charAt(4) == 's'){
                                DownloaderHttps downloaderHttps = new DownloaderHttps(file);
                                downloaderHttps.start();
                            }else{
                                DownloaderHttp downloaderHttp = new DownloaderHttp(file);
                                downloaderHttp.start();
                            }
                        }catch (Exception ex){
                            System.err.println("Please select a file");
                        }
                    }
                });
                thread1.start();
            }
        });
        panel.add(button2);
    }

}
