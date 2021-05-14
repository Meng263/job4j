package ru.job4j.cache;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

public class TextFileCachedReaderTest {
    File workDir = new File(System.getProperty("java.io.tmpdir"), String.valueOf(new Date().getTime()));
    List<String> files = List.of("Address.txt", "Names.txt");

    @Before
    public void createTempFiles() throws IOException {
        workDir.mkdir();

        for (String fileName : files) {
            try (FileOutputStream fos = new FileOutputStream(new File(workDir, fileName))) {
                fos.write(this.getClass().getResourceAsStream("/" + fileName).readAllBytes());
            }
        }
    }

    @After
    public void deleteTempFiles() {
        File[] files = workDir.listFiles();
        for (File file : files) {
            file.deleteOnExit();
        }
        workDir.deleteOnExit();
    }

    @Test
    public void whenGetFileFromStorageShouldBeGot() throws IOException {
        TextFileCachedReader fileStorage = new TextFileCachedReader(workDir);
        String addresses = fileStorage.getTextByFileName("Address.txt");
        String expectedAddresses = Files.readString(new File(workDir, "Address.txt").toPath());

        Assert.assertEquals(addresses, expectedAddresses);
    }

    @Test
    public void whenGetFileFromStorageTwiceShouldBeEqualAndSecondFromCache() {
        TextFileCachedReader fileStorage = new TextFileCachedReader(workDir);
        String addresses = fileStorage.getTextByFileName("Address.txt");
        String addressesFromCache = fileStorage.getTextByFileName("Address.txt");

        Assert.assertEquals(addresses, addressesFromCache);
        Assert.assertEquals(fileStorage.getCountReadsFromFile(), 1);
    }
}