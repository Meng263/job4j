package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Архивация файлов
 */
public class Zip {
    /**
     * Метод упаковывает список файлов в зип архив
     * @param source список файлов
     * @param target файл архива
     */
    private void pack(List<File> source, File target) {
        try (ZipOutputStream zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            source.forEach(file -> {
                if (!file.equals(target)) {
                    try {
                        zos.putNextEntry(new ZipEntry(file.getPath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zos.write(out.readAllBytes());
                        System.out.println("Добавленен в архив файл " + file.getCanonicalPath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод ищет в заданном каталоге и во воложенных каталогах файлы с расширением
     * файлы без расширения игнорируются
     *
     * @param rootPath путь к каталогу
     * @param ext      расширение
     * @return список файлов
     * @throws IOException в случае, если заданный каталог не является каталогом
     */
    private List<File> seekBy(String rootPath, String ext) throws IOException {
        String[] splitExt = ext.split("\\.");
        String actualExt = splitExt[splitExt.length - 1];
        List<File> result = new ArrayList<>();
        File root = new File(rootPath);
        if (!root.isDirectory()) {
            throw new IOException("Not is directory!");
        }
        Queue<File> queue = new LinkedList<>(Arrays.asList(Objects.requireNonNull(root.listFiles())));
        while (!queue.isEmpty()) {
            File currentFile = queue.poll();
            if (currentFile.isDirectory()) {
                queue.addAll(Arrays.asList(Objects.requireNonNull(currentFile.listFiles())));
            } else {
                String[] split = currentFile.getName().split("\\.");
                if (split.length > 0) {
                    String currentFileExt = split[split.length - 1];
                    if (!actualExt.equals(currentFileExt)) {
                        result.add(currentFile);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Метод запускает программу, параметры нужно указать:
     *
     * -d - directory - которую мы хотим архивировать
     * -e - exclude - исключить файлы *.xml
     * -o - output - во что мы архивируем.
     *
     * @param args массив параметров
     */
    public static void main(String[] args) {
        Zip zip = new Zip();
        Map<String, String> map = Args.getParam(args);
        try {
            zip.pack(zip.seekBy(map.get("-d"), map.get("-e")), new File(map.get("-o")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Класс содержит статический метод для парсинга параметров запуска
     */
     static class Args {
        /**
         * Метод парсит параметры запуска и возвращает в виде Map
         * @param args параметры запуска
         * @return Map ключ строка с префиксом "-", значение следующая строка без префикса
         */
         static Map<String, String> getParam(String[] args) {
            Map<String, String> map = new HashMap<>();
            String keyTmp = "";
            String valueTmp = "";
            for (String param : args) {
                if (param.startsWith("-")) {
                    keyTmp = param;
                } else {
                    valueTmp = param;
                }
                if (!keyTmp.isEmpty() && !valueTmp.isEmpty()) {
                    map.put(keyTmp, valueTmp);
                    keyTmp = "";
                    valueTmp = "";
                }
            }
            return map;
        }
    }
}