package ru.job4j.solid.dip.second;

import java.util.Scanner;

public class CurrencyConverter {
    private int courseDollar = 75;

    public void convertDollarToRuble() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, input amount in ruble: ");

        double result = Integer.parseInt(scanner.nextLine()) * courseDollar;
        System.out.printf("amount in dollar is %s%n", result);
    }

}
