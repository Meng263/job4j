package ru.job4j.cache;

import org.junit.After;
import org.junit.Before;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TextFileStorageTest {
    File workDir = new File(System.getProperty("java.io.tmpdir"), String.valueOf(new Date().getTime()));
    List<String> files = List.of("Address.txt", "Names.txt");

    @Before
    private void createTempFiles() throws IOException {
        workDir.mkdir();

        for (String fileName : files) {
            try (FileOutputStream fos = new FileOutputStream(new File(workDir, fileName))) {
                fos.write(this.getClass().getResourceAsStream(fileName).readAllBytes());
            }
        }
    }

    @After
    private void deleteTempFiles() {
        File[] files = workDir.listFiles();
        for (File file : files) {
            file.deleteOnExit();
        }
        workDir.deleteOnExit();
    }


}