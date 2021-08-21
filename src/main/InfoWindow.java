package main;

import javax.swing.*;
import java.awt.*;

public class InfoWindow {

    /**
     * constructor of the InfoWindow class
     * @param file file
     */
    public InfoWindow(File file){

        JFrame infoFrame = new JFrame();

        panels(file, infoFrame);

        setFrame(infoFrame);
    }

    /**
     * method to set up InfoWindow functions
     * @param file file
     * @param infoFrame frame
     */
    private void panels(File file, JFrame infoFrame) {
        JPanel panel = new JPanel(new GridLayout(5,1));
        panel.setBackground(Color.WHITE);
        JLabel labelName = new JLabel(MainWindow.getText("Name:    " + file.getName(), "نام:     " + file.getName()));
        JLabel labelURL = new JLabel("URL:     " + file.getURL());
        JLabel labelTimeAdded = new JLabel(MainWindow.getText("Time Added:     " + file.getTimeAdded(), "زمان افزودن:      " + file.getTimeAdded()));
        JLabel labelDirectory = new JLabel(MainWindow.getText("Directory:     " + SettingsWindow.saveDirectory, "مسیر ذخیره:      " + SettingsWindow.saveDirectory));
        JLabel labelSize = new JLabel(MainWindow.getText("Size:    " + file.getSize() + " MB", "حجم:      " + file.getSize() + " KB"));
        panel.add(labelName);
        panel.add(labelURL);
        panel.add(labelTimeAdded);
        panel.add(labelDirectory);
        panel.add(labelSize);
        infoFrame.add(panel);
    }

    /**
     * method to set up frames details
     * @param infoFrame frame
     */
    private void setFrame(JFrame infoFrame) {
        infoFrame.setTitle(MainWindow.getText("File Information", "اطلاعات فایل"));
        infoFrame.setSize(500,200);
        infoFrame.setResizable(false);
        infoFrame.setLocationRelativeTo(null);
        infoFrame.setVisible(true);
    }
}
