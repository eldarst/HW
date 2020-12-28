package com.eldar.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


class Tester {

    static public boolean delDirectory(File dir) {
        if (dir.exists()) {
            String[] allFiles = dir.list();
            for (String f: allFiles) {
                File file = new File(f);
                if (file.isDirectory()) {
                    delDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return (dir.delete());
    }


    @Test
    void testWithAddedFile() throws IOException {
        File directory = new File("./DIRECTORY").getCanonicalFile();
        directory.mkdir();
        for (int i = 1; i < 3; ++i) {
            String newPath = "./DIRECTORY/FILE" + i + ".txt";
            File newFile = new File(newPath);
            newFile.createNewFile();
        }
        String newPath = "./DIRECTORY/FOLDER";
        File newDir = new File(newPath);
        newDir.mkdir();
        String outputPath = "./DIRECTORY/output.txt";
        File outFile = new File(outputPath);
        outFile.createNewFile();
        FileManager reader = new FileManager(outputPath, new File("./DIRECTORY"));
        String result = ";C:\\Users\\GOSH\\IdeaProjects\\project_1\\DIRECTORY\\FILE1.txt\n" +
                ";C:\\Users\\GOSH\\IdeaProjects\\project_1\\DIRECTORY\\FILE2.txt\n" +
                ";C:\\Users\\GOSH\\IdeaProjects\\project_1\\DIRECTORY\\FOLDER\n" +
                ";C:\\Users\\GOSH\\IdeaProjects\\project_1\\DIRECTORY\\output.txt\n";
        Assertions.assertEquals(result, reader.output);
        delDirectory(directory);
    }

    @Test
    void testNullDirectory() {
        Assertions.assertThrows(NullPointerException.class, () -> new FileManager("./DIRECTORY/NULL/output.txt", new File("./DIRECTORY/NULL")));
    }

    @Test
    void testNullFile() {
        String dirPath1 = "./DIRECTORY/FOLDER";
        File dirPath = new File(dirPath1);
        Assertions.assertThrows(RuntimeException.class, () -> new FileManager("./DIRECTORY/FILE/file.txt", new File("./DIRECTORY/FOLDER")));
    }


}