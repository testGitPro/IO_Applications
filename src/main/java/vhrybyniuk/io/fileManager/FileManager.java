package vhrybyniuk.io.fileManager;

import java.io.*;

public class FileManager {
    private static int counter;


    public static void main(String[] args) throws IOException {
        //      File file = new File(args[0]); // args[0] - path
        //      String query = args[1];         // word
        String path = "/Users/volodimirgribinuk/Desktop/IOApiJava/src/main/java/vhrybyniuk/io/FileAnalyzer";
        String pathTo = "/Users/volodimirgribinuk/Desktop/IOApiJava/src/test/java/resources";
        int countFileInPackages = countFiles(path);
        int countDirs = countDirs(path);
        drop(pathTo);
  //      copy(path, pathTo);
    }

    public static int countFiles(String path) {
        int countFiles = 0;
        File fileFolder = new File(path);
        File[] files = fileFolder.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                countFiles++;
            }
            if (file.isDirectory()) {
                countFiles += countFiles(file.getAbsolutePath());
            }
        }
        return countFiles;
    }

    public static int countDirs(String path) {
        File fileFolder = new File(path);
        int counterDir = 0;

        for (File object : fileFolder.listFiles()) {
            if (object.isDirectory()){
                counterDir = counterDir + countDirs(object.getPath());
                counterDir++;
            }
        }
        return counterDir;
    }


    public static void copy(String from, String to) throws IOException {
        File elementsFrom = new File(from);
        File elementsTo = new File(to);

        if (elementsFrom.isFile()) { filesCoppy(elementsFrom, elementsTo); }
        if (elementsFrom.isDirectory()) { packagesCoppy(elementsFrom, elementsTo); }
       }


    private static void filesCoppy(File from, File to) throws IOException {
        InputStream inputStream = new FileInputStream(from);
        byte[] bytesArr = inputStream.readAllBytes();
        OutputStream outputStream = new FileOutputStream(to.getPath());
        outputStream.write(bytesArr);
        inputStream.close();
        outputStream.close();
    }

    private static void packagesCoppy(File from, File to) throws IOException {
        if (!to.exists()) {
            to.mkdir();
        }
        to = new File(to.getPath());
        for (File file : from.listFiles()) {
            if (file.isFile()){
                filesCoppy(file, new File(to.getPath() + "/" + file.getName() ));
            } else if (file.isDirectory()){
                String toName = to.getPath() + "/" + file.getName();
                packagesCoppy(file, new File(toName));
            }

        }
    }

    public static void move(String from, String to) throws IOException {
        copy(from, to);
        drop(from);
    }

    public  static  void drop (String from) {
        File fileFrom = new File(from);
        for (File file : fileFrom.listFiles()) {
            if (file.isDirectory()) {
                drop(file.getPath());
                file.delete();
            }
            file.delete();
        }
    }


}


