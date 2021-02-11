package ru.job4j.solid.lsp.store;

public class Trash implements Store {
    @Override
    public int capacity() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int countProduct() {
        return 0;
    }

    @Override
    public boolean addProduct(Food food) {
        return true;
    }
}
