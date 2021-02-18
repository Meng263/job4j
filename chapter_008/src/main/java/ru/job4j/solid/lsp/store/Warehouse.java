package ru.job4j.solid.lsp.store;

import java.util.Date;

public class Warehouse extends BaseStore {
    @Override
    public boolean accept(Food food, Date currentDate) {
        return food.getFreshnessCoefficient(currentDate) < 25;
    }

    @Override
    public int capacity() {
        return 200;
    }
}
