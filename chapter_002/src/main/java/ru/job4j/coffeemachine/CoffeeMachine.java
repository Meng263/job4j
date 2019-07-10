package ru.job4j.coffeemachine;

import java.util.Arrays;

/**
 * @author Oleg
 * @since 23.06.2019
 */
public class CoffeeMachine {
    /**
     *Конструктор по-умолчанию, инициализирует массив с номиналами монет
     */
    public CoffeeMachine() {
        coins = new int[]{1, 2, 5, 10};
    }

    /**
     * Массив, содержащий номиналы монет для сдачи
     */
    private final int[] coins;

    /**
     * Метод выдачи сдачи для автомата
     *
     * @param value купюра 50р 100р и т д
     * @param price цена кофе
     * @return массив монет
     */
    int[] changes(int value, int price) {
        int[] arr = new int[50];
        int maxCoin = coins[coins.length - 1];
        int change = value - price;
        int k = coins.length - 1;
        int i = 0;
        while (change != 0) {
            if (change >= maxCoin) {
                arr[i++] = maxCoin;
                change = change - maxCoin;
            } else {
                maxCoin = coins[--k];
            }
        }
        return Arrays.copyOf(arr, i);
    }
}
