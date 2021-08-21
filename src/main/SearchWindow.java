package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SearchWindow {

    /**
     * constructor of the SearchWindow class
     */
    public SearchWindow(){

        JPanel panelMain = new JPanel(null);
        panelMain.setBackground(Color.GREEN);

        JPanel panelSearch = new JPanel();
        panelSearch.setLayout(new GridLayout(0,1));

        JScrollPane scrollPaneSearch = new JScrollPane(panelSearch);
        scrollPaneSearch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneSearch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPaneSearch.setBounds(0, 0, 535, 490);

        panelMain.add(scrollPaneSearch);

        addFiles(panelSearch);

        JFrame frameSearch = new JFrame();
        frameSearch.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                for (int i = 0; i < NewDownload.files.size(); i++) {
                    if(NewDownload.files.get(i).getURL().contains(MainWindow.search)){
                        MainWindow.panelDownload.add(NewDownload.files.get(i).getPanel());
                    }
                }
                for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
                    if(QueueWindow.queueFiles.get(i).getURL().contains(MainWindow.search)){
                        QueueWindow.panelQueue.add(QueueWindow.queueFiles.get(i).getPanel());
                    }
                }

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

        setFrame(frameSearch, panelMain);
    }

    /**
     * add files
     * @param panelSearch panel
     */
    private void addFiles(JPanel panelSearch) {
        for (int i = 0; i < NewDownload.files.size(); i++) {
            if(NewDownload.files.get(i).getURL().contains(MainWindow.search)){
                panelSearch.add(NewDownload.files.get(i).getPanel());
            }
        }

        for (int i = 0; i < NewDownload.files.size(); i++) {
            if(NewDownload.files.get(i).getName().contains(MainWindow.search)){
                panelSearch.add(NewDownload.files.get(i).getPanel());
            }
        }

        for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
            if(QueueWindow.queueFiles.get(i).getURL().contains(MainWindow.search)){
                panelSearch.add(QueueWindow.queueFiles.get(i).getPanel());
            }
        }

        for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
            if(QueueWindow.queueFiles.get(i).getName().contains(MainWindow.search)){
                panelSearch.add(QueueWindow.queueFiles.get(i).getPanel());
            }
        }

    }

    /**
     * set frame
     * @param frameSearch frame
     * @param contentPane frame
     */
    private void setFrame(JFrame frameSearch, JPanel contentPane) {
        frameSearch.setTitle(MainWindow.getText("Search", "جستجو"));
        frameSearch.setSize(541,518);
        frameSearch.setLocationRelativeTo(null);
        frameSearch.setContentPane(contentPane);
        frameSearch.setResizable(false);
        frameSearch.setVisible(true);
    }
}
