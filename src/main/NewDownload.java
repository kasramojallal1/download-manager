package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NewDownload {

    public static JFrame newDownloadFrame = new JFrame();
    public static boolean isOpenedNewDownload = false;

    public static ArrayList<File> files = new ArrayList<>();
    public static ArrayList<File> removedFiles = new ArrayList<>();

    public static int numberOfFile = 0;

    /**
     * constructor of the NewDownloadWindow class
     */
    public NewDownload() {

        newDownloadFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                isOpenedNewDownload = true;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                isOpenedNewDownload = false;
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        panel();

        setFrame();
    }

    /**
     * method to set up NewDownloadWindow functions
     */
    private void panel() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.WHITE);

        JLabel label1 = new JLabel(MainWindow.getText("Enter URL: ", ":آدرس را وارد کنی:"));
        label1.setBounds(20, 15, 100, 20);
        JTextField textField1 = new JTextField();
        textField1.setToolTipText("URL");
        textField1.setBounds(20, 40, 300, 25);
        JButton button11 = new JButton(MainWindow.getText("Add", "افزودن"));
        button11.setToolTipText("Add");
        button11.setBounds(20, 80, 100, 20);
        button11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int flag = 1;

                for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
                    if(textField1.getText().toLowerCase().contains(SettingsWindow.badURLs.get(i))){
                        flag = 0;
                        System.err.println("Bad URL, Please enter an other URL");
                    }
                }

                if(!(textField1.getText().contains("http://")) && !(textField1.getText().contains("https://"))){
                    flag = 0;
                }

                if(flag == 1){
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());

                    StringBuilder fullURL = new StringBuilder(textField1.getText());

                    if(fullURL.charAt(fullURL.length() - 1) == '/'){
                        fullURL.delete(fullURL.lastIndexOf("/"), fullURL.length());
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    else if(fullURL.charAt(fullURL.length() - 1) != '/'){
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    isOpenedNewDownload = false;
                    newDownloadFrame.dispose();
                    textField1.setText("");
                    MainWindow.setUpNewFiles(MainWindow.panelDownload);
                    numberOfFile++;
                }
            }
        });

        JButton button101 = new JButton(MainWindow.getText("Add to queue", "به صف اضافه کن"));
        button101.setToolTipText("Add to queue");
        button101.setBounds(20,110,100,20);
        button101.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int flag = 1;

                for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
                    if(textField1.getText().toLowerCase().contains(SettingsWindow.badURLs.get(i))){
                        flag = 0;
                        System.err.println("Bad URL, Please enter an other URL");
                    }
                }

                if(!(textField1.getText().contains("http://")) && !(textField1.getText().contains("https://"))){
                    flag = 0;
                }

                if(flag == 1){
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());
                    StringBuilder fullURL = new StringBuilder(textField1.getText());

                    if(fullURL.charAt(fullURL.length() - 1) == '/'){
                        fullURL.delete(fullURL.lastIndexOf("/"), fullURL.length());
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        QueueWindow.queueFiles.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    else if(fullURL.charAt(fullURL.length() - 1) != '/'){
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        QueueWindow.queueFiles.add(new File(fileName,textField1.getText(), timeStamp));
                    }

                    isOpenedNewDownload = false;
                    newDownloadFrame.dispose();
                    textField1.setText("");
                    QueueWindow.setUpQueueFiles(QueueWindow.panelQueue);
                    numberOfFile++;
                }

            }
        });
        panel1.add(button101);

        JButton button12 = new JButton(MainWindow.getText("Start", "شروع"));
        button12.setToolTipText("Start");
        button12.setBounds(150,80,100,20);
        button12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int flag = 1;

                for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
                    if(textField1.getText().toLowerCase().contains(SettingsWindow.badURLs.get(i))){
                        flag = 0;
                        System.err.println("Bad URL, Please enter an other URL");
                    }
                }

                if(!(textField1.getText().contains("http://")) && !(textField1.getText().contains("https://"))){
                    flag = 0;
                }

                if(flag == 1){
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());

                    StringBuilder fullURL = new StringBuilder(textField1.getText());

                    if(fullURL.charAt(fullURL.length() - 1) == '/'){
                        fullURL.delete(fullURL.lastIndexOf("/"), fullURL.length());
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    else if(fullURL.charAt(fullURL.length() - 1) != '/'){
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    isOpenedNewDownload = false;
                    newDownloadFrame.dispose();
                    textField1.setText("");
                    MainWindow.setUpNewFiles(MainWindow.panelDownload);
                    if(files.get(numberOfFile).getURL().charAt(4) == 's'){
                        DownloaderHttps downloaderHttps = new DownloaderHttps(files.get(numberOfFile));
                        downloaderHttps.start();
                    }else{
                        DownloaderHttp downloaderHttp = new DownloaderHttp(files.get(numberOfFile));
                        downloaderHttp.start();
                    }
                    numberOfFile++;
                }
            }
        });

        JButton button13 = new JButton(MainWindow.getText("Schedule", "زمان بندی"));
        button13.setToolTipText("Schedule");
        button13.setBounds(280,80,100,20);
        button13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int flag = 1;

                for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
                    if(textField1.getText().toLowerCase().contains(SettingsWindow.badURLs.get(i))){
                        flag = 0;
                        System.err.println("Bad URL, Please enter an other URL");
                    }
                }

                if(!(textField1.getText().contains("http://")) && !(textField1.getText().contains("https://"))){
                    flag = 0;
                }

                if(flag == 1){
                    String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());

                    StringBuilder fullURL = new StringBuilder(textField1.getText());

                    if(fullURL.charAt(fullURL.length() - 1) == '/'){
                        fullURL.delete(fullURL.lastIndexOf("/"), fullURL.length());
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                    else if(fullURL.charAt(fullURL.length() - 1) != '/'){
                        String fileName = fullURL.substring(fullURL.lastIndexOf("/") + 1 , fullURL.length());
                        files.add(new File(fileName,textField1.getText(), timeStamp));
                    }
                }
                isOpenedNewDownload = false;
                newDownloadFrame.dispose();
                textField1.setText("");
                MainWindow.setUpNewFiles(MainWindow.panelDownload);

                new ScheduleWindow(files.get(numberOfFile));
                newDownloadFrame.dispose();
            }
        });

        JLabel labelDirectory = new JLabel(MainWindow.getText("Change directory:", ":تغییر مسیر"));
        labelDirectory.setBounds(20,150,200,20);

        JTextField textFieldDirectory = new JTextField(String.valueOf(SettingsWindow.saveDirectory));
        textFieldDirectory.setToolTipText("Text Field");
        textFieldDirectory.setEditable(false);
        textFieldDirectory.setBounds(20,180,200,20);

        JButton button21 = new JButton(MainWindow.getText("Change", "تغییر"));
        button21.setToolTipText("Change");
        button21.setBounds(20,210,100,20);
        button21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser2 = new JFileChooser();
                chooser2.setCurrentDirectory(new java.io.File("C:\\Users\\Kasra\\Desktop"));
                chooser2.showOpenDialog(null);
                StringBuilder newDirectory = new StringBuilder();
                newDirectory.append(chooser2.getCurrentDirectory());
                chooser2.setCurrentDirectory(new java.io.File(String.valueOf(newDirectory)));
                textFieldDirectory.setText(String.valueOf(newDirectory));
                SettingsWindow.saveDirectory.delete(0, SettingsWindow.saveDirectory.length());
                SettingsWindow.saveDirectory.append(textFieldDirectory.getText());
            }
        });


        panel1.add(label1);
        panel1.add(textField1);
        panel1.add(button11);
        panel1.add(button12);
        panel1.add(button13);
        panel1.add(labelDirectory);
        panel1.add(textFieldDirectory);
        panel1.add(button21);
        newDownloadFrame.add(panel1);
    }

    /**
     * getter for array of files
     * @return array of files
     */
    public ArrayList<File> getFiles() {
        return files;
    }

    /**
     * method to set frame details
     */
    private void setFrame() {
        newDownloadFrame.setTitle(MainWindow.getText("New Download", "دانلود جدید"));
        newDownloadFrame.setSize(500, 300);
        newDownloadFrame.setResizable(false);
        newDownloadFrame.setLocationRelativeTo(null);
        newDownloadFrame.setVisible(true);
    }

}