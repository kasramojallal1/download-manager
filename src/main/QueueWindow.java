package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class QueueWindow {
    public static JFrame frameQueue = new JFrame();

    public static ArrayList<File> queueFiles = new ArrayList<>();

    public static int numOfQueueFile = 0;
    public static int numOfMouse = 0;

    public static JPanel panelQueue = new JPanel();
    public static JScrollPane scrollPaneQueue;

    /**
     * constructor of the QueueWindow class
     */
    public QueueWindow(){

        JPanel panelMain = new JPanel(null);

        panelQueue.setLayout(new GridLayout(0,1));

        scrollPaneQueue = new JScrollPane(panelQueue);
        scrollPaneQueue.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneQueue.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneQueue.setBounds(0, 0, 535, 490);

        panelMain.add(scrollPaneQueue);

        setFrame(panelMain);
        menuBar();
    }

    /**
     * set up new downloads
     * @param panel panel
     */
    public static void setUpQueueFiles(JPanel panel){

        queueFiles.get(numOfQueueFile).setPanel(new JPanel());
        queueFiles.get(numOfQueueFile).getPanel().setPreferredSize(new Dimension(510,100));
        queueFiles.get(numOfQueueFile).getPanel().setLayout(null);

        try {
            MainWindow.setFileSize(queueFiles.get(numOfQueueFile));
        } catch (IOException e) {
            e.printStackTrace();
        }

        queueFiles.get(numOfQueueFile).setLabel(new JLabel(QueueWindow.queueFiles.get(numOfQueueFile).getURL()));
        queueFiles.get(numOfQueueFile).getLabel().setBounds(5,10,350,15);
        queueFiles.get(numOfQueueFile).getPanel().add(QueueWindow.queueFiles.get(numOfQueueFile).getLabel());

        queueFiles.get(numOfQueueFile).setLabelSize(new JLabel("Size:    " + queueFiles.get(numOfQueueFile).getSize() + "  MB"));
        queueFiles.get(numOfQueueFile).getLabelSize().setBounds(380,10,150,15);
        queueFiles.get(numOfQueueFile).getPanel().add(queueFiles.get(numOfQueueFile).getLabelSize());

        queueFiles.get(numOfQueueFile).setProgressBar(new JProgressBar());
        queueFiles.get(numOfQueueFile).getProgressBar().setBounds(5,50,350,20);
        if(queueFiles.get(numOfQueueFile).getIsDownloaded()){
            queueFiles.get(numOfQueueFile).getProgressBar().setValue(100);
        }else{
            queueFiles.get(numOfQueueFile).getProgressBar().setValue(0);
        }
        queueFiles.get(numOfQueueFile).getProgressBar().setForeground(Color.GREEN);
        queueFiles.get(numOfQueueFile).getProgressBar().setStringPainted(true);
        queueFiles.get(numOfQueueFile).getProgressBar().setVisible(true);
        queueFiles.get(numOfQueueFile).getPanel().add(QueueWindow.queueFiles.get(numOfQueueFile).getProgressBar());

        queueFiles.get(numOfQueueFile).setLabelSpeed(new JLabel("Speed:    " + String.valueOf(queueFiles.get(numOfQueueFile).getDownloadSpeed()) + "  MB/S"));
        queueFiles.get(numOfQueueFile).getLabelSpeed().setBounds(380,50,150,15);
        queueFiles.get(numOfQueueFile).getPanel().add(queueFiles.get(numOfQueueFile).getLabelSpeed());

        panel.add(QueueWindow.queueFiles.get(numOfQueueFile).getPanel());
        SwingUtilities.updateComponentTreeUI(QueueWindow.frameQueue.getContentPane());

        numOfQueueFile++;
        updateMouseListener();
    }

    /**
     * set frame
     * @param contentPane panel
     */
    private void setFrame(JPanel contentPane) {
        frameQueue.setTitle(MainWindow.getText("Queue","صف"));
        frameQueue.setContentPane(contentPane);
        frameQueue.setLocationRelativeTo(null);
        frameQueue.setResizable(false);
        frameQueue.setSize(540,540);
        frameQueue.setVisible(true);
    }

    /**
     * add mouse listeners
     */
    public static void updateMouseListener(){
        for (int i = numOfMouse; i < queueFiles.size(); i++) {
            int finalI = i;
            queueFiles.get(i).getPanel().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(e.getButton() == 3){
                        new InfoWindow(queueFiles.get(finalI));
                    }
                    if(e.getButton() == 1 && e.getClickCount() == 1){
                        MainWindow.fileSelected = queueFiles.get(finalI);
                    }
                    if(e.getButton() == 1 && e.getClickCount() == 2){
                        MainWindow.fileSelected = queueFiles.get(finalI);

                        if (Desktop.isDesktopSupported()) {
                            try {
                                java.io.File myFile = new java.io.File(String.valueOf(SettingsWindow.saveDirectory + "\\" + MainWindow.fileSelected.getName()));
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
        numOfMouse = queueFiles.size() - numOfMouse;
    }

    /**
     * set up menu bar
     */
    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu(MainWindow.getText("File", "فایل"));

        JMenuItem startMenuItem = new JMenuItem(MainWindow.getText("Start", "آغاز"));
        startMenuItem.setToolTipText("Start");
        startMenuItem.setMnemonic('s');
        startMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        startMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < queueFiles.size(); i++) {
                    if (queueFiles.get(i).getURL().charAt(4) == 's' && !queueFiles.get(i).isDownloaded()) {
                        DownloaderHttps downloaderHttps = new DownloaderHttps(queueFiles.get(i));
                        downloaderHttps.start();
                    } else if(queueFiles.get(i).getURL().charAt(4) != 's' && !queueFiles.get(i).isDownloaded()){
                        DownloaderHttp downloaderHttp = new DownloaderHttp(queueFiles.get(i));
                        downloaderHttp.start();
                    }
                }
            }
        });
        fileMenu.add(startMenuItem);

        JMenuItem stopMenuItem = new JMenuItem(MainWindow.getText("Stop", "توقف"));
        stopMenuItem.setToolTipText("Stop");
        stopMenuItem.setMnemonic('t');
        stopMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        stopMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        fileMenu.add(stopMenuItem);

        JMenuItem scheduleMenuItem = new JMenuItem(MainWindow.getText("Schedule", "زمانبندی"));
        scheduleMenuItem.setToolTipText("Schedule");
        scheduleMenuItem.setMnemonic('c');
        scheduleMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        scheduleMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ScheduleQueue();
            }
        });
        fileMenu.add(scheduleMenuItem);

        menuBar.add(fileMenu);
        frameQueue.setJMenuBar(menuBar);
    }
}
