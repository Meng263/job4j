package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SearchTest {
    @Test
    public void whenSearchFilesInDirectoryThanReturnListOfTenFiles() {
        Search search = new Search();
        List<File> result = null;
        List<File> expected = new ArrayList<>();
        String rootPath = System.getProperty("java.io.tmpdir");
        File root = new File(rootPath, "root");
        root.mkdir();
        File firstChild = new File(root, "first");
        firstChild.mkdir();
        File secondChild = new File(firstChild, "second");
        secondChild.mkdir();
        try {
            for (int i = 0; i < 5; i++) {
                File file = new File(root, i + ".txt");
                file.createNewFile();
                expected.add(file);
            }
            for (int i = 0; i < 3; i++) {
                File file = new File(firstChild, i + ".log");
                file.createNewFile();
                expected.add(file);
            }
            for (int i = 0; i < 2; i++) {
                File file = new File(secondChild, i + ".odt");
                file.createNewFile();
                expected.add(file);
            }
            result = search.searchFiles(root.getPath(), new ArrayList<>(Arrays.asList("txt", "log", "odt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        Collections.sort(expected);
        assertThat(result, is(expected));
    }
}