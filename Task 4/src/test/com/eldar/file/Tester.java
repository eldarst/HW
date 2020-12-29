package com.eldar.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


class Tester {

    @Test
    void testWithAddedFiles() throws IOException {
        List<String> space = createDirectoryWithFiles();
        File directory = new File(space.get(0));
        String outputPath = space.get(1);
        new FileManager(outputPath, directory);
        String result = directory.getCanonicalPath() + "\\FILE1.txt\n" +
                directory.getCanonicalFile() + "\\FILE2.txt\n" +
                directory.getCanonicalFile() + "\\FOLDER\n" +
                directory.getCanonicalFile() + "\\output.txt\n";
        FileInputStream fin = new FileInputStream(outputPath);
        String realRes = new String();
        int i = -1;
        while ((i = fin.read()) != -1) {
            realRes = realRes + (char) i;
        }
        Assertions.assertEquals(result, realRes);
    }

    @Test
    void testNullDirectory() throws IOException {
        createDirectoryWithFiles();
        Assertions.assertThrows(FileNotFoundException.class, () -> new FileManager("./DIRECTORY/NULL/output.txt", new File("./DIRECTORY/NULL")));
    }

    @Test
    void testNullFile() throws IOException {
        createDirectoryWithFiles();
        Assertions.assertThrows(FileNotFoundException.class, () -> new FileManager("./DIRECTORY/FOLDER/file.txt", new File("./DIRECTORY/FOLDER")));
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


    private List<String> createDirectoryWithFiles() throws IOException {
        File directory = new File("./DIRECTORY").getCanonicalFile();
        if(directory.exists()){
            delDirectory(directory);
        }
        directory.mkdir();
        List<String> res = new LinkedList<>();
        res.add(directory.getName());
        for (int i = 1; i < 3; ++i) {
            String newPath = "./DIRECTORY/FILE" + i + ".txt";
            File newFile = new File(newPath);
            newFile.createNewFile();
        }
        String newPath = "./DIRECTORY/FOLDER";
        File newDir = new File(newPath);
        newDir.mkdir();
        String outputPath = "./DIRECTORY/output.txt";
        res.add(outputPath);
        File outFile = new File(outputPath);
        outFile.createNewFile();
        return res;
    }

}