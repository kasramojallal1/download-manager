package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SettingsWindow {

    public static JFrame settingsFrame = new JFrame();
    public static boolean isOpenedSettings = false;
    public static StringBuilder saveDirectory = new StringBuilder("C:\\Users\\Kasra\\Desktop");
    public static ArrayList<StringBuilder> badURLs = new ArrayList<>();
    public static int downloadLimit = 100;
    public static StringBuilder lookAndFeel = new StringBuilder("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    public static long timeLeft = 0;

    /**
     * constructor of the SettingsWindow class
     */
    public SettingsWindow(){
        settings();

        settingsFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                isOpenedSettings = true;
            }

            @Override
            public void windowClosing(WindowEvent e) {
                isOpenedSettings = false;
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

        setFrame();
    }

    /**
     * set frame
     */
    private void setFrame() {
        settingsFrame.setTitle(MainWindow.getText("Settings", "تنظیمات"));
        settingsFrame.setSize(500,500);
        settingsFrame.setResizable(false);
        settingsFrame.setLocationRelativeTo(null);
        settingsFrame.setVisible(true);
    }

    /**
     * method for setting up settings window functions
     */
    private void settings() {
        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.WHITE);
        settingsFrame.add(panel1);

        JLabel label1 = new JLabel(MainWindow.getText("Set download number limit: ", ":تعداد محدود دانلود ها"));
        label1.setBounds(50,30,180,30);
        JTextField textField1 = new JTextField();
        textField1.setToolTipText("Text Field");
        textField1.setBounds(230,30,50,30);
        JButton button1 = new JButton(MainWindow.getText("Set", "ثبت"));
        button1.setToolTipText("Set");
        button1.setBounds(300,30,75,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Integer.parseInt(textField1.getText());
                    downloadLimit = Integer.parseInt(textField1.getText());
                    textField1.setText("");
                }catch (NumberFormatException e1){
                    System.err.println("Please enter a number");
                }
            }
        });

        panel1.add(label1);
        panel1.add(textField1);
        panel1.add(button1);

        JLabel label2 = new JLabel(MainWindow.getText("Set save directory: ", ":ثبت محل ذخیره"));
        label2.setBounds(50,80,300,25);
        JTextField textField2 = new JTextField();
        textField2.setToolTipText("Text Field");
        textField2.setBounds(50,100,300,25);
        textField2.setText(String.valueOf(saveDirectory));
        textField2.setEditable(false);
        JButton button2 = new JButton(MainWindow.getText("Set", "ثبت"));
        button2.setToolTipText("Set");
        button2.setBounds(50,130,80,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(String.valueOf(saveDirectory)));
                chooser.showOpenDialog(null);
                StringBuilder newDirectory = new StringBuilder();
                newDirectory.append(chooser.getCurrentDirectory());
                chooser.setCurrentDirectory(new File(String.valueOf(newDirectory)));
                textField2.setText(String.valueOf(newDirectory));
                saveDirectory.delete(0, saveDirectory.length());
                saveDirectory.append(textField2.getText());
            }
        });

        panel1.add(label2);
        panel1.add(textField2);
        panel1.add(button2);

        JLabel label3 = new JLabel(MainWindow.getText("Set Look And Feel: ", ":تنظیم ظاهر"));
        label3.setBounds(50,180,300,25);
        JButton button301 = new JButton(MainWindow.getText("Classic", "کلاسیک"));
        button301.setToolTipText("Classic Look And Feel");
        button301.setBounds(50,200,100,25);
        button301.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                lookAndFeel.replace(0,lookAndFeel.length(), "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

                SwingUtilities.updateComponentTreeUI(settingsFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(HelpWindow.helpFrame.getContentPane());
            }
        });
        JButton button302 = new JButton(MainWindow.getText("Win 10", "ویندوز ۱۰"));
        button302.setToolTipText("Windows 10 Look And Feel");
        button302.setBounds(180,200,100,25);
        button302.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                lookAndFeel.replace(0,lookAndFeel.length(), "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

                SwingUtilities.updateComponentTreeUI(settingsFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(HelpWindow.helpFrame.getContentPane());

            }
        });
        JButton button303 = new JButton(MainWindow.getText("Nimbus", "نیمبوس"));
        button303.setToolTipText("Nimbus Look And Feel");
        button303.setBounds(310,200,100,25);
        button303.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                lookAndFeel.replace(0,lookAndFeel.length(), "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

                SwingUtilities.updateComponentTreeUI(settingsFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(MainWindow.mainFrame.getContentPane());
                SwingUtilities.updateComponentTreeUI(HelpWindow.helpFrame.getContentPane());

            }
        });

        panel1.add(label3);
        panel1.add(button301);
        panel1.add(button302);
        panel1.add(button303);

        JLabel label4 = new JLabel(MainWindow.getText("Set bad URLs:", ":ثبت آدرس های غیر مجاز"));
        label4.setBounds(50,260,200,20);

        JTextField textFieldBadURls = new JTextField();
        textFieldBadURls.setToolTipText("Text Field");
        textFieldBadURls.setBounds(50,280,400,25);
        textFieldBadURls.setText("");

        JButton button401 = new JButton(MainWindow.getText("Add", "افزودن"));
        button401.setToolTipText("Add");
        button401.setBounds(50,310,100,25);
        button401.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stringBuilderBadURL = new StringBuilder();
                stringBuilderBadURL.append(textFieldBadURls.getText());
                badURLs.add(stringBuilderBadURL);
                textFieldBadURls.setText("");

            }
        });

        JButton button402 = new JButton(MainWindow.getText("Show list", "نشان دادن"));
        button402.setToolTipText("Show list");
        button402.setBounds(200,310,100,25);
        button402.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BadURLsWindow();

            }
        });

        panel1.add(label4);
        panel1.add(textFieldBadURls);
        panel1.add(button401);
        panel1.add(button402);

        JLabel label5 = new JLabel(MainWindow.getText("Set Language: ", ":تایین زبان"));
        label5.setBounds(50,360,200,20);

        JButton button501 = new JButton(MainWindow.getText("English", "انگلیسی"));
        button501.setToolTipText("English");
        button501.setBounds(50,380,100,25);
        button501.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.language = 1;
            }
        });

        JButton button502 = new JButton(MainWindow.getText("Persian", "فارسی"));
        button502.setToolTipText("Persian");
        button502.setBounds(180,380,100,25);
        button502.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainWindow.language = 2;
            }
        });

        panel1.add(label5);
        panel1.add(button501);
        panel1.add(button502);


    }

    /**
     * method for making look and feels
     * @param string name of the look and feel
     */
    public static void setLookAndFeel(String string){
        try{
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
                try {
                    UIManager.setLookAndFeel(string);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (Exception ex){
            System.out.println("Exception");
        }
    }

}
