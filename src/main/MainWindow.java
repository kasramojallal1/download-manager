package main;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class MainWindow {
    public static JFrame mainFrame = new JFrame();

    public static int language = 1;

    public static int numOfFile = 0;
    public static int numOfMouse = 0;
    public static int numToLimit = 0;

    public static main.File fileSelected;
    public static JScrollPane scrollPaneFiles;

    public static StringBuilder search = new StringBuilder();

    public static JPanel panelDownload = new JPanel();
    public static SerializeInformation serializeInformation = new SerializeInformation();

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     * constructor of the MainWindow class
     */
    public MainWindow(){

        doAtFirst();

        SettingsWindow.setLookAndFeel(String.valueOf(SettingsWindow.lookAndFeel));

        JPanel panelMain = new JPanel(null);

        JPanel panelLeft = getPanelLeft();
        panelMain.add(panelLeft);

        JToolBar toolBar = getToolBar();
        panelMain.add(toolBar);

        search(panelMain);

        setPreviousDownloads();
        setPreviousDownloadsQueue();

        panelDownload.setLayout(new GridLayout(0,1));

        scrollPane();

        panelMain.add(scrollPaneFiles);

        menuBar();
        addTrayIcon();
        setMainFrame(panelMain);

        mainFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {serializeInformation.onCloseList();}
                catch (IOException e1) {e1.printStackTrace();}

                try {serializeInformation.onCloseLanguage();}
                catch (IOException e1) {e1.printStackTrace();}

                try {serializeInformation.onCloseQueue();}
                catch (IOException e1) {e1.printStackTrace();}

                try {serializeInformation.onCloseSettingsLimit();}
                catch (IOException e1) {e1.printStackTrace();}

                try {
                    serializeInformation.onCloseSettingsLook();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    serializeInformation.onCloseSettingsDirectory();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    serializeInformation.onCloseSettingsBadURLs();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    serializeInformation.onCloseRemovedFiles();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                try {
                    serializeInformation.onCloseSchedule();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                ScheduleQueue.timeEnded = System.currentTimeMillis();

                long timeDifference = ScheduleQueue.timeEnded - ScheduleQueue.timeStarted;
                SettingsWindow.timeLeft = ScheduleQueue.startAfter * 1000 - timeDifference;

                try {
                    serializeInformation.onCloseSchedule();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

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
            public void windowDeactivated(WindowEvent e) {}
        });

    }

    /**
     * method to set file size
     * @param file file
     * @throws IOException exception
     */
    public static void setFileSize(main.File file) throws IOException {
        if(file.getURL().charAt(4) == 's'){
            URL url = new URL(file.getURL());
            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
            httpsConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

            int responseCode = httpsConn.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK){
                float contentLength = httpsConn.getContentLength();
                file.setSize(contentLength / 1048576);
                file.setBytes((long) contentLength);
            }else{
                System.err.println("Response code " + httpsConn.getResponseCode());
            }
            httpsConn.disconnect();
        }else{
            URL url = new URL(file.getURL());
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

            int responseCode = httpConn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                float contentLength = httpConn.getContentLength();
                file.setSize(contentLength / 1048576);
                file.setBytes((long) contentLength);
            }else{
                System.err.println("Response code " + httpConn.getResponseCode());
            }
            httpConn.disconnect();
        }
    }

    /**
     * search method
     * @param panelMain panel
     */
    private void search(JPanel panelMain) {
        JTextField textFieldSearch = new JTextField();
        textFieldSearch.setBackground(Color.WHITE);
        textFieldSearch.setBounds(160,60,400,30);
        textFieldSearch.setToolTipText("Search");
        panelMain.add(textFieldSearch);

        JButton buttonSearch = new JButton(getText("Search", "جستجو"));
        buttonSearch.setBackground(Color.WHITE);
        buttonSearch.setBounds(580,60,100,30);
        buttonSearch.setToolTipText("Search");
        buttonSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.append(textFieldSearch.getText());
                new SearchWindow();
                textFieldSearch.setText("");
                search.delete(0,search.length());
            }
        });
        panelMain.add(buttonSearch);
    }

    /**
     * scroll pane
     */
    private void scrollPane() {
        scrollPaneFiles = new JScrollPane(panelDownload);
        scrollPaneFiles.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneFiles.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneFiles.setBounds(150, 100, 535, 490);
    }

    /**
     * tasks to do first
     */
    public void doAtFirst() {

        try {serializeInformation.onOpenList();}
        catch (IOException | ClassNotFoundException e) {
            System.out.println("");
        }

        try {serializeInformation.onOpenLanguage();}
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}

        try {serializeInformation.onOpenQueue();}
        catch (IOException | ClassNotFoundException e) {
            System.out.println("");
        }

        try {serializeInformation.onOpenSettingsLimit();}
        catch (IOException | ClassNotFoundException e) {e.printStackTrace();}

        try {
            serializeInformation.onOpenSettingsLook();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            serializeInformation.onOpenSettingsDirectory();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            serializeInformation.onOpenSettingsBadURLs();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("");
        }

        try {
            serializeInformation.onOpenSchedule();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            serializeInformation.onOpenSchedule();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if(SettingsWindow.timeLeft > 0){

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if(SettingsWindow.timeLeft != 0){
                            Thread.sleep(SettingsWindow.timeLeft);

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

                        SettingsWindow.timeLeft = 0;
                    } catch (InterruptedException e) {
                        System.out.println("");
                    }
                }
            });
            thread.start();
        }
    }

    /**
     * set up new files
     * @param panel panel
     */
    public static void setUpNewFiles(JPanel panel){
        NewDownload.files.get(numOfFile).setPanel(new JPanel());
        NewDownload.files.get(numOfFile).getPanel().setPreferredSize(new Dimension(510,100));
        NewDownload.files.get(numOfFile).getPanel().setLayout(null);

        try {
            setFileSize(NewDownload.files.get(numOfFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        NewDownload.files.get(numOfFile).setLabel(new JLabel(NewDownload.files.get(numOfFile).getURL()));
        NewDownload.files.get(numOfFile).getLabel().setBounds(5,10,350,15);
        NewDownload.files.get(numOfFile).getPanel().add(NewDownload.files.get(numOfFile).getLabel());

        NewDownload.files.get(numOfFile).setLabelSize(new JLabel("Size:    " + NewDownload.files.get(numOfFile).getSize() + "  MB"));
        NewDownload.files.get(numOfFile).getLabelSize().setBounds(380,10,150,15);
        NewDownload.files.get(numOfFile).getPanel().add(NewDownload.files.get(numOfFile).getLabelSize());

        NewDownload.files.get(numOfFile).setProgressBar(new JProgressBar());
        NewDownload.files.get(numOfFile).getProgressBar().setBounds(5,50,350,20);
        if(NewDownload.files.get(numOfFile).getIsDownloaded()){
            NewDownload.files.get(numOfFile).getProgressBar().setValue(100);
        }else{
            NewDownload.files.get(numOfFile).getProgressBar().setValue(0);
        }
        NewDownload.files.get(numOfFile).getProgressBar().setForeground(Color.GREEN);
        NewDownload.files.get(numOfFile).getProgressBar().setStringPainted(true);
        NewDownload.files.get(numOfFile).getProgressBar().setVisible(true);
        NewDownload.files.get(numOfFile).getPanel().add(NewDownload.files.get(numOfFile).getProgressBar());

        NewDownload.files.get(numOfFile).setLabelSpeed(new JLabel("Speed:    " + String.valueOf(NewDownload.files.get(numOfFile).getDownloadSpeed()) + "  MB/S"));
        NewDownload.files.get(numOfFile).getLabelSpeed().setBounds(380,50,150,15);
        NewDownload.files.get(numOfFile).getPanel().add(NewDownload.files.get(numOfFile).getLabelSpeed());

        panel.add(NewDownload.files.get(numOfFile).getPanel());
        SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());

        numOfFile++;
        updateMouseListener();
    }

    /**
     * set previous downloads
     */
    private void setPreviousDownloads() {
        for (int i = 0; i < NewDownload.files.size(); i++) {
            setUpNewFiles(panelDownload);
        }
    }

    /**
     * set previous downloads of the queue
     */
    private void setPreviousDownloadsQueue(){
        for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
            QueueWindow.setUpQueueFiles(QueueWindow.panelQueue);
        }
    }

    /**
     * add mouse listeners
     */
    public static void updateMouseListener(){
        for (int i = numOfMouse; i < NewDownload.files.size(); i++) {
            int finalI = i;
            NewDownload.files.get(i).getPanel().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == 3){
                        new InfoWindow(NewDownload.files.get(finalI));
                    }
                    if(e.getButton() == 1 && e.getClickCount() == 1){
                        fileSelected = NewDownload.files.get(finalI);
                    }
                    if(e.getButton() == 1 && e.getClickCount() == 2){
                        fileSelected = NewDownload.files.get(finalI);

                        if (Desktop.isDesktopSupported()) {
                            try {
                                File myFile = new File(String.valueOf(SettingsWindow.saveDirectory + "\\" + fileSelected.getName()));
                                Desktop.getDesktop().open(myFile);
                            } catch (IOException ex) {
                                System.err.println("File not found");
                            }
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        }
        numOfMouse = NewDownload.files.size() - numOfMouse;
    }

    /**
     * method for setting up panel on the left side of the frame
     * @return JPanel
     */
    private JPanel getPanelLeft() {
        JPanel panelLeft = new JPanel();
        panelLeft.setLayout(null);
        panelLeft.setBounds(0,0,150,600);
        panelLeft.setPreferredSize(new Dimension(150,600));
        panelLeft.setBackground(Color.WHITE);


        ImageIcon imageLeft = new ImageIcon("icons\\download128.png");
        JLabel labelLeft = new JLabel(imageLeft);
        labelLeft.setBounds(0,0,150,150);
        panelLeft.add(labelLeft);

        JButton buttonQueue = new JButton(getText("Queue", "صف"));
        buttonQueue.setBackground(Color.WHITE);
        buttonQueue.setBounds(25,160,100,30);
        buttonQueue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueueWindow();
            }
        });
        panelLeft.add(buttonQueue);

        return panelLeft;
    }

    /**
     * method for setting up toolbar
     * @return JToolbar
     */
    private JToolBar getToolBar() {
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(Color.WHITE);
        toolBar.setBounds(150,0,550,50);

        ImageIcon newDownloadIcon = new ImageIcon("icons\\download.png");
        ImageIcon pauseIcon = new ImageIcon("icons\\pause.png");
        ImageIcon resumeIcon = new ImageIcon("icons\\play.png");
        ImageIcon cancelIcon = new ImageIcon("icons\\close.png");
        ImageIcon removeIcon = new ImageIcon("icons\\rubbish-bin.png");
        ImageIcon scheduleIcon = new ImageIcon("icons\\appointment.png");
        ImageIcon settingsIcon = new ImageIcon("icons\\settings.png");

        JButton newDownloadButton = new JButton(newDownloadIcon);
        newDownloadButton.setBackground(Color.WHITE);
        newDownloadButton.setToolTipText("New Download");
        newDownloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!NewDownload.isOpenedNewDownload){
                    new NewDownload();
                }
            }
        });
        toolBar.add(newDownloadButton);

        JButton resumeButton = new JButton(resumeIcon);
        resumeButton.setBackground(Color.WHITE);
        resumeButton.setToolTipText("Resume");
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(numToLimit < SettingsWindow.downloadLimit){
                    try{
                        if(fileSelected.getURL().charAt(4) == 's'){
                            DownloaderHttps downloaderHttps = new DownloaderHttps(fileSelected);
                            downloaderHttps.start();
                        }else{
                            DownloaderHttp downloaderHttp = new DownloaderHttp(fileSelected);
                            downloaderHttp.start();
                        }
                    }catch (NullPointerException ex){
                        System.out.println(ANSI_PURPLE + "Please select a file first" + ANSI_RESET);
                    }
                }else{
                    System.out.println("You can have " + SettingsWindow.downloadLimit + " Downloads at a time.");
                }
            }
        });
        toolBar.add(resumeButton);

        JButton pauseButton = new JButton(pauseIcon);
        pauseButton.setBackground(Color.WHITE);
        pauseButton.setToolTipText("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        toolBar.add(pauseButton);

        JButton cancelButton = new JButton(cancelIcon);
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setToolTipText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileSelected.getProgressBar().setValue(0);
                fileSelected.isDownloadedFalse();
                SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());
            }
        });
        toolBar.add(cancelButton);

        JButton removeButton = new JButton(removeIcon);
        removeButton.setBackground(Color.WHITE);
        removeButton.setToolTipText("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    NewDownload.files.remove(fileSelected);
                }catch(NullPointerException ex){
                    System.out.println(ANSI_PURPLE + "Please select a file first" + ANSI_RESET);
                }
            }
        });
        toolBar.add(removeButton);

        JButton scheduleButton = new JButton(scheduleIcon);
        scheduleButton.setBackground(Color.WHITE);
        scheduleButton.setToolTipText("Set Schedule");
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                new ScheduleWindow(fileSelected);
            }
        });
        toolBar.add(scheduleButton);

        JButton settingsButton = new JButton(settingsIcon);
        settingsButton.setBackground(Color.WHITE);
        settingsButton.setToolTipText("Settings");
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SettingsWindow.isOpenedSettings){
                    new SettingsWindow();
                }
            }
        });
        toolBar.add(settingsButton);
        return toolBar;
    }

    /**
     * method to set language
     * @param string1 english
     * @param string2 persian
     * @return string
     */
    public static String getText(String string1, String string2){
        if(language == 1){
            return string1;
        }
        else return string2;
    }

    /**
     * method for setting up menu bar
     */
    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);

        JMenu downloadMenu = new JMenu(getText("Download", "دانلود"));

        JMenuItem newDownloadMenuItem = new JMenuItem(getText("New Download", "دانلود جدید"));
        newDownloadMenuItem.setBackground(Color.WHITE);
        newDownloadMenuItem.setToolTipText("New Download");
        newDownloadMenuItem.setMnemonic('n');
        newDownloadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        newDownloadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!NewDownload.isOpenedNewDownload){
                    new NewDownload();
                }
            }
        });
        downloadMenu.add(newDownloadMenuItem);

        JMenuItem resumeMenuItem = new JMenuItem(getText("Resume", "ادامه"));
        resumeMenuItem.setBackground(Color.WHITE);
        resumeMenuItem.setToolTipText("Resume");
        resumeMenuItem.setMnemonic('r');
        resumeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(numToLimit < SettingsWindow.downloadLimit){
                    try{
                        if(fileSelected.getURL().charAt(4) == 's'){
                            DownloaderHttps downloaderHttps = new DownloaderHttps(fileSelected);
                            downloaderHttps.start();
                        }else{
                            DownloaderHttp downloaderHttp = new DownloaderHttp(fileSelected);
                            downloaderHttp.start();
                        }
                    }catch (NullPointerException ex){
                        System.out.println(ANSI_PURPLE + "Please select a file first" + ANSI_RESET);
                    }
                }else{
                    System.out.println("You can have " + SettingsWindow.downloadLimit + " Downloads at a time.");
                }
            }
        });
        downloadMenu.add(resumeMenuItem);

        JMenuItem pauseMenuItem = new JMenuItem(getText("Pause", "توقف"));
        pauseMenuItem.setBackground(Color.WHITE);
        pauseMenuItem.setToolTipText("Pause");
        pauseMenuItem.setMnemonic('p');
        pauseMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        downloadMenu.add(pauseMenuItem);

        JMenuItem cancelMenuItem = new JMenuItem(getText("Cancel", "انصراف"));
        cancelMenuItem.setBackground(Color.WHITE);
        cancelMenuItem.setToolTipText("Cancel");
        cancelMenuItem.setMnemonic('c');
        cancelMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        cancelMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileSelected.getProgressBar().setValue(0);
                fileSelected.isDownloadedFalse();
                SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());
            }
        });
        downloadMenu.add(cancelMenuItem);

        JMenuItem removeMenuItem = new JMenuItem(getText("Remove", "حذف"));
        removeMenuItem.setBackground(Color.WHITE);
        removeMenuItem.setToolTipText("Remove");
        removeMenuItem.setMnemonic('m');
        removeMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        removeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    NewDownload.files.remove(fileSelected);
                }catch(NullPointerException ex){
                    System.out.println(ANSI_PURPLE + "Please select a file first" + ANSI_RESET);
                }
            }
        });

        downloadMenu.add(removeMenuItem);

        JMenuItem settingsMenuItem = new JMenuItem(getText("Settings", "تنظیمات"));
        settingsMenuItem.setBackground(Color.WHITE);
        settingsMenuItem.setToolTipText("Settings");
        settingsMenuItem.setMnemonic('s');
        settingsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        settingsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!SettingsWindow.isOpenedSettings){
                    new SettingsWindow();
                }
            }
        });
        downloadMenu.add(settingsMenuItem);

        JMenuItem exportMenuItem = new JMenuItem(getText("Export", "صدور"));
        exportMenuItem.setBackground(Color.WHITE);
        exportMenuItem.setToolTipText("Export");
        exportMenuItem.setMnemonic('x');
        exportMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        exportMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ZipFiles();
            }
        });
        downloadMenu.add(exportMenuItem);

        JMenuItem exitMenuItem = new JMenuItem(getText("Exit", "خروج"));
        exitMenuItem.setBackground(Color.WHITE);
        exitMenuItem.setToolTipText("Exit");
        exitMenuItem.setMnemonic('e');
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        downloadMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu(getText("Help", "کمک"));

        JMenuItem aboutMenuItem = new JMenuItem(getText("About", "درباره"));
        aboutMenuItem.setBackground(Color.WHITE);
        aboutMenuItem.setToolTipText("About");
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        aboutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpWindow();
            }
        });
        helpMenu.add(aboutMenuItem);

        menuBar.add(downloadMenu);
        menuBar.add(helpMenu);
        mainFrame.setJMenuBar(menuBar);
    }

    /**
     * add the tray icon
     */
    private void addTrayIcon() {
        try {SystemTray systemTray = SystemTray.getSystemTray();
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image image = toolkit.getImage("icons\\inbox16.png");
            PopupMenu popupMenu = new PopupMenu();
            TrayIcon icon = new TrayIcon(image, "Java Download Manager", popupMenu);
            MenuItem open = new MenuItem("Open");
            open.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mainFrame.setVisible(true);
                }
            });
            popupMenu.add(open);
            MenuItem close = new MenuItem("Close");
            close.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SystemTray.getSystemTray().remove(icon);
                    System.exit(0);
                }
            });
            popupMenu.add(close);
            icon.setImageAutoSize(true);
            systemTray.add(icon);}
        catch (AWTException e) {e.printStackTrace();}
    }

    /**
     * method to set frame details
     */
    private void setMainFrame(JPanel contentPane) {
        mainFrame.setTitle(getText("Java Download Manager", "مدیریت دانلود جاوا"));
        mainFrame.setContentPane(contentPane);
        mainFrame.setSize(700,650);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
