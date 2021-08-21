package main;

import javax.swing.*;
import java.awt.*;

public class HelpWindow {

    public static JFrame helpFrame = new JFrame();

    /**
     * constructor of the HelpWindow class
     */
    public HelpWindow(){

        labels();

        setFrame();
    }

    /**
     * method to set frame details
     */
    private void setFrame() {
        helpFrame.setTitle(MainWindow.getText("Help", "کمک"));
        helpFrame.setSize(500,500);
        helpFrame.setResizable(false);
        helpFrame.setLocationRelativeTo(null);
        helpFrame.setVisible(true);
    }

     /**
     * method to set up JLabels
     */
    private void labels() {
        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);

        JLabel label1 = new JLabel("Name: Kasra Mojallal      ID: 9631419        Date:4/13/2018-7/13/2018");
        label1.setBounds(10,20,450,20);
        JLabel label2 = new JLabel(MainWindow.getText("You should get instructions for using the application", "کسری مجلل همیشه با شماست"));
        label2.setBounds(10,90,450,20);
        JLabel label3 = new JLabel(MainWindow.getText("and the only one who can provide that instruction is Mr. Kasra Mojallal", ""));
        label3.setBounds(10,110,450,20);

        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        helpFrame.add(panel1);
    }
}
