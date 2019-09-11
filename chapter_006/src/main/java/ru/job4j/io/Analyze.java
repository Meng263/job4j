package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

/**
 * Анализ доступности сервера
 */
public class Analyze {
    /**
     * Метод преобразует один файл в другой, на основе лога нахоит интервалы, когда не работал сервер
     * Сервер не работал. если status = 400 или 500.
     * @param source лог сервера
     * @param target файл с интервалами, когда сервер не работал в формате 
     * hh:mm:ss;hh:mm:ss; (начало периода;конец периода;) 
     */
    public void unavailable(String source, String target) {
        boolean isStartTimeInterval = false;
        List<String> sourceLines;
        List<String> targetLines = new ArrayList<>(100);
        String beginTimeInterval = "";
        String endTimeInterval = "";
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            sourceLines = read.lines().collect(Collectors.toList());
            read.close();
            for (String line : sourceLines) {
                if (!line.isEmpty()
                        && !line.startsWith("/*")
                        && !line.startsWith("*")
                        && !line.startsWith("#")
                        && !line.startsWith(" ")) {
                    String[] keyValueArr = line.split(" ");
                    int code = Integer.parseInt(keyValueArr[0]);
                    String time = keyValueArr[1];
                    if (!isStartTimeInterval && (code == 400 || code == 500)) {
                        isStartTimeInterval = true;
                        beginTimeInterval = time;
                    }
                    if (isStartTimeInterval && code != 400 && code != 500) {
                        isStartTimeInterval = false;
                        endTimeInterval = time;
                        targetLines.add(new StringBuilder()
                                .append(beginTimeInterval)
                                .append(";")
                                .append(endTimeInterval)
                                .append(";")
                                .toString());
                    }
                }
            }
            PrintWriter out = new PrintWriter(new FileOutputStream(target));
            targetLines.forEach(out::println);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}