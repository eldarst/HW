package com.eldar.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileManager {

    public static String output = new String();

    public FileManager(String out, File dir) throws IOException {
        try {
            if (!dir.getCanonicalFile().exists()) {
                throw new NullPointerException("Your directory is wrong!");
            }
            getAllFromDirectory(dir);
            printToFile(new File(out));
        } catch (Exception ex) {
            throw ex;
        }
    }


    private static void getAllFromDirectory(File dir) throws IOException {
        try {
            File directory = dir.getCanonicalFile();
            String[] allFiles = directory.list();

            if (allFiles.length > 0) {
                for (String file : allFiles) {
                    String path = dir + File.separator + file;
                    File currentFile = new File(path).getCanonicalFile();
                    if (currentFile.isDirectory()) {
                        output = output + File.pathSeparator + currentFile + "\n";
                        getAllFromDirectory(currentFile.getCanonicalFile());
                    } else if (currentFile.isFile()) {
                        output = output + File.pathSeparator + currentFile + "\n";
                    }
                }
            }
        } catch (Exception er) {
            throw er;
        }


    }


    public void printToFile(File dir) {
        try {
            if (!dir.getCanonicalFile().exists()) {
                throw new RuntimeException("You are trying to write on non existent file!");
            }
            Files.write(Paths.get(dir.getCanonicalPath()), output.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.getCause();
        }


    }


}
