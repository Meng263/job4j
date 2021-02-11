package ru.job4j.solid.lsp.store;

public interface Store {
    int capacity();

    int countProduct();

    boolean addProduct(Food food);
}
