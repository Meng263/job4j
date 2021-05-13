package ru.job4j.cache;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TextFileCachedReader {
    private final File workDir;
    private final SoftCache<String, String> softCache = new SoftCache<>(this::readTextFromFileOrNull);

    private int countReadsFromFile = 0;

    public TextFileCachedReader(File workDir) {
        this.workDir = workDir;
    }

    public TextFileCachedReader(String workDirPath) {
        this.workDir = new File(workDirPath);
    }

    public int getCountReadsFromFile() {
        return countReadsFromFile;
    }

    public String getTextByFileName(String fileName) {
        return softCache.getValue(fileName);
    }

    private String readTextFromFileOrNull(String fileName) {
        String result = null;
        try {
            result = Files.readString(new File(workDir, "Address.txt").toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        countReadsFromFile++;
        return result;
    }

}
