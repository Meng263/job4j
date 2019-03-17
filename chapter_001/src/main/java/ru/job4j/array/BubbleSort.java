package ru.job4j.array;

/**
 * Сортировка пузырьком
 */
public class BubbleSort {
    /**
     * @param mas массив, который сортируем
     * @return отсортированный массив
     */
    public int[] sortBubble(int[] mas){
        int tmp = 0;
        for (int i = mas.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (mas[j] > mas[j + 1]){
                    tmp = mas[j];
                    mas[j] = mas[j + 1];
                    mas[j + 1] = tmp;
                }
            }
        }
        return mas;
    }
}
