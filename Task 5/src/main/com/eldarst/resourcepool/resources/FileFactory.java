package com.eldarst.resourcepool.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class FileFactory implements ResourceFactory<File> {


    static AtomicInteger atomic = new AtomicInteger(0);

    public FileFactory() {
    }

    @Override
    public File create() throws IOException {
        String currentLocation = "./DIRECTORY";
        File currentDirectory = new File(currentLocation);
        if (currentDirectory.exists()) {
            delDirectory(currentDirectory);
        }
        currentDirectory.mkdir();
        File file = new File(currentDirectory + "/FILE" + (atomic.incrementAndGet()) + ".txt");
        if (file.exists()) {
            throw new FileNotFoundException();
        }
        System.out.println(file.getCanonicalFile());
        return file;
    }

    static public boolean delDirectory(File dir) throws IOException {
        if (dir.exists()) {
            File[] allFiles = dir.listFiles();
            for (File file : allFiles) {
                if (file.isDirectory()) {
                    delDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (dir.delete());
    }
}
