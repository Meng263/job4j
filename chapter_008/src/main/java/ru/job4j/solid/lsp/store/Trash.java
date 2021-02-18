package ru.job4j.solid.lsp.store;

import java.util.Date;

public class Trash implements Store {
    @Override
    public boolean accept(Food food, Date currentDate) {
        return food.getFreshnessCoefficient(currentDate) >= 100;
    }

    private int count = 0;

    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int countProduct() {
        return count;
    }

    @Override
    public boolean addProduct(Food food) {
        count++;
        return true;
    }
}
