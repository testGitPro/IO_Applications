package vhrybyniuk.io.fileAnalyzer;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.regex.Pattern;

public class FileAnalyzer {
    private static final Pattern SENTENCE_PATTERN = Pattern.compile("((?<=[.?!]))");

    public static void main(String[] args) throws IOException {


        File file = new File(args[0]); // args[0] - path
        String query = args[1];         // word


        int searchCount = countOfCrossing(file, query);

        System.out.println("Кол-во вхождений искомого слова в файле" + searchCount);

        String[] foundSentances = findAllSentences(file, query);
        showFoundSentences(foundSentances);

    }

    static String readContent(File file) throws IOException {
        FileInputStream inputFileStream = new FileInputStream(file);
        byte[] content = inputFileStream.readAllBytes();
        inputFileStream.close();
        return new String(content);
    }

    public static int countOfCrossing(File file, String query) throws IOException {
        int counter = 0;

        String content = readContent(file);

        String[] words = content.toLowerCase().replaceAll(String.valueOf(SENTENCE_PATTERN), " ").split("\\s");
        for (String word : words) {
            if (word.equals(query))
                counter++;
        }
        return counter;
    }

    public static String[] findAllSentences(File file, String query) throws IOException {
        String content = readContent(file);
        int lengthArr = countOfCrossing(file, query);
        String[] sentences = content.split(String.valueOf(SENTENCE_PATTERN));
        int i = 0;
        String[] foundSentences = new String[lengthArr];

        for (String s : sentences) {
            if (countOfCrossing(file, query) > 0) {
                foundSentences[i] = s;
                i++;
            }
        }
        return foundSentences;
    }

    static void showFoundSentences(String[] sentences) {
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }


}

