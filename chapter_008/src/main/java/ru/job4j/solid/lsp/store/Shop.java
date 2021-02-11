package ru.job4j.solid.lsp.store;

import java.util.ArrayList;
import java.util.List;

public class Shop extends BaseStore {
    private final List<Food> internalStorage = new ArrayList<>();

    @Override
    public int capacity() {
        return 50;
    }
}
