package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BadURLsWindow {

    public static JFrame frameBadURLs = new JFrame();
    public static ArrayList<JPanel> panelsBadURLs = new ArrayList<>();
    public static ArrayList<JTextField> textFieldsBadURLs = new ArrayList<>();
    public static ArrayList<JButton> buttonsBadURLsDelete = new ArrayList<>();
    public static ArrayList<JButton> buttonBadURLsChange = new ArrayList<>();

    /**
     * constructor for the BadURLsWindow class
     */
    public BadURLsWindow(){

        setNewBadURLs();

        setFrame();
    }

    /**
     * method to set frame
     */
    private void setFrame() {
        frameBadURLs.setTitle("Bad URLs");
        frameBadURLs.setLayout(new GridLayout(8,1));
        frameBadURLs.setLocationRelativeTo(null);
        frameBadURLs.setSize(400,500);
        frameBadURLs.setVisible(true);
    }

    /**
     * method to add the bad URLs
     */
    private void setNewBadURLs(){
        for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
            panelsBadURLs.add(new JPanel());

            textFieldsBadURLs.add(new JTextField());
            textFieldsBadURLs.get(i).setText(String.valueOf(SettingsWindow.badURLs.get(i)));
            textFieldsBadURLs.get(i).setEditable(false);
            textFieldsBadURLs.get(i).setPreferredSize(new Dimension(200,20));
            panelsBadURLs.get(i).add(textFieldsBadURLs.get(i));

            buttonsBadURLsDelete.add(new JButton(MainWindow.getText("Delete", "حذف")));
            int final1I = i;
            buttonsBadURLsDelete.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });
            panelsBadURLs.get(i).add(buttonsBadURLsDelete.get(i));

            buttonBadURLsChange.add(new JButton(MainWindow.getText("Change", "تغییر")));
            int final2I = i;
            buttonBadURLsChange.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frameChangeBadURL = new JFrame();
                    JPanel panel = new JPanel();
                    panel.setLayout(null);

                    JTextField textField = new JTextField();
                    textField.setBounds(10,10,300,30);
                    textField.setText("");
                    panel.add(textField);

                    JButton button = new JButton(MainWindow.getText("Set", "ثبت"));
                    button.setBounds(320,10,100,30);
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            textFieldsBadURLs.get(final2I).setText(textField.getText());
                            SettingsWindow.badURLs.get(final2I).replace(0, SettingsWindow.badURLs.get(final2I).length(), textField.getText());
                            frameChangeBadURL.dispose();
                            SwingUtilities.updateComponentTreeUI(frameBadURLs.getContentPane());
                        }
                    });
                    panel.add(button);

                    frameChangeBadURL.add(panel);

                    frameChangeBadURL.setLocationRelativeTo(null);
                    frameChangeBadURL.setLayout(new GridLayout(2,1));
                    frameChangeBadURL.setSize(450,120);
                    frameChangeBadURL.setVisible(true);
                }
            });
            panelsBadURLs.get(i).add(buttonBadURLsChange.get(i));


            frameBadURLs.add(panelsBadURLs.get(i));
        }
    }

}
