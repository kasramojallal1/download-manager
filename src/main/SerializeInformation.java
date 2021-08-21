package main;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SerializeInformation {

    /**
     * this class serializes information
     * @throws IOException exception
     */

    public void onCloseLanguage() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("language.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        out.writeObject(MainWindow.language);

        out.close();
        fileOut.close();
    }

    public void onOpenLanguage() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("language.jdm");

        if(f.exists() && !f.isDirectory()) {
            java.io.FileInputStream fileIn = new java.io.FileInputStream("language.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            MainWindow.language = (int) in.readObject();
        }
    }

    public void onCloseList() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("list.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        for (int i = 0; i < NewDownload.files.size(); i++) {
            out.writeObject(NewDownload.files.get(i));
        }
        out.close();
        fileOut.close();
    }

    public void onOpenList() throws IOException, ClassNotFoundException {

        java.io.File f = new java.io.File("list.jdm");
        if(f.exists() && !f.isDirectory()) {
            java.io.FileInputStream fileIn = new java.io.FileInputStream("list.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            while(true){
                NewDownload.files.add((File) in.readObject());
            }
        }

    }

    public void onCloseQueue() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("queue.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        for (int i = 0; i < QueueWindow.queueFiles.size(); i++) {
            out.writeObject(QueueWindow.queueFiles.get(i));
        }
        out.close();
        fileOut.close();
    }

    public void onOpenQueue() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("queue.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("queue.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            while(true){
                QueueWindow.queueFiles.add((File) in.readObject());
            }
        }
    }

    public void onCloseSettingsLimit() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("settingsLimit.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        out.writeObject(SettingsWindow.downloadLimit);

        out.close();
        fileOut.close();
    }

    public void onOpenSettingsLimit() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("settingsLimit.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("settingsLimit.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            SettingsWindow.downloadLimit = (int) in.readObject();
        }
    }

    public void onCloseSettingsLook() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("settingsLook.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        out.writeObject(SettingsWindow.lookAndFeel);

        out.close();
        fileOut.close();
    }

    public void onOpenSettingsLook() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("settingsLook.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("settingsLook.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            SettingsWindow.lookAndFeel.replace(0,SettingsWindow.lookAndFeel.length(), String.valueOf(in.readObject()));
        }
    }

    public void onCloseSettingsDirectory() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("settingsDirectory.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        out.writeObject(SettingsWindow.saveDirectory);

        out.close();
        fileOut.close();
    }

    public void onOpenSettingsDirectory() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("settingsDirectory.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("settingsDirectory.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

           SettingsWindow.saveDirectory.replace(0,SettingsWindow.saveDirectory.length(), String.valueOf(in.readObject()));
        }
    }

    public void onCloseSettingsBadURLs() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("filter.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        for (int i = 0; i < SettingsWindow.badURLs.size(); i++) {
            out.writeObject(SettingsWindow.badURLs.get(i));
        }
        out.close();
        fileOut.close();
    }

    public void onOpenSettingsBadURLs() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("filter.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("filter.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            while(true){
                SettingsWindow.badURLs.add((StringBuilder) in.readObject());
            }
        }
    }

    public void onCloseRemovedFiles() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("removed.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        for (int i = 0; i < NewDownload.removedFiles.size(); i++) {
            out.writeObject(NewDownload.removedFiles.get(i));
        }

        out.close();
        fileOut.close();
    }

    public void onOpenRemovedFiles() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("removed.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("removed.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            while(true){
                NewDownload.files.add((File) in.readObject());
            }
        }
    }

    public void onCloseSchedule() throws IOException {
        java.io.FileOutputStream fileOut = new java.io.FileOutputStream("schedule.jdm");
        java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(fileOut);

        out.writeObject(SettingsWindow.timeLeft);

        out.close();
        fileOut.close();
    }

    public void onOpenSchedule() throws IOException, ClassNotFoundException {
        java.io.File f = new java.io.File("schedule.jdm");

        if(f.exists() && !f.isDirectory()){
            java.io.FileInputStream fileIn = new java.io.FileInputStream("schedule.jdm");
            java.io.ObjectInputStream in = new java.io.ObjectInputStream(fileIn);

            SettingsWindow.timeLeft = (long) in.readObject();
        }
    }
}
