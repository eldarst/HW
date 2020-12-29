package com.eldar.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {

    public FileManager(String out, File dir) throws IOException {
        if(!dir.exists()) {
            throw new FileNotFoundException("Directory is not found!");
        } else if(!(new File(out).getCanonicalFile().exists())) {
            throw new FileNotFoundException("File is not found!");
        }
        scanAndWrite(dir, out);

    }


    private static void scanAndWrite(File dir, String output) {

        Logger log = Logger.getLogger(FileManager.class.getName());
        try(FileOutputStream out = new FileOutputStream(output,true)) {
            File directory = dir.getCanonicalFile();
            String[] allFiles = directory.list();

            if (allFiles.length > 0) {
                for (String file : allFiles) {
                    String path = dir + File.separator + file;
                    File currentFile = new File(path).getCanonicalFile();
                    if (currentFile.isDirectory()) {
                        byte[] buffer = (currentFile + "\n").getBytes();
                        out.write(buffer);
                        scanAndWrite(currentFile.getCanonicalFile(), output);
                    } else if (currentFile.isFile()) {
                        byte[] buffer = (currentFile + "\n").getBytes();
                        out.write(buffer);
                        }
                }
            } else {
                log.info( dir + " is empty");
            }
        } catch (Exception ex) {

            throw new RuntimeException();
        }


    }


}
