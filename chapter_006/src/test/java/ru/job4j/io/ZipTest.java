package ru.job4j.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ZipTest {
    @Test
    public void whenSendParamThanGetParam() {
        Map<String, String> expected = Map.of("-d", System.getProperty("java.io.tmpdir") + "/root",
                "-e", "*.doc", "-o", System.getProperty("java.io.tmpdir") + "/project.zip");
        String[] args = new String[]{"-d", System.getProperty("java.io.tmpdir") + "/root",
                "-e", "*.doc", "-o", System.getProperty("java.io.tmpdir") + "/project.zip"};
        Map<String, String> result = Zip.Args.getParam(args);
        assertThat(expected, is(result));
    }

    @Test
    public void whenPackFilesThanThisFilesContainsInZipFile() {
        List<File> result = new ArrayList<>();
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
                file = new File(secondChild, i * 3 + ".doc");
                file.createNewFile();
            }
            Zip.main(new String[]{"-d", System.getProperty("java.io.tmpdir") + "/root",
                   "-e", "*.doc", "-o", System.getProperty("java.io.tmpdir") + "/project.zip"});
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(System.getProperty("java.io.tmpdir") + "/project.zip"))) {
            ZipEntry entry;
            String fileName;
            while ((entry = zis.getNextEntry()) != null) {
                fileName = entry.getName();
                result.add(new File(fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(result);
        Collections.sort(expected);
        assertThat(result, is(expected));
    }
}