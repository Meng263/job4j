package ru.job4j.solid.lsp.store;

public class Trash implements Store {
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
