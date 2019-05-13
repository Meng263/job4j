package ru.job4j.tracker;

import java.util.Scanner;

public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, int[] range) {
        boolean illegal = true;
        int result = Integer.valueOf(this.ask(question));
        for (int i : range) {
            if (i == result) {
                illegal = false;
                break;
            }
        }
        if (!illegal) {
            return result;
        } else {
            throw new MenuOutException("Out of range");
        }
    }
}
