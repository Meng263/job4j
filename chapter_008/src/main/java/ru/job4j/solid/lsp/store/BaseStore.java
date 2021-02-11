package ru.job4j.solid.lsp.store;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseStore implements Store {
    private final List<Food> internalStorage = new ArrayList<>();
    @Override
    public int countProduct() {
        return internalStorage.size();
    }

    @Override
    public boolean addProduct(Food food) {
        if (countProduct() > capacity()) return false;
        return internalStorage.add(food);
    }
}
