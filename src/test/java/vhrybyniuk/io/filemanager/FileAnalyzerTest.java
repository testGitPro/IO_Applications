package vhrybyniuk.io.filemanager;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static vhrybyniuk.io.fileAnalyzer.FileAnalyzer.countOfCrossing;
import static vhrybyniuk.io.fileAnalyzer.FileAnalyzer.findAllSentences;

public class FileAnalyzerTest {
    File file = new File("src/test/java/resources/loremIpsum.txt");
    String query = "lorem";

    @Test
    public void testCountOfCrossing() throws IOException {
        int actual = countOfCrossing(file, query);
        assertEquals(3, actual);
    }

    @Test
    public void testFindAllSentences() throws IOException {
        String[] actual = findAllSentences(file, query);
        assertEquals(3, actual.length);
    }
}
