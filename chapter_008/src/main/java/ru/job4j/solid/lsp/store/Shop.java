package ru.job4j.solid.lsp.store;

import java.util.Date;

public class Shop extends BaseStore {
    @Override
    public boolean accept(Food food, Date currentDate) {
        int freshnessCoefficient = food.getFreshnessCoefficient(currentDate);
        return freshnessCoefficient > 25 && freshnessCoefficient < 75;
    }

    @Override
    public int capacity() {
        return 50;
    }
}
