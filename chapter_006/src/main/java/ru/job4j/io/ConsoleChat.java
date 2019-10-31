package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Консольный чат
 * Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * Программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить», программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить запиписываются в текстовый лог.
 */
public class ConsoleChat {
    final static String EXIT = "закончить";
    final static String STOP = "стоп";
    final static String CONT = "продолжить";
    /**
     * Метод содержит логику чата
     *
     * @throws IOException при ошибках ввода/вывода
     */
    private void chatStart() throws IOException {
        BufferedReader readerConsole = new BufferedReader(new InputStreamReader(System.in));
        String rootPath = System.getProperty("java.io.tmpdir");
        File root = new File(rootPath, "consoleChat");
        root.mkdir();
        File fileLog = new File(root, "log.txt");
        fileLog.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(fileLog);
        File fileChatWords = new File("chapter_006/src/main/resources/chatWords.txt");
        BufferedReader readerFile = new BufferedReader(new FileReader(fileChatWords));
        List<String> listWords = new ArrayList<>();
        String line = readerFile.readLine();
        while (line != null) {
            listWords.add(line);
            line = readerFile.readLine();
        }
        readerFile.close();
        String incomingMassage = "";
        boolean isSpeak = true;
        while (!incomingMassage.equals(EXIT)) {
            incomingMassage = readerConsole.readLine();
            fileOutputStream.write((incomingMassage + System.lineSeparator()).getBytes());
            if (incomingMassage.equals(STOP) || incomingMassage.equals(EXIT)) {
                isSpeak = false;
            }
            if (incomingMassage.equals(CONT)) {
                isSpeak = true;
            }
            if (isSpeak) {
                System.out.println(listWords.get(random(listWords.size())));
            }
        }
    }

    /**
     * Метод генерирует случайное число от 0 до length
     *
     * @param max конец диапазона
     * @return случайное число
     */
    private static int random(int max) {
        return (int) (Math.random() * max);
    }

    /**
     * Метод запускает программу
     *
     * @param args аргументы
     */
    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat();
        try {
            consoleChat.chatStart();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
