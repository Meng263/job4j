package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Сканирование файловой системы
 */
public class Search {
    /**
     * Метод ищет файлы с заданным расширением во всех вложенных каталогах
     *
     * @param parent путь до каталога, с которого начинатеся поиск
     * @param exts   список расширений
     * @return список найденных файлов
     * @throws IOException если каталог, скоторого начинается поиск не является каталогом
     */
    List<File> searchFiles(String parent, List<String> exts) throws IOException {
        List<File> result = new ArrayList<>();
        File root = new File(parent);
        if (!root.isDirectory()) {
            throw new IOException("Not is directory!");
        }
        Queue<File> queue = new LinkedList<File>(Arrays.asList(root.listFiles()));
        while (!queue.isEmpty()) {
            File currentFile = queue.poll();
            if (currentFile.isDirectory()) {
                if (currentFile.list() != null) {
                    queue.addAll(Arrays.asList(currentFile.listFiles()));
                }
            } else {
                String[] split = currentFile.getName().split("\\.");
                String currentFileExt = "";
                if (split.length > 0) {
                    currentFileExt = split[split.length - 1];
                }
                for (String ext : exts) {
                    if (ext.equals(currentFileExt)) {
                        result.add(currentFile);
                        break;
                    }
                }
            }
        }
        return result;
    }
}
