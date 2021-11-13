package vhrybyniuk.io.filemanager;

import org.junit.jupiter.api.Test;
import vhrybyniuk.io.fileManager.FileManager;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileManagerTest {

    @Test //1
    public void testCountFilesInEmptyFolder() {
        int actual = FileManager.countFiles("src/test/java/resources/TEST1(TEST3)_emptyFolder");
        assertEquals(0, actual);
    }

    @Test //2
    public void testCountFilesInFolderWithFiles() {
        int actual = FileManager.countFiles("src/test/java/resources/TEST2_FolderWithThreeFiles");
        assertEquals(3, actual);
    }

    @Test //3
    public void testCountSubFoldersInEmptyFolder() {
        int actual = FileManager.countDirs("src/test/java/resources/TEST1(TEST3)_emptyFolder");
        assertEquals(0, actual);
    }

    @Test //4
    public void testCountSubFoldersAndFilesInFolder() {
        int actual = FileManager.countFiles("src/test/java/resources/TEST4(TEST5)_CountSubFoldersAndFilesInFolder");
        assertEquals(3, actual);
    }

    @Test //5
    public void testCopyFromFoldreWithOneFile() throws IOException {
        FileManager.copy("src/test/java/resources/TEST4(TEST5)_CountSubFoldersAndFilesInFolder/Folder_1", "src/test/java/resources/TEST5_Copy_fromPackageWithOneFile");
        int actual = FileManager.countFiles("src/test/java/resources/TEST5_Copy_fromPackageWithOneFile");
        assertEquals(1, actual);
    }

    @Test //6
    public void testCopyFromFolderWithSubFolder() throws IOException {
        FileManager.copy("src/test/java/resources/TEST6_EmptyFolderWithSubFolder", "src/test/java/resources/TEST6_Test_EmptyFolderWithSubFolder");
        int actual = FileManager.countDirs("src/test/java/resources/TEST6_Test_EmptyFolderWithSubFolder");
        assertEquals(1, actual);
    }


}
