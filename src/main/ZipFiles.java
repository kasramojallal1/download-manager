package main;

import java.io.*;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFiles {

    public ZipFiles(){
        String zipFile = "archive.zip";
        String[] srcFiles = {"list.jdm", "queue.jdm", "removed.jdm", "schedule.jdm", "settingsDirectory.jdm", "settingsLimit.jdm", "filter.jdm", "language.jdm", "settingsLook.jdm"};

        try{
            byte[] buffer = new byte[1024];

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (int i = 0; i < srcFiles.length; i++) {
                File srcFile = new File(srcFiles[i]);

                FileInputStream fis = new FileInputStream(srcFile);
                zos.putNextEntry(new ZipEntry(srcFile.getName()));

                int length;

                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();
                fis.close();
            }
            zos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
